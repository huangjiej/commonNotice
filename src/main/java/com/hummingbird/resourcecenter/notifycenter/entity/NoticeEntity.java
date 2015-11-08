package com.hummingbird.resourcecenter.notifycenter.entity;

import java.util.HashMap;

public class NoticeEntity {
	private String identity;
	private String notifyType;
	private String content;
	private HashMap attrs;

	public HashMap getAttrs() {
		return attrs;
	}

	public void setAttrs(HashMap attrs) {
		this.attrs = attrs;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
