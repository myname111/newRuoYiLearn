package com.ruoyi.gen.util;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.gen.domain.ColumnInfo;
import com.ruoyi.gen.domain.TableInfo;
import org.apache.velocity.VelocityContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenUtils {
    /**
     * 项目空间路径
     */
    private static final String PROJECT_PATH = getProjectPath();
    /**
     * myatis空间路径
     */
    private static final String MYBATIS_PATH = "main/resources/mapper";
    /**
     * html空间路径
     */
    private static final String TEMPLATES_PATH="main/resources/templates";

    /**
     * 类型转换
     */
    public static Map<String,String> javaTypeMap = new HashMap<>();
    /**
     * 获取模板信息
     * @param tableInfo
     * @return
     */
    public static VelocityContext getVelocityContext(TableInfo tableInfo) {
        //java对象传递到模板文件,用于填充模板文件中的velocity标签
        VelocityContext context = new VelocityContext();
        String packageName = Global.getPackageName();
        context.put("tableName",tableInfo.getTableName());//表名称
        context.put("tableComment",replaceKeyword(tableInfo.getTableComment()));//表注释
        context.put("primaryKey",tableInfo.getPrimaryKey());//主键
        context.put("className",tableInfo.getClassName());//类名(首字母大写);
        context.put("classname",tableInfo.getClassname());//类名(首字母小写)
        context.put("moduleName",getModuleName(packageName));
        context.put("columns",tableInfo.getColumns());//列信息
        context.put("basePackage",getBasePackage(packageName));
        context.put("package",packageName);
        context.put("author",Global.getAuthor());
        context.put("datetime", DateUtils.getDate());
        return context;
    }

    private static String getBasePackage(String packageName) {
        int lastIndex=packageName.lastIndexOf(".");
        String basePackage = StringUtils.substring(packageName,0,lastIndex);
        return basePackage;
    }

    //TODO:正则表达式
    public static String replaceKeyword(String keyword){
        String keyName = keyword.replaceAll("(?:表|信息)","");
        return keyName;
    }
    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<>();
        templates.add("vm/java/domain.java.vm");
        templates.add("vm/java/Mapper.java.vm");
        templates.add("vm/java/Service.java.vm");
        templates.add("vm/java/ServiceImpl.java.vm");
        templates.add("vm/java/Controller.java.vm");
        templates.add("vm/xml/Mapper.xml.vm");
        templates.add("vm/html/list.html.vm");
        templates.add("vm/html/add.html.vm");
        templates.add("vm/html/edit.html.vm");
        templates.add("vm/sql/sql.vm");
        return templates;
    }

    /**
     * 表名称转换成java类名
     * @param tableName
     * @return
     */
    public static String tableToJava(String tableName) {
        //是否自动移除前缀
         String autoRemovePre =  Global.getAutoRemovePre();
         //表前缀
         String tablePrefix =  Global.getTablePrefix();
         if(Constants.AUTO_REMOVE_PRE.equals(autoRemovePre)&& StringUtils.isNotEmpty(tablePrefix)){
             tableName=tableName.replaceFirst(tablePrefix,"");
         }
         return StringUtils.convertToCamelCase(tableName);
    }

    public static List<ColumnInfo> transColumns(List<ColumnInfo> columns) {
        List<ColumnInfo> columnsList = new ArrayList<>();
        for (ColumnInfo columnInfo: columns) {
            String attrName = StringUtils.convertToCamelCase(columnInfo.getColumnName());
            columnInfo.setAttrName(attrName);
            columnInfo.setAttrname(StringUtils.uncapitalize(attrName));

            String attrType = javaTypeMap.get(columnInfo.getDataType());
            columnInfo.setAttrType(attrType);

            columnsList.add(columnInfo);
        }
        return columnsList;
    }
    static{
        javaTypeMap.put("tinyint", "Integer");
        javaTypeMap.put("smallint", "Integer");
        javaTypeMap.put("mediumint", "Integer");
        javaTypeMap.put("int", "Integer");
        javaTypeMap.put("number", "Integer");
        javaTypeMap.put("integer", "integer");
        javaTypeMap.put("bigint", "Long");
        javaTypeMap.put("float", "Float");
        javaTypeMap.put("double", "Double");
        javaTypeMap.put("decimal", "BigDecimal");
        javaTypeMap.put("bit", "Boolean");
        javaTypeMap.put("char", "String");
        javaTypeMap.put("varchar", "String");
        javaTypeMap.put("varchar2", "String");
        javaTypeMap.put("tinytext", "String");
        javaTypeMap.put("text", "String");
        javaTypeMap.put("mediumtext", "String");
        javaTypeMap.put("longtext", "String");
        javaTypeMap.put("time", "Date");
        javaTypeMap.put("date", "Date");
        javaTypeMap.put("datetime", "Date");
        javaTypeMap.put("timestamp", "Date");
    }

    public static String getModuleName(String packageName) {
       int lastIndex =  packageName.lastIndexOf(".");
       int nameLength=packageName.length();
       String moduleName = StringUtils.substring(packageName,lastIndex+1,nameLength);
       return moduleName;
    }

    /**
     * 获取文件名
     * @param template
     * @param tableInfo
     * @param moduleName
     * @return
     */
    public static String getFileName(String template, TableInfo tableInfo, String moduleName) {
        String classname=tableInfo.getClassname();//类名,首字母小写
        String className = tableInfo.getClassName();//类名,首字母大写
        String javaPath=PROJECT_PATH;
        String mybatisPath=MYBATIS_PATH+"/"+moduleName+"/"+className;
        String htmlPath=TEMPLATES_PATH+"/"+moduleName+"/"+classname;
        if (template.contains("domain.java.vm")){
            return javaPath+"domain"+"/"+className+".java";
        }else if (template.contains("Mapper.java.vm")){
            return javaPath+"mapper"+"/"+className+"Mapper.java";
        }else if (template.contains("Service.java.vm")){
            return javaPath+"service"+"/"+"I"+className+"Service.java";
        }else if (template.contains("ServiceImpl.java.vm")){
            return javaPath+"service"+"/impl/"+className+"ServiceImpl.java";
        }else if (template.contains("Controller.java.vm")){
            return javaPath+"controller"+"/"+className+"Controller.java";
        }else if(template.contains("Mapper.xml.vm")){
             return mybatisPath+"Mapper.xml";
        }else if (template.contains("list.html.vm")){
            return htmlPath+"/"+classname+".html";
        }else if (template.contains("edit.html.vm")){
            return htmlPath+"/"+"edit.html";
        }else if (template.contains("add.html.vm")){
            return htmlPath+"/"+"add.html";
        }else if (template.contains("sql.vm")){
            return classname+"Menu.sql";
        }
        return null;
    }
    public static String getProjectPath() {
        String packageName = Global.getPackageName();
        StringBuilder projectPath = new StringBuilder();
        projectPath.append("main/java/");
        projectPath.append(packageName.replace(".","/"));
        projectPath.append("/");
        return projectPath.toString();
    }

}
