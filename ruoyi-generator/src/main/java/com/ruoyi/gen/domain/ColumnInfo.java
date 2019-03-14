package com.ruoyi.gen.domain;

import com.ruoyi.common.base.BaseEntity;
import com.ruoyi.common.json.JSON;
import com.ruoyi.common.utils.StringUtils;

/**
 * 列信息
 */
public class ColumnInfo extends BaseEntity {
    /**
     * 字段名称
     */
    private String columnName;
    /**
     *字段类型
     */
    private String dataType;
    /**
     *列描述
     */
    private String columnComment;
    /**
     * 列配置
     */
    private ColumnConfigInfo configInfo;
    /**
     * java属性类型
     */
    private String attrType;
    /**
     * java属性名称(第一个字母大写).例如: dept_id=>DeptId
     */
     private String attrName;
    /**
     * java属性名称(第一个字母小写),例如:user_name=>userName
     */
    private String attrname;
    /**
     * 执行计划(包含了与索引有关的一些细节信息)
     */
    private String extra;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) throws Exception{
        if(StringUtils.isNotEmpty(columnComment)&&columnComment.startsWith("{")){
            this.configInfo = JSON.unmarshal(columnComment,ColumnConfigInfo.class);
            this.columnComment=configInfo.getTitle();
        }else{
            this.columnComment=columnComment;
        }
    }

    public ColumnConfigInfo getConfigInfo() {
        return configInfo;
    }

    public void setConfigInfo(ColumnConfigInfo configInfo) {
        this.configInfo = configInfo;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrname() {
        return attrname;
    }

    public void setAttrname(String attrname) {
        this.attrname = attrname;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
