package com.hummingbird.resourcecenter.notifycenter.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hummingbird.common.exception.DataInvalidException;
import com.hummingbird.common.exception.RequestException;
import com.hummingbird.common.exception.ValidateException;
import com.hummingbird.common.util.DateUtil;
import com.hummingbird.common.util.JsonUtil;
import com.hummingbird.common.util.SpringBeanUtil;
import com.hummingbird.common.util.http.HttpRequester;
import com.hummingbird.common.vo.ResultModel;
import com.hummingbird.commonbiz.vo.AppVO;
import com.hummingbird.resourcecenter.notifycenter.entity.WeChat;
import com.hummingbird.resourcecenter.notifycenter.mapper.WeChatMapper;

/*通知类*/
public class WChatNotifyUtil {
	/**
	 * 
	 */
	static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(WChatNotifyUtil.class);

	public static ResultModel notifyToCustomer(String openid, String textMsg, String wxappid) {
		WeChatMapper wm = SpringBeanUtil.getInstance().getBean(WeChatMapper.class);
		ResultModel rm = null;
		if (log.isDebugEnabled()) {
			log.debug(String.format("微信通知"));
		}
		// 获取对应的应用openid
		WeChat wc = wm.selectByPrimaryKey(wxappid);
		String url = wc.getBaseUrl();
		if (wc != null && url != null && openid != null) {
			return WxUrlNotice(openid, textMsg, url);
		} else {
			rm.mergeException(
					ValidateException.ERROR_PARAM_FORMAT_ERROR.cloneAndAppend(null, "发送微信 WxUrlNotice的方法不执行：参数有空"));
			return rm;
		}
	}

	/* 调用微信接口 */
	public static ResultModel WxUrlNotice(String openid, String textMsg, String url) {
		ResultModel rm = new ResultModel();
		if (log.isDebugEnabled()) {
			log.debug(String.format("微信通知"));
		}
		if (StringUtils.isBlank(openid) || StringUtils.isBlank(textMsg) || StringUtils.isBlank(url)) {
			log.error("WxUrlNotice无法调用:参数含空");
			rm.mergeException(ValidateException.ERROR_PARAM_FORMAT_ERROR.cloneAndAppend(null, "WxUrlNotice无法调用:参数含空"));
			return rm;
		}
		try {
			// 获取对应的微信openid
			Map body = new HashMap<>();
			body.put("touser", openid);
			body.put("msgtype", "text");
			Map<String, String> textmap = new HashMap<>();
			body.put("text", textmap);
			textmap.put("content", textMsg);
			Map map = new HashMap();
			AppVO app = new AppVO();
			app.setAppId("notifyCenter");
			app.setTimeStamp(DateUtil.formatToday("yyyyMMddHHmmss"));
			app.setNonce(String.valueOf((int) (Math.random() * 10000)));
			app.setSignature(null);
			map.put("app", app);
			map.put("body", body);
			String notifyjson = JsonUtil.convert2Json(map);
			HttpRequester httpreq = new HttpRequester();// 同步URL，向该URL发起同步请求；

			String notifyresultstr = httpreq.postRequest(url, notifyjson);
			if (StringUtils.isNotBlank(notifyresultstr)) {
				rm = JsonUtil.convertJson2Obj(notifyresultstr, ResultModel.class);
			} else {
				if (log.isDebugEnabled()) {
					log.debug(String.format("微信发送通知失败：发送通知后收不到反馈"));
				}
				rm.mergeException(
						ValidateException.ERROR_PARAM_FORMAT_ERROR.cloneAndAppend(null, "微信发送通知失败：发送通知后收不到反馈"));
			}

		} catch (DataInvalidException e) {
			log.error(String.format("微信通知参数转化失败"), e);
			rm.mergeException(
					ValidateException.ERROR_PARAM_FORMAT_ERROR.cloneAndAppend(null, "insertRecordsIntoDatabase方法返回空"));
		} catch (RequestException e) {
			log.error(String.format("微信通知参数httprequest发送请求失败"), e);
			rm.mergeException(
					ValidateException.ERROR_PARAM_FORMAT_ERROR.cloneAndAppend(null, "微信通知参数httprequest发送请求失败"));
		}
		return rm;
	}
}
