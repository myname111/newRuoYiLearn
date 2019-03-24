package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysDictType;
import java.util.List;

/**
 * 字典类型 服务层
 * 
 * @author ruoyi
 * @date 2019-03-21
 */
public interface ISysDictTypeService 
{
	/**
     * 查询字典类型信息
     * 
     * @param dictId 字典类型ID
     * @return 字典类型信息
     */
	public SysDictType selectSysDictTypeById(Long dictId);
	
	/**
     * 查询字典类型列表
     * 
     * @param sysDictType 字典类型信息
     * @return 字典类型集合
     */
	public List<SysDictType> selectSysDictTypeList(SysDictType sysDictType);
	
	/**
     * 新增字典类型
     * 
     * @param sysDictType 字典类型信息
     * @return 结果
     */
	public int insertSysDictType(SysDictType sysDictType);
	
	/**
     * 修改字典类型
     * 
     * @param sysDictType 字典类型信息
     * @return 结果
     */
	public int updateSysDictType(SysDictType sysDictType);
		
	/**
     * 删除字典类型信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSysDictTypeByIds(String ids);
	
}
