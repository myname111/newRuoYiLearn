package com.ruoyi.gen.mapper;

import com.ruoyi.gen.domain.ColumnInfo;
import com.ruoyi.gen.domain.TableInfo;

import java.util.List;

public interface GenMapper {
    List<TableInfo> selectTableList(TableInfo tableInfo);

    TableInfo selectTableByTableName(String tableName);

    List<ColumnInfo> selectColumnsByTableName(String tableName);
}
