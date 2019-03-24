package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysDictTypeMapper;
import com.ruoyi.system.domain.SysDictType;
import com.ruoyi.system.service.ISysDictTypeService;
import com.ruoyi.common.support.Convert;

/**
 * 字典类型 服务层实现
 * 
 * @author ruoyi
 * @date 2019-03-21
 */
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService 
{
	@Autowired
	private SysDictTypeMapper sysDictTypeMapper;

	/**
     * 查询字典类型信息
     * 
     * @param dictId 字典类型ID
     * @return 字典类型信息
     */
    @Override
	public SysDictType selectSysDictTypeById(Long dictId)
	{
	    return sysDictTypeMapper.selectSysDictTypeById(dictId);
	}
	
	/**
     * 查询字典类型列表
     * 
     * @param sysDictType 字典类型信息
     * @return 字典类型集合
     */
	@Override
	public List<SysDictType> selectSysDictTypeList(SysDictType sysDictType)
	{
	    return sysDictTypeMapper.selectSysDictTypeList(sysDictType);
	}
	
    /**
     * 新增字典类型
     * 
     * @param sysDictType 字典类型信息
     * @return 结果
     */
	@Override
	public int insertSysDictType(SysDictType sysDictType)
	{
	    return sysDictTypeMapper.insertSysDictType(sysDictType);
	}
	
	/**
     * 修改字典类型
     * 
     * @param sysDictType 字典类型信息
     * @return 结果
     */
	@Override
	public int updateSysDictType(SysDictType sysDictType)
	{
	    return sysDictTypeMapper.updateSysDictType(sysDictType);
	}

	/**
     * 删除字典类型对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSysDictTypeByIds(String ids)
	{
		return sysDictTypeMapper.deleteSysDictTypeByIds(Convert.toStrArray(ids));
	}
	
}
