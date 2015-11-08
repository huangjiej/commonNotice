package com.hummingbird.resourcecenter.notifycenter.mapper;

import com.hummingbird.resourcecenter.notifycenter.entity.WeChat;

public interface WeChatMapper {
	/**
	 * 根据主键删除记录
	 */
	int deleteByPrimaryKey(String appid);

	/**
	 * 保存记录,不管记录里面的属性是否为空
	 */
	int insert(WeChat record);

	/**
	 * 保存属性不为空的记录
	 */
	int insertSelective(WeChat record);

	/**
	 * 根据主键查询记录
	 */
	WeChat selectByPrimaryKey(String appid);

	/**
	 * 根据主键更新属性不为空的记录
	 */
	int updateByPrimaryKeySelective(WeChat record);

	/**
	 * 根据主键更新记录
	 */
	int updateByPrimaryKey(WeChat record);
}