package com.hummingbird.babyspace.util;

import com.hummingbird.common.exception.BusinessException;

public class NONotifySenderException extends BusinessException {

	public NONotifySenderException(String notifyType) {
		super(notifyType+" 对应的通知器不存在");
	}

}
