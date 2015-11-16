package com.hummingbird.payment.vo;

import com.hummingbird.commonbiz.vo.AppBaseVO;
import com.hummingbird.commonbiz.vo.Decidable;

public class NotifyQueryVO extends AppBaseVO implements Decidable {
	private NotifyQueryBodyVO body;

	public NotifyQueryBodyVO getBody() {
		return body;
	}

	public void setBody(NotifyQueryBodyVO body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return " NotifyQueryVO[body=" + body + ", app=" + app + "]";
	}
}