package com.hummingbird.payment.util;

import com.hummingbird.common.vo.ResultModel;

public interface NotifySender {

	ResultModel sendNotify(NotifyParameter parameter);

}
