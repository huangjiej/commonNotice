package com.hummingbird.babyspace.entity;

import java.util.HashMap;

public class NotifyData {
     private String notifyType;
     private String identity;
    /* private String notifyTime;*/
     private String content;
     private HashMap<String ,String> attrs = new HashMap<String,String>(0);
	 public String getNotifyType() {
		return notifyType;
	 }
	 public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	 }
	 public String getIdentity() {
		return identity;
	 }
	 public void setIdentity(String identity) {
		this.identity = identity;
	 }
/*	 public String getNotifyTime() {
		return notifyTime;
	 }
	 public void setNotifyTime(String notifyTime) {
			this.notifyTime = notifyTime;
     }*/
	 public String getContent() {
			return content;
	 }
	public void setContent(String content) {
			this.content = content;
	}
	public HashMap<String, String> getAttrs() {
			return attrs;
	}
	public void setAttrs(HashMap<String, String> attrs) {
			this.attrs = attrs;
	}
	@Override
	public String toString() {
		return "NotifyData [notifyType=" + notifyType + ", identity=" + identity + ", content=" + content + ", attrs="
				+ attrs + "]";
	}
     
}
