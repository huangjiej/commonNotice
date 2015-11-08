package com.hummingbird.resourcecenter.notifycenter.vo;

import java.util.HashMap;
import java.util.Map;

import com.hummingbird.commonbiz.vo.AppBaseVO;
import com.hummingbird.commonbiz.vo.Decidable;

//("\"identity\":"+identity+",\"notifyType\":"+notifyType+",\"content\":"+content+",\"status\":"+nr.getStatus()+",\"notifyTime\":"+nr.getUpdateTime()+",\"notifyId\":"+nr.getNotifyId()+",\"subNotifyId\":"+nr.getId()+"},");
public class NotifyQueryResultBodyVO {
	private String identity;
	private String notifyType;
	private String content;
	private String status;
	private String notifyTime;
	private String notifyId;
	private String subNotifyId;
	private Map attrs;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}

	public String getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}

	public String getSubNotifyId() {
		return subNotifyId;
	}

	public void setSubNotifyId(String subNotifyId) {
		this.subNotifyId = subNotifyId;
	}

	public Map getAttrs() {
		return attrs;
	}

	public void setAttrs(Map attrs2) {
		this.attrs = attrs2;
	}

}
