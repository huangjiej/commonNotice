package com.hummingbird.resourcecenter.notifycenter.mapper;

import java.util.List;

import com.hummingbird.resourcecenter.notifycenter.entity.NotifyRecords;

public interface NotifyRecordsMapper {
	/**
	 * 根据主键删除记录
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 保存记录,不管记录里面的属性是否为空
	 */
	int insert(NotifyRecords record);

	/**
	 * 保存属性不为空的记录
	 */
	int insertSelective(NotifyRecords record);

	/**
	 * 根据主键查询记录
	 */
	NotifyRecords selectByPrimaryKey(Integer id);

	/**
	 * 根据主键更新属性不为空的记录
	 */
	int updateByPrimaryKeySelective(NotifyRecords record);

	/**
	 * 根据主键更新记录
	 */
	int updateByPrimaryKeyWithBLOBs(NotifyRecords record);

	/**
	 * 根据主键更新记录
	 */
	int updateByPrimaryKey(NotifyRecords record);

	/**
	 * 根据notifyId更新
	 */
	List<NotifyRecords> selectByNotifyId(Integer notifyId);

	/***
	 * 定时发送未通知可户
	 **/

	List<NotifyRecords> selectNeedNotifyRecords();
}