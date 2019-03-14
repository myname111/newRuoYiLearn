package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.service.ISysDeptService;
import org.apache.poi.ddf.UnknownEscherRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysDeptServiceImpl implements ISysDeptService{
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Override
    public SysDept selectDeptById(Long deptId) {
        return sysDeptMapper.selectDeptById(deptId);
    }

    @Override
    public List<SysDept> selectDeptList(SysDept sysDept) {
        return sysDeptMapper.selectDeptList(sysDept);
    }

    @Override
    public List<Map<String, Object>> selectDeptTree(SysDept sysDept) {
        List<Map<String,Object>> trees = new ArrayList<>();
        List<SysDept> deptList = sysDeptMapper.selectDeptList(sysDept);
        trees = getTrees(deptList,false,null);
        return trees;
    }

    /**
     * 校验部门名称的唯一性
     * @param sysDept 部门信息
     * @return 是否唯一
     */
    @Override
    public String checkDeptNameUnique(SysDept sysDept) {
        Long  deptId= StringUtils.isNull(sysDept.getDeptId())?-1L:sysDept.getDeptId();
        SysDept dept = sysDeptMapper.checkDeptNameUnique(sysDept.getDeptName(),sysDept.getParentId());
        if (StringUtils.isNotNull(dept)&&dept.getDeptId().longValue()!=deptId.longValue()){
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }

    @Override
    public int insertDept(SysDept sysDept) {
        SysDept dept =  sysDeptMapper.selectDeptById(sysDept.getParentId());
        if (StringUtils.isNull(dept)){
            throw new BusinessException("父部门不存在");
        }
        if (!UserConstants.DEPT_NORMAL.equals(dept.getStatus())){
            throw new BusinessException("父部门停用,禁止添加子部门");
        }
        if (UserConstants.DEPT_NAME_NOT_UNIQUE.equals(checkDeptNameUnique(sysDept))){
            throw new BusinessException("部门已存在");
        }
        sysDept.setAncestors(dept.getAncestors()+","+dept.getDeptId());
        return sysDeptMapper.insertDept(sysDept);
    }
    //修改部门信息
    @Override
    public int updateDept(SysDept sysDept) {
        SysDept parentDept = sysDeptMapper.selectDeptById(sysDept.getParentId());
        if (StringUtils.isNull(parentDept)){
            throw new BusinessException("父部门不存在");
        }
        SysDept dept = sysDeptMapper.selectDeptById(sysDept.getDeptId());
        if(StringUtils.isNull(dept)){
            throw new BusinessException("部门不存在");
        }
        if (!dept.getParentId().equals(sysDept.getParentId())){
            sysDept.setAncestors(parentDept.getAncestors()+","+parentDept.getDeptId());
            List<SysDept> childrenDept = sysDeptMapper.selectChildrenDept(sysDept);
            if (StringUtils.isNotNull(childrenDept)){
                for (SysDept child:childrenDept) {
                    String ancestors = child.getAncestors();
                    ancestors = sysDept.getAncestors()+ancestors.substring(ancestors.indexOf(","+sysDept.getDeptId()+",")<0?ancestors.lastIndexOf(","):ancestors.indexOf(","+sysDept.getDeptId()+","));
                    child.setAncestors(ancestors);
                    child.setUpdateBy(sysDept.getUpdateBy());
                }
                sysDeptMapper.updateChildrenDept(childrenDept);
            }
        }
        sysDeptMapper.updateDeptStatus(sysDept);
        int row = sysDeptMapper.updateDept(sysDept);
        return row;
    }

    @Override
    public int selectDeptCount(Long deptId) {
        return sysDeptMapper.selectDeptCount(deptId);
    }

    @Override
    public int deleteDept(Long deptId) {
        return sysDeptMapper.deleteDept(deptId);
    }

    /**
     *
     * @param deptList
     * @param isCheck
     * @param roleDeptList
     * @return
     */
    public List<Map<String,Object>> getTrees(List<SysDept> deptList,boolean isCheck,List<String> roleDeptList){
        List<Map<String,Object>> trees = new ArrayList<>();
        for (SysDept dept:deptList) {
            if(UserConstants.DEPT_NORMAL.equals(dept.getStatus())){
                Map<String,Object> deptMap = new HashMap<>();
                deptMap.put("id",dept.getDeptId());
                deptMap.put("pId",dept.getParentId());
                deptMap.put("name",dept.getDeptName());
                deptMap.put("title",dept.getDeptName());
                if (isCheck){
                    deptMap.put("checked",roleDeptList.contains(dept.getDeptId()+dept.getDeptName()));
                }else{
                    deptMap.put("checked",false);
                }
                trees.add(deptMap);
            }
        }
        return trees;
    }
}
