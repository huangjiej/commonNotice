package com.hummingbird.resourcecenter.notifycenter.mapper;

import com.hummingbird.resourcecenter.notifycenter.entity.RawNotifyRecords;

public interface RawNotifyRecordsMapper {
	/**
	 * 根据主键删除记录
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 保存记录,不管记录里面的属性是否为空
	 */
	int insert(RawNotifyRecords record);

	/**
	 * 保存属性不为空的记录
	 */
	int insertSelective(RawNotifyRecords record);

	/**
	 * 根据主键查询记录
	 */
	RawNotifyRecords selectByPrimaryKey(Integer id);

	/**
	 * 根据主键更新属性不为空的记录
	 */
	int updateByPrimaryKeySelective(RawNotifyRecords record);

	/**
	 * 根据主键更新记录
	 */
	int updateByPrimaryKeyWithBLOBs(RawNotifyRecords record);

	/**
	 * 根据主键更新记录
	 */
	int updateByPrimaryKey(RawNotifyRecords record);
}