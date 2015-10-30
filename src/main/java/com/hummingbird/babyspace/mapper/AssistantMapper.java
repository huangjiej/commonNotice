package com.hummingbird.babyspace.mapper;

import com.hummingbird.babyspace.entity.Assistant;

public interface AssistantMapper {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * 
     * **/
    int selectByAssistantId(Integer assistantId);
    
    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(Assistant record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(Assistant record);

    /**
     * 根据主键查询记录
     */
    Assistant selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(Assistant record);
    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(Assistant record);

	/**
	 * 根据业务员id查询该业务员的微信openid
	 * @param assistantId
	 * @return
	 */
	String selectOpenIdbyAssistantId(Integer assistantId);
}