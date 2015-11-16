package com.hummingbird.payment.util;

import com.hummingbird.payment.entity.NotifyRecords;
import com.hummingbird.payment.exception.NONotifySenderException;

public class NotifyFactory {

	public static NotifySender getNotifySender(NotifyRecords not) throws NONotifySenderException {
		String notifyType = not.getNotifyType();
		switch (notifyType) {
		case "EMAIL":
			return new EmailNotifySender();
		case "SMS":
			return new SMSNotifySender();
		case "WECHAT":
			return new WECHATNotifySender();
		default:
			break;
		}
		throw new NONotifySenderException(notifyType);
	}

}
