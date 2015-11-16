package com.hummingbird.payment.redisthread;

import java.util.Observable;

import com.hummingbird.common.exception.DataInvalidException;
import com.hummingbird.common.util.JsonUtil;
import com.hummingbird.common.vo.ResultModel;
import com.hummingbird.payment.entity.NotifyRecords;
import com.hummingbird.payment.util.BabySpaceDeviceToCusUtil;
import com.hummingbird.payment.util.JedisPoolUtils;

import redis.clients.jedis.Jedis;

public class NoticeThread extends Observable implements Runnable {
	org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());

	public void doBusiness() {
		if (true) {
			super.setChanged();
		}
		notifyObservers();
	}

	@Override
	public void run() {
		Boolean flag = true;
		Jedis jedis = JedisPoolUtils.getJedis();
		if (log.isDebugEnabled()) {
			log.debug("进入线程");
		}
		while (true) {
			try {
				Long notifysize = jedis.llen("notice");
				if (notifysize == 0) {
					if (log.isDebugEnabled()) {
						log.debug("没有待通知的数据，停30秒");
					}
					Thread.currentThread().sleep(30000);
				} else {
					for (int i = 0; i < notifysize; i++) {
						if (log.isDebugEnabled()) {
							log.debug("准备发送通知");
						}
						Thread.sleep(30000);
						String notice = jedis.lpop("notice");
						sendNotice(notice);
						if (log.isDebugEnabled()) {
							log.debug("发送通知执行完成" + notice);
						}
					}

				}
			} catch (Exception e) {
				doBusiness();
				log.error("线程发送出错");
				JedisPoolUtils.returnRes(jedis);
				break;
			}
		}
		JedisPoolUtils.returnRes(jedis);
		if (log.isDebugEnabled()) {
			log.debug("结束线程");
		}

	}

	/**
	 * 发送通知
	 * 
	 * @param notice
	 */
	private void sendNotice(String notice) {
		try {
			NotifyRecords not = JsonUtil.convertJson2Obj(notice, NotifyRecords.class);
			sendNotice(not);
		} catch (DataInvalidException e) {
			log.error(notice + "notice数据类型转化错误" + e);
		}
	}

	/**
	 * 发送通知
	 * 
	 * @param nf
	 * @param notice
	 * @throws DataInvalidException
	 */
	private void sendNotice(NotifyRecords not) throws DataInvalidException {
		BabySpaceDeviceToCusUtil.sendDevice(not);
	}
}
