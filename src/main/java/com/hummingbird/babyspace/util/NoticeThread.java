package com.hummingbird.babyspace.util;

import com.hummingbird.babyspace.entity.NotifyRecords;
import com.hummingbird.babyspace.util.Jedis.JedisPoolUtils;
import com.hummingbird.common.exception.DataInvalidException;
import com.hummingbird.common.util.JsonUtil;

import redis.clients.jedis.Jedis;

public class NoticeThread implements Runnable{
	org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());	
	@Override
	public void run() {
		Jedis jedis =	 JedisPoolUtils.getJedis();
	  while (true){
		      if(log.isDebugEnabled()){
		    	  log.debug("通知线程启动。。。。。。。获取队列的待通知数据");
		      }  
	          Long notifysize = jedis.llen("notice");
			  if(notifysize==0){
					  if(log.isDebugEnabled()){
				    	  log.debug("没有待通知的数据，停30秒");
				      }
					  try {
						Thread.currentThread().sleep(30000);
					   } catch (InterruptedException e) {
						log.error("暂停出错",e);
						JedisPoolUtils.returnRes(jedis);  
						break;
					  }
				  }else{
					  for(int i=0;i<notifysize;i++){
						  String notice = jedis.lpop("notice");
						  if(log.isDebugEnabled()){
							  log.debug("准备发送通知"+notice);
						  }
						  sendNotice(notice);  
					  }
					  
				  }
	  	  }
		    JedisPoolUtils.returnRes(jedis);  
		}


	/**
	 * 发送通知
	 * @param notice
	 */
	private void sendNotice(String notice) {
		try {
			  NotifyRecords not = JsonUtil.convertJson2Obj(notice, NotifyRecords.class);
			  sendNotice(not);
		  } catch (DataInvalidException e) {
			  log.error(notice+"notice数据类型转化错误"+e);		 
		  }
	}
	/**
	 * 发送通知
	 * @param nf 
	 * @param notice
	 * @throws DataInvalidException 
	 */
	private void sendNotice(NotifyRecords not) throws DataInvalidException {
		BabySpaceDeviceToCusUtil.sendDevice(not);
	}
}
