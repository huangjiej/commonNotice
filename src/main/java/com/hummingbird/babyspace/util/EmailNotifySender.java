package com.hummingbird.babyspace.util;

import org.apache.commons.mail.EmailException;

import com.hummingbird.babyspace.entity.NotifyData;
import com.hummingbird.common.exception.DataInvalidException;
import com.hummingbird.common.util.EmailUtil;
import com.hummingbird.common.vo.ResultModel;

public class EmailNotifySender implements NotifySender {

	static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(BabySpaceDeviceToCusUtil.class);
	
	@Override
	public ResultModel sendNotify(NotifyParameter parameter) {
		if(log.isDebugEnabled()){
			log.debug("邮件发送开始");
		}
		
		ResultModel rm = new ResultModel(0,"邮件发送成功");
		
		try {
			NotifyData notifyData = parameter.getNotifyData();
		    EmailUtil.sendEmail(notifyData.getIdentity(), "", notifyData.getContent());
		} catch (Exception e) {
			log.error("邮件发送出错",e);
			rm.mergeException(e);
			rm.setErrcode(310100);
		}
		return rm;
	}

}
