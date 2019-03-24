package com.ruoyi.system.domain;

import com.ruoyi.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysDept extends BaseEntity{
    private static final long serialVersionUID = 1L;
    private Long deptId;//部门id
    private Long parentId;//父部门id
    private String deptName;//部门名称
    private String ancestors;//组级列表
    private String orderNum;//显示顺序
    private String leader;//负责人
    private String phone;//手机号
    private String email;//邮箱
    private String status;//部门状态:0正常 1停用
    private String delFlag;//删除标志 0表示存在,2表示删除
    private String parentName;//父部门名称

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("deptId",getDeptId())
                .append("parentId",getParentId())
                .append("ancestors",getAncestors())
                .append("deptName",getDeptName())
                .append("orderNum",getOrderNum())
                .append("leader",getLeader())
                .append("phone",getPhone())
                .append("email",getEmail())
                .append("status",getStatus())
                .append("delFlag",getDelFlag())
                .append("createBy",getCreateBy())
                .append("createTime",getCreateTime())
                .append("updateBy",getUpdateBy())
                .append("updateTime",getUpdateTime())
                .toString();
    }
}
