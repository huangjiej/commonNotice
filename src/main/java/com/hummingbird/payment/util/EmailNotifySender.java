package com.hummingbird.payment.util;

import com.hummingbird.common.exception.ValidateException;
import com.hummingbird.common.util.EmailUtil;
import com.hummingbird.common.vo.ResultModel;
import com.hummingbird.payment.entity.NotifyData;

public class EmailNotifySender implements NotifySender {

	static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(EmailNotifySender.class);

	/***
	 * 发送邮件
	 */
	@Override
	public ResultModel sendNotify(NotifyParameter parameter) {
		if (log.isDebugEnabled()) {
			log.debug("邮件发送开始");
		}
		ResultModel rm = new ResultModel(0, "邮件发送成功");
		try {
			NotifyData notifyData = parameter.getNotifyData();
			if (notifyData != null && notifyData.getContent() != null) {
				EmailUtil.sendEmail(notifyData.getIdentity(), "", notifyData.getContent());
			} else {
				rm.mergeException(
						ValidateException.ERROR_PARAM_FORMAT_ERROR.cloneAndAppend(null, "邮件发送出错:notifyData参数为空"));
			}
		} catch (Exception e) {
			log.error("邮件发送出错", e);
			rm.mergeException(ValidateException.ERROR_PARAM_FORMAT_ERROR.cloneAndAppend(null, "邮件发送出错"));
		}
		return rm;
	}

}
