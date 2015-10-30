package com.hummingbird.babyspace.entity;

public class WeChat {
    /**
     * appid
     */
    private String appid;

    /**
     * app密钥
     */
    private String appsecret;

    /**
     * token
     */
    private String token;

    /**
     * 加密串
     */
    private String encodingaeskey;

    /**
     * 公众号名称
     */
    private String name;

    /**
     * 获取到的凭证
     */
    private String accessToken;

    /**
     * token有效时间，单位：秒 
     */
    private Integer expiresIn;

    /**
     * access_token获取时间
     */
    private Integer tokenCtime;

    /**
     * 官网域名
     */
    private String baseUrl;

    /**
     * @return appid
     */
    public String getAppid() {
        return appid;
    }

    /**
     * @param appid 
	 *            appid
     */
    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    /**
     * @return app密钥
     */
    public String getAppsecret() {
        return appsecret;
    }

    /**
     * @param appsecret 
	 *            app密钥
     */
    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret == null ? null : appsecret.trim();
    }

    /**
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token 
	 *            token
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * @return 加密串
     */
    public String getEncodingaeskey() {
        return encodingaeskey;
    }

    /**
     * @param encodingaeskey 
	 *            加密串
     */
    public void setEncodingaeskey(String encodingaeskey) {
        this.encodingaeskey = encodingaeskey == null ? null : encodingaeskey.trim();
    }

    /**
     * @return 公众号名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            公众号名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return 获取到的凭证
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken 
	 *            获取到的凭证
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    /**
     * @return token有效时间，单位：秒 
     */
    public Integer getExpiresIn() {
        return expiresIn;
    }

    /**
     * @param expiresIn 
	 *            token有效时间，单位：秒 
     */
    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    /**
     * @return access_token获取时间
     */
    public Integer getTokenCtime() {
        return tokenCtime;
    }

    /**
     * @param tokenCtime 
	 *            access_token获取时间
     */
    public void setTokenCtime(Integer tokenCtime) {
        this.tokenCtime = tokenCtime;
    }

    /**
     * @return 官网域名
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * @param baseUrl 
	 *            官网域名
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl == null ? null : baseUrl.trim();
    }
}