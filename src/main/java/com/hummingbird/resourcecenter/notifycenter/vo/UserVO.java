package com.hummingbird.resourcecenter.notifycenter.vo;

import java.util.HashMap;
import java.util.Map;

import com.hummingbird.commonbiz.vo.AppBaseVO;
import com.hummingbird.commonbiz.vo.Decidable;

public class UserVO extends AppBaseVO implements Decidable {
	private String identity;
	private String content;
	private HashMap attrs = new HashMap();

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Map getAttrs() {
		return attrs;
	}

	public void setAttrs(HashMap attrs) {
		this.attrs = attrs;
	}

	@Override
	public String toString() {
		return " UserVO[identity=" + identity + ", attrs= " + attrs + ", content=" + content + "]";
	}
}
