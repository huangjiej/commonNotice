package com.hummingbird.babyspace.util;

import com.hummingbird.babyspace.entity.NotifyRecords;

public class NotifyFactory{

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
