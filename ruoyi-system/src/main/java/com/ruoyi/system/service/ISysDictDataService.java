package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysDictData;
import java.util.List;

/**
 * 字典数据 服务层
 * 
 * @author ruoyi
 * @date 2019-03-21
 */
public interface ISysDictDataService 
{
	/**
     * 查询字典数据信息
     * 
     * @param dictCode 字典数据ID
     * @return 字典数据信息
     */
	public SysDictData selectSysDictDataById(Long dictCode);
	
	/**
     * 查询字典数据列表
     * 
     * @param sysDictData 字典数据信息
     * @return 字典数据集合
     */
	public List<SysDictData> selectSysDictDataList(SysDictData sysDictData);
	
	/**
     * 新增字典数据
     * 
     * @param sysDictData 字典数据信息
     * @return 结果
     */
	public int insertSysDictData(SysDictData sysDictData);
	
	/**
     * 修改字典数据
     * 
     * @param sysDictData 字典数据信息
     * @return 结果
     */
	public int updateSysDictData(SysDictData sysDictData);
		
	/**
     * 删除字典数据信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSysDictDataByIds(String ids);
	
}
