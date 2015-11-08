package com.hummingbird.resourcecenter.notifycenter.exception;

import com.hummingbird.common.exception.BusinessException;

/*
 *未找到通知器异常 
 * **/
public class NONotifySenderException extends BusinessException {

	public NONotifySenderException(String notifyType) {
		super(notifyType + " 对应的通知器不存在");
	}

}
