package com.ruoyi.gen.service;

import com.ruoyi.gen.domain.TableInfo;

import java.util.List;

public interface IGenService {
    List<TableInfo> selectTableList(TableInfo tableInfo);

    byte[] generatorCode(String tableName);

    byte[] generatorCode(String[] tableNames);
}
