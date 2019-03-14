package com.ruoyi.gen.domain;

import com.ruoyi.common.base.BaseEntity;
import com.ruoyi.common.utils.StringUtils;
import sun.util.resources.ga.LocaleNames_ga;

import java.util.List;

/**
 * 表信息
 */
public class TableInfo extends BaseEntity {
    /**
     * 表名称
     */
    private String tableName;
    /*表描述*/
    private String tableComment;
    /**
      表的列信息(主键)
     */
    private ColumnInfo primaryKey;
    /**
     * 表的列信息(非主键)
     */
    private List<ColumnInfo> columns;
    /**
     *表对应的类的类名(首字母大写)
     */
    private String className;
    /**
     * 表对应的类的类名(首字母小写)
     */
    private String classname;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public ColumnInfo getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ColumnInfo primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<ColumnInfo> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnInfo> columns) {
        this.columns = columns;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
    //获取主键信息
    public ColumnInfo getColumnInfoLast(){
        ColumnInfo columnInfo = null;
        if(StringUtils.isNotNull(columns)&&columns.size()>0){
            columnInfo=columns.get(0);
        }
        return columnInfo;
    }
}
