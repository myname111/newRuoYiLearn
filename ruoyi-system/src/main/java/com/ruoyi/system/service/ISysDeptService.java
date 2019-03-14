package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysDept;

import java.util.List;
import java.util.Map;

public interface ISysDeptService {
    //根据部门id查询部门信息
    public SysDept selectDeptById(Long deptId);

    List<SysDept> selectDeptList(SysDept sysDept);

    List<Map<String,Object>> selectDeptTree(SysDept sysDept);

    String checkDeptNameUnique(SysDept sysDept);

    int insertDept(SysDept sysDept);

    int updateDept(SysDept sysDept);

    int selectDeptCount(Long deptId);

    int deleteDept(Long deptId);
}
