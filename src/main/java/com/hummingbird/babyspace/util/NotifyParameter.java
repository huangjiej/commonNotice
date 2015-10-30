package com.hummingbird.babyspace.util;

import java.util.Date;

import com.hummingbird.babyspace.entity.NotifyData;
import com.hummingbird.babyspace.entity.NotifyRecords;
import com.hummingbird.common.exception.DataInvalidException;
import com.hummingbird.common.util.JsonUtil;

public class NotifyParameter {

	private NotifyRecords notifyrecords;

	public Integer getId() {
		return notifyrecords.getId();
	}

	public String getSendMsg() {
		return notifyrecords.getSendMsg();
	}

	public void setSendMsg(String sendMsg) {
		notifyrecords.setSendMsg(sendMsg);
	}

	public void setId(Integer id) {
		notifyrecords.setId(id);
	}

	public String getAppId() {
		return notifyrecords.getAppId();
	}

	public void setAppId(String appId) {
		notifyrecords.setAppId(appId);
	}

	public Date getInsertTime() {
		return notifyrecords.getInsertTime();
	}

	public void setInsertTime(Date insertTime) {
		notifyrecords.setInsertTime(insertTime);
	}

	public Date getUpdateTime() {
		return notifyrecords.getUpdateTime();
	}

	public void setUpdateTime(Date updateTime) {
		notifyrecords.setUpdateTime(updateTime);
	}

	public String getStatus() {
		return notifyrecords.getStatus();
	}

	public void setStatus(String status) {
		notifyrecords.setStatus(status);
	}

	public String getNotifyType() {
		return notifyrecords.getNotifyType();
	}

	public void setNotifyType(String notifyType) {
		notifyrecords.setNotifyType(notifyType);
	}

	public Integer getNotifyId() {
		return notifyrecords.getNotifyId();
	}

	public void setNotifyId(Integer notifyId) {
		notifyrecords.setNotifyId(notifyId);
	}

	public String toString() {
		return notifyrecords.toString();
	}

	public NotifyParameter(NotifyRecords not) {
		this.notifyrecords = not;
	}
	
	public NotifyData getNotifyData() throws DataInvalidException{
		String notifyData = notifyrecords.getNotifyData();
		NotifyData nd = JsonUtil.convertJson2Obj(notifyData, NotifyData.class);
		return nd;
	}

}
