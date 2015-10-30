package com.hummingbird.babyspace.util;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hummingbird.common.vo.ResultModel;

public class WECHATNotifySender implements NotifySender{
	static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(BabySpaceDeviceToCusUtil.class);
	
	@Override
	public ResultModel sendNotify(NotifyParameter parameter){
		ResultModel rm = new ResultModel(0,"微信发送通知成功");
		try{
		String appId = null;
		Map maps = parameter.getNotifyData().getAttrs();
		String identity = parameter.getNotifyData().getIdentity();
		String content = parameter.getNotifyData().getContent();
	    if(maps!=null){
	    	String wechatAppId = (String) maps.get("wechat_appId");
	    	if(StringUtils.isNotBlank(wechatAppId)){
	    		if(log.isDebugEnabled()){
					log.debug("微信通知发送中");
				}
	    	    rm  =  WChatNotifyUtil.notifyToCustomer(identity, content,  wechatAppId);
	    	   //微信通知方法成功 
	    	    if(log.isDebugEnabled()){
	    	    	log.debug("微信通知发送成功");
	    	    }
	    	 
	    	}else{
	    	   log.error("wechatAppId获取失败微信发送失败");	
	    	   rm.setBaseErrorCode(310100);
	    	   rm.setErrmsg("wechatAppId获取失败微信发送失败");
	    	 }
	    }else{
	    	log.error("属性参数传入错误");
	    	rm.setBaseErrorCode(310100);
	        rm.setErrmsg("属性参数传入错误");
	    }
		}catch(Exception e){
		  log.error("微信通知出错");
		  rm.setBaseErrorCode(310100);
	      rm.setErrmsg("微信通知出错");
		}
		return rm;
	}

}
