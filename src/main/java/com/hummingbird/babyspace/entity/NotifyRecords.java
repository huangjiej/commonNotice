package com.hummingbird.babyspace.entity;

import java.util.Date;

/**
 * 通知请求表
 */
public class NotifyRecords {
    /**
     * id
     */
    private Integer id;

    /**
     * app_id
     */
    private String appId;

    /**
     * 新增时间
     */
    private Date insertTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 状态,CRT待通知，OK#已通知，FLS通知失败
     */
    private String status;

    /**
     * 通知类型,EMAIL# 邮件,SMS### 短信,WECHAT 微信
     */
    private String notifyType;

    private Integer notifyId;
    /**通知回馈信息
     * */
    private String sendMsg;
    /**
     * 通知参数
     */
    private String notifyData;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    public String getSendMsg() {
		return sendMsg;
	}

	public void setSendMsg(String sendMsg) {
		this.sendMsg = sendMsg;
	}

	/**
     * @param id 
	 *            id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return app_id
     */
    public String getAppId() {
        return appId;
    }

    /**
     * @param appId 
	 *            app_id
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * @return 新增时间
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * @param insertTime 
	 *            新增时间
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * @return 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime 
	 *            更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return 状态,CRT待通知，OK#已通知，FLS通知失败
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            状态,CRT待通知，OK#已通知，FLS通知失败
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * @return 通知类型,EMAIL# 邮件,SMS### 短信,WECHAT 微信
     */
    public String getNotifyType() {
        return notifyType;
    }

    /**
     * @param notifyType 
	 *            通知类型,EMAIL# 邮件,SMS### 短信,WECHAT 微信
     */
    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType == null ? null : notifyType.trim();
    }

    public Integer getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(Integer notifyId) {
        this.notifyId = notifyId;
    }

    /**
     * @return 通知参数
     */
    public String getNotifyData() {
        return notifyData;
    }

    /**
     * @param notifyData 
	 *            通知参数
     */
    public void setNotifyData(String notifyData) {
        this.notifyData = notifyData == null ? null : notifyData.trim();
    }
}