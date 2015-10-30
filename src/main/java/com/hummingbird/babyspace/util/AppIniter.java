/**
 * 
 */
package com.hummingbird.babyspace.util;

import java.util.List;
import java.util.Map;

import com.hummingbird.babyspace.entity.NotifyRecords;
import com.hummingbird.babyspace.mapper.NotifyRecordsMapper;
import com.hummingbird.babyspace.util.Jedis.JedisPoolUtils;
import com.hummingbird.common.exception.DataInvalidException;
import com.hummingbird.common.util.JsonUtil;
import com.hummingbird.common.util.SpringBeanUtil;

import redis.clients.jedis.Jedis;

/**
 * 启动
 * @author fn
 *
 */
public class AppIniter implements com.hummingbird.common.ext.AppIniter {
	/* (non-Javadoc)
	 * @see com.hummingbird.common.ext.AppIniter#init(java.util.Map)
	 */
	org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());
	
	@Override
	public void init(Map param) {
		if(log.isDebugEnabled()){
			log.debug("appiniter启动开始");
		}
		Jedis jedis = JedisPoolUtils.getJedis();
		List<NotifyRecords> nrs = this.needToNote();
		for(NotifyRecords nr :nrs){
			try {
				String notice =  JsonUtil.convert2Json(nr);
				jedis.rpush("notice",notice);
			} catch (DataInvalidException e) {
				log.error(nr+"notice转化json出错,不进行处理");
			}
		}
		JedisPoolUtils.returnRes(jedis);
		NoticeThread nt  = new NoticeThread();
		Thread t = new Thread(nt);
		t.setDaemon(true);
		t.start();
		if(log.isDebugEnabled()){
			log.debug("appiniter启动结束");
		}
	}
	public List<NotifyRecords> needToNote(){
		NotifyRecordsMapper nrmDao = SpringBeanUtil.getInstance().getBean(NotifyRecordsMapper.class);
		List<NotifyRecords> nrs = nrmDao.selectNeedNotifyRecords();
		return nrs;
	}
}
