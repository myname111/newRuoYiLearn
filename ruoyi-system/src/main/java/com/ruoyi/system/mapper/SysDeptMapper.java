package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDeptMapper {
    //根据部门id查询部门
   public SysDept selectDeptById(Long deptId);
   //查询部门列表信息
    List<SysDept> selectDeptList(SysDept sysDept);

    SysDept checkDeptNameUnique(@Param("deptName") String deptName,@Param("parentId") Long parentId);

    int insertDept(SysDept sysDept);

    List<SysDept> selectChildrenDept(SysDept sysDept);

    int updateChildrenDept(@Param("depts") List<SysDept> childrenDept);

    int updateDeptStatus(SysDept sysDept);

    int updateDept(SysDept sysDept);

    int selectDeptCount(Long deptId);

    int deleteDept(Long deptId);
}
