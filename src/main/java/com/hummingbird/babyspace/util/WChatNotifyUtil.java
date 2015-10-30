package com.hummingbird.babyspace.util;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hummingbird.babyspace.entity.WeChat;
import com.hummingbird.babyspace.mapper.WeChatMapper;
import com.hummingbird.common.util.DateUtil;
import com.hummingbird.common.util.JsonUtil;
import com.hummingbird.common.util.SpringBeanUtil;
import com.hummingbird.common.util.http.HttpRequester;
import com.hummingbird.common.vo.ResultModel;
import com.hummingbird.commonbiz.vo.AppVO;
/*通知类*/
public class WChatNotifyUtil {
	/**
	 * 
	 */
	public static ResultModel notifyToCustomer(String openid,String textMsg,String wxappid){
	    WeChatMapper wm =  SpringBeanUtil.getInstance().getBean(WeChatMapper.class);
		org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(WChatNotifyUtil.class);
		ResultModel rm = null;
		if (log.isDebugEnabled()) {
			log.debug(String.format("微信通知"));
		}
		try {		
			//获取对应的微信openid
			WeChat wc = wm.selectByPrimaryKey(wxappid);
			String url = wc.getBaseUrl();
			return WxUrlNotice(openid,textMsg,url);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rm;
     }

	public static ResultModel WxUrlNotice(String openid,String textMsg,String url){
		org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(WChatNotifyUtil.class);
		ResultModel rm = null;
		System.out.println(url);
		if (log.isDebugEnabled()) {
			log.debug(String.format("微信通知"));
		}
		try {		
			//获取对应的微信openid
			Map body = new HashMap<>();
			body.put("touser",openid);
			body.put("msgtype", "text");
			Map<String,String> textmap = new HashMap<>();
			body.put("text", textmap);
			textmap.put("content", textMsg);
			Map map = new HashMap();
			AppVO app = new AppVO();
			app.setAppId("babyspace");
			app.setTimeStamp(DateUtil.formatToday("yyyyMMddHHmmss"));
			app.setNonce(String.valueOf((int)(Math.random()*10000)));
			app.setSignature(null);
			map.put("app", app);
			map.put("body", body);
			String notifyjson = JsonUtil.convert2Json(map);
			HttpRequester httpreq = new HttpRequester();// 同步URL，向该URL发起同步请求；
			String notifyresultstr = httpreq.postRequest(url, notifyjson);
			if(StringUtils.isNotBlank(notifyresultstr)){
			   rm = JsonUtil.convertJson2Obj(notifyresultstr, ResultModel.class);
			}	
			else{
				if (log.isDebugEnabled()) {
					log.debug(String.format("通知服务访问失败,或不通"));
				}
			}
		} catch (Exception e) {
			log.error(String.format("通知业务员跟进出错"),e);
		}
		return rm;
     }
}
