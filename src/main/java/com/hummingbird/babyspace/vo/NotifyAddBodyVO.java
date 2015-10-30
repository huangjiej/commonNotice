package com.hummingbird.babyspace.vo;

import java.util.List;

import com.hummingbird.commonbiz.vo.AppBaseVO;
import com.hummingbird.commonbiz.vo.Decidable;

public class NotifyAddBodyVO extends AppBaseVO implements Decidable{
	private List<UserVO> users;
	private String notifyType;
	public String getNotifyType() {
		return notifyType;
	}
	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}
	public List<UserVO> getUsers() {
		return users;
	}
	public void setUsers(List<UserVO> users) {
		this.users = users;
	}
	@Override//
	public String toString() {
		return " NotifyAddBodyVO[users=" +users+", notifyType="+notifyType+"]";
	}
}
