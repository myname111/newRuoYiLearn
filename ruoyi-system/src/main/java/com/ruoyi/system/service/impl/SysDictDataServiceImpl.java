package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.common.support.Convert;

/**
 * 字典数据 服务层实现
 * 
 * @author ruoyi
 * @date 2019-03-21
 */
@Service
public class SysDictDataServiceImpl implements ISysDictDataService 
{
	@Autowired
	private SysDictDataMapper sysDictDataMapper;

	/**
     * 查询字典数据信息
     * 
     * @param dictCode 字典数据ID
     * @return 字典数据信息
     */
    @Override
	public SysDictData selectSysDictDataById(Long dictCode)
	{
	    return sysDictDataMapper.selectSysDictDataById(dictCode);
	}
	
	/**
     * 查询字典数据列表
     * 
     * @param sysDictData 字典数据信息
     * @return 字典数据集合
     */
	@Override
	public List<SysDictData> selectSysDictDataList(SysDictData sysDictData)
	{
	    return sysDictDataMapper.selectSysDictDataList(sysDictData);
	}
	
    /**
     * 新增字典数据
     * 
     * @param sysDictData 字典数据信息
     * @return 结果
     */
	@Override
	public int insertSysDictData(SysDictData sysDictData)
	{
	    return sysDictDataMapper.insertSysDictData(sysDictData);
	}
	
	/**
     * 修改字典数据
     * 
     * @param sysDictData 字典数据信息
     * @return 结果
     */
	@Override
	public int updateSysDictData(SysDictData sysDictData)
	{
	    return sysDictDataMapper.updateSysDictData(sysDictData);
	}

	/**
     * 删除字典数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSysDictDataByIds(String ids)
	{
		return sysDictDataMapper.deleteSysDictDataByIds(Convert.toStrArray(ids));
	}
	
}
