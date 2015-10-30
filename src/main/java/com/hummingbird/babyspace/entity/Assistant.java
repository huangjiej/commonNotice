package com.hummingbird.babyspace.entity;

/**
 * 用户表
 */
public class Assistant {
    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 微信用户ID
     */
    private Integer wxUserid;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户手机
     */
    private String mobile;

    /**
     * 注册时间
     */
    private Integer regTime;

    /**
     * 注册IP
     */
    private Long regIp;

    /**
     * 最后登录时间
     */
    private Integer lastLoginTime;

    /**
     * 最后登录IP
     */
    private Long lastLoginIp;

    /**
     * 更新时间
     */
    private Integer updateTime;

    /**
     * 用户状态
     */
    private Byte status;

    /**
     * 会话令牌
     */
    private String token;

    /**
     * 其他参数，以json数据保存
     */
    private String params;

    /**
     * 家庭所有城市
     */
    private String city;

    /**
     * 家庭所在地区
     */
    private String district;

    /**
     * @return 用户ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            用户ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username 
	 *            用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password 
	 *            密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * @return 微信用户ID
     */
    public Integer getWxUserid() {
        return wxUserid;
    }

    /**
     * @param wxUserid 
	 *            微信用户ID
     */
    public void setWxUserid(Integer wxUserid) {
        this.wxUserid = wxUserid;
    }

    /**
     * @return 用户邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email 
	 *            用户邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * @return 用户手机
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile 
	 *            用户手机
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * @return 注册时间
     */
    public Integer getRegTime() {
        return regTime;
    }

    /**
     * @param regTime 
	 *            注册时间
     */
    public void setRegTime(Integer regTime) {
        this.regTime = regTime;
    }

    /**
     * @return 注册IP
     */
    public Long getRegIp() {
        return regIp;
    }

    /**
     * @param regIp 
	 *            注册IP
     */
    public void setRegIp(Long regIp) {
        this.regIp = regIp;
    }

    /**
     * @return 最后登录时间
     */
    public Integer getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * @param lastLoginTime 
	 *            最后登录时间
     */
    public void setLastLoginTime(Integer lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * @return 最后登录IP
     */
    public Long getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * @param lastLoginIp 
	 *            最后登录IP
     */
    public void setLastLoginIp(Long lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    /**
     * @return 更新时间
     */
    public Integer getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime 
	 *            更新时间
     */
    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return 用户状态
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            用户状态
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * @return 会话令牌
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token 
	 *            会话令牌
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * @return 其他参数，以json数据保存
     */
    public String getParams() {
        return params;
    }

    /**
     * @param params 
	 *            其他参数，以json数据保存
     */
    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    /**
     * @return 家庭所有城市
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city 
	 *            家庭所有城市
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * @return 家庭所在地区
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district 
	 *            家庭所在地区
     */
    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }
}