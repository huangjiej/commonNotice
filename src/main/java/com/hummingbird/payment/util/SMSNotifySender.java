package com.hummingbird.payment.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hummingbird.common.exception.DataInvalidException;
import com.hummingbird.common.exception.RequestException;
import com.hummingbird.common.exception.ValidateException;
import com.hummingbird.common.util.JsonUtil;
import com.hummingbird.common.util.Md5Util;
import com.hummingbird.common.util.PropertiesUtil;
import com.hummingbird.common.util.ValidateUtil;
import com.hummingbird.common.util.http.HttpRequester;
import com.hummingbird.common.vo.ResultModel;

public class SMSNotifySender implements NotifySender {
	static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(SMSNotifySender.class);
	static String url = new PropertiesUtil().getProperty("sms_url");
	static String user = new PropertiesUtil().getProperty("sms_user");
	static String password = new PropertiesUtil().getProperty("sms_password");

	/** 发送短信通知 */
	@Override
	public ResultModel sendNotify(NotifyParameter parameter) {
		if (log.isDebugEnabled()) {
			log.debug("短信发送中");
		}
		ResultModel rm = new ResultModel();
		if (StringUtils.isBlank(url) || StringUtils.isBlank(user) || StringUtils.isBlank(password)) {
			log.error("发送短信失败。传入参数有空");
			rm.mergeException(ValidateException.ERROR_PARAM_FORMAT_ERROR.cloneAndAppend(null, "短信发送失败，含空参数"));
			return rm;
		}
		try {
			
			Map<String, String> ma = new HashMap<String, String>();
			String identity = parameter.getNotifyData().getIdentity();
			String content = parameter.getNotifyData().getContent();
			ma.put("user", user);// 未定
			ma.put("mobileNum", identity);
			ma.put("content", content);
			String signature = Md5Util.Encrypt(ValidateUtil.sortbyValues(user, identity, content, password));
			ma.put("signature", signature);
			HttpRequester hr = new HttpRequester();
			String postStr = null;
			postStr = JsonUtil.convert2Json(ma);
			String result = hr.postRequest(url, postStr);
			rm = JsonUtil.convertJson2Obj(result, ResultModel.class);
			if (log.isDebugEnabled()) {
				log.debug("发送短信成功" + ma);
			}
		} catch (DataInvalidException e) {
			log.error("发送短信收到的回馈信息格式错误" + e);
			rm.mergeException(ValidateException.ERROR_PARAM_FORMAT_ERROR.cloneAndAppend(null, "发送短信收到的回馈信息格式错误"));
		} catch (RequestException e) {
			log.error("调用短信接口requst请求失败" + e);
			rm.mergeException(ValidateException.ERROR_PARAM_FORMAT_ERROR.cloneAndAppend(null, "调用短信接口requst请求失败"));
		}catch(Exception e){
			log.error("调用短信接口其他错误" + e);
			rm.mergeException(ValidateException.ERROR_PARAM_FORMAT_ERROR.cloneAndAppend(null, "调用短信接口请求失败"));
		}
		return rm;
	}
	
	  public static void main(String[] args) 
	  {
	  Map<String,String> ma =new HashMap<String,String>();

	  ma.put("user","imap");//未定
	  ma.put("mobileNum","15739577527");
	  ma.put("content", "nihao");
	  String signature =Md5Util.Encrypt(ValidateUtil.sortbyValues("imap","15739577527","nihao","imapsmsgd"));
	  ma.put("signature",signature);
	  HttpRequester hr = new HttpRequester(); 
	  String postStr =null;
	  try {
	  postStr=JsonUtil.convert2Json(ma); 
	  } catch (DataInvalidException e) { 
		  // TODO Auto-generated catch block
		  e.printStackTrace(); } 
	  String result = null;
	  try { result =
	  hr.postRequest("http://115.29.211.3:8094/SMSSenderGateway/sms/send",
	  postStr); ResultModel rm = JsonUtil.convertJson2Obj(result,
	  ResultModel.class); System.out.println(rm.getErrcode()+rm.getErrmsg()); }
	  catch (Exception e) { // TODO Auto-generated catch block
	  e.printStackTrace(); } System.out.println(result); }
	 
}
