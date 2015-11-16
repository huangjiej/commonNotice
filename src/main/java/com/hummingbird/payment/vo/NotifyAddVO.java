package com.hummingbird.payment.vo;

import com.hummingbird.commonbiz.vo.AppBaseVO;
import com.hummingbird.commonbiz.vo.Decidable;

public class NotifyAddVO extends AppBaseVO implements Decidable {
	private NotifyAddBodyVO body;

	public NotifyAddBodyVO getBody() {
		return body;
	}

	public void setBody(NotifyAddBodyVO body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return " NotifyAddVO[body=" + body + ", app=" + app + "]";
	}

}
