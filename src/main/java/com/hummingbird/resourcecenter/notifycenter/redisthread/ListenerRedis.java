package com.hummingbird.resourcecenter.notifycenter.redisthread;

import java.util.Observable;
import java.util.Observer;

public class ListenerRedis implements Observer {
	static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(ListenerRedis.class);

	@Override
	public void update(Observable o, Object arg) {
		if (log.isDebugEnabled()) {
			log.debug("NoticeThread死机");
		}
		NoticeThread nth = new NoticeThread();
		nth.addObserver(this);
		new Thread(nth).start();
		if (log.isDebugEnabled()) {
			log.debug("NoticeThread重启");
		}
	}

}
