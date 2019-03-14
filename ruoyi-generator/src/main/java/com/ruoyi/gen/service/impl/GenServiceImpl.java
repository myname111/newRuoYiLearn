package com.ruoyi.gen.service.impl;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.gen.domain.ColumnInfo;
import com.ruoyi.gen.domain.TableInfo;
import com.ruoyi.gen.mapper.GenMapper;
import com.ruoyi.gen.service.IGenService;
import com.ruoyi.gen.util.GenUtils;
import com.ruoyi.gen.util.VelocityInitializer;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class GenServiceImpl implements IGenService {
    private static final Logger log = LoggerFactory.getLogger(GenServiceImpl.class);

    @Autowired
    private GenMapper genMapper;
    @Override
    public List<TableInfo> selectTableList(TableInfo tableInfo) {
        return genMapper.selectTableList(tableInfo);
    }

    @Override
    public byte[] generatorCode(String tableName) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        TableInfo tableInfo = genMapper.selectTableByTableName(tableName);
        List<ColumnInfo> columnList= genMapper.selectColumnsByTableName(tableName);
        generatorCode(tableInfo,columnList,zip);
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    @Override
    public byte[] generatorCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName:tableNames) {
            TableInfo tableInfo = genMapper.selectTableByTableName(tableName);
            List<ColumnInfo> columnList= genMapper.selectColumnsByTableName(tableName);
            generatorCode(tableInfo,columnList,zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    /**
     * TODO:生成代码
     * @param tableInfo
     * @param columns
     * @param zip
     */
    public void generatorCode(TableInfo tableInfo, List<ColumnInfo> columns, ZipOutputStream zip ){
       //表名转换成java属性名
        //通过表明获取类名(首字母大写)
        String className =  GenUtils.tableToJava(tableInfo.getTableName());
        tableInfo.setClassName(className);
        //首字母小写
        tableInfo.setClassname(StringUtils.uncapitalize(className));
        //列信息
        tableInfo.setColumns(GenUtils.transColumns(columns));
        //主键
        tableInfo.setPrimaryKey(tableInfo.getColumnInfoLast());
        //获取包名和模块名
        String packageName =  Global.getPackageName();
        String moduleName =  GenUtils.getModuleName(packageName);

       //初始化Velocity引擎
        VelocityInitializer.initVelocity();
        //获取VelocityContext
        VelocityContext velocityContext = GenUtils.getVelocityContext(tableInfo);
        //获取模板名称列表
        List<String> templates = GenUtils.getTemplates();
        for (String template: templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            //根据模板名称获取模板对象
            Template tpl = Velocity.getTemplate(template, Constants.UTF8);
            tpl.merge(velocityContext,sw);
            try {
                //添加到zip TODO:具体是如何做到的
                zip.putNextEntry(new ZipEntry(GenUtils.getFileName(template,tableInfo,moduleName)));
                IOUtils.write(sw.toString(),zip,Constants.UTF8);
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                log.error("渲染模板失败,表名:"+tableInfo.getTableName(),e);
            }
        }
    }
}
