package com.hummingbird.babyspace.entity;

import java.util.Date;

/**
 * 通知原始请求表
 */
public class RawNotifyRecords {
    /**
     * id
     */
    private Integer id;

    /**
     * 新增时间
     */
    private Date insertTime;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 状态,OK#接受请求,FLS拒绝请求
     */
    private String status;

    /**
     * 数据
     */
    private String data;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            id
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return 应用id
     */
    public String getAppId() {
        return appId;
    }

    /**
     * @param appId 
	 *            应用id
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * @return 状态,OK#接受请求,FLS拒绝请求
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            状态,OK#接受请求,FLS拒绝请求
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * @return 数据
     */
    public String getData() {
        return data;
    }

    /**
     * @param data 
	 *            数据
     */
    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }
}