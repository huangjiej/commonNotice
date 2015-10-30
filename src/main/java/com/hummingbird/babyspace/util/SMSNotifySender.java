package com.hummingbird.babyspace.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.hummingbird.common.exception.DataInvalidException;
import com.hummingbird.common.exception.RequestException;
import com.hummingbird.common.util.JsonUtil;
import com.hummingbird.common.util.Md5Util;
import com.hummingbird.common.util.PropertiesUtil;
import com.hummingbird.common.util.ValidateUtil;
import com.hummingbird.common.util.http.HttpRequester;
import com.hummingbird.common.vo.ResultModel;

public class SMSNotifySender implements NotifySender{
	static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(BabySpaceDeviceToCusUtil.class);
	static String url = new PropertiesUtil().getProperty("sms_url");
	static String user =new PropertiesUtil().getProperty("sms_user");
	static String password =new PropertiesUtil().getProperty("sms_password");
	@Override
	public ResultModel sendNotify(NotifyParameter parameter) {
		ResultModel rm =null;
		  try {
           Map<String,String> ma =new HashMap<String,String>();
           String identity = parameter.getNotifyData().getIdentity();
           String content=parameter.getNotifyData().getContent();
           ma.put("user", user);//未定
           ma.put("mobileNum",identity);
           ma.put("content",content);
           String signature = Md5Util.Encrypt(ValidateUtil.sortbyValues(user,identity,content,password));
           ma.put("signature",signature);
           HttpRequester hr = new HttpRequester();
           String postStr =null;
           try {
   			 postStr= JsonUtil.convert2Json(ma);
   		   } catch (DataInvalidException e) {
   			// TODO Auto-generated catch block
   			 e.printStackTrace();
   		   }
           if(log.isDebugEnabled()){
       	      log.debug("开始发送短信"+ma);
           }
           String result  = hr.sendPost("http://115.29.211.3:8094/SMSSenderGateway/sms/send", ma);
           if(log.isDebugEnabled()){
       	     log.debug("发送短信成功"+ma);
           }
			 rm = JsonUtil.convertJson2Obj(result, ResultModel.class);
          }catch (Exception e) {
				log.error("发送短信收到的回馈信息格式错误"+e);
				rm.setBaseErrorCode(310100);
				rm.setErrmsg("发送短信失败");
		  }
		  return rm;
	}
	public static void main(String[] args) {
	    Map<String,String> ma =new HashMap<String,String>();
     /*   String identity = parameter.getNotifyData().getIdentity();
        String content=parameter.getNotifyData().getContent();*/
        ma.put("user",user);//未定
        ma.put("mobileNum","15739577527");
        ma.put("content", "nihao");
        String signature = Md5Util.Encrypt(ValidateUtil.sortbyValues("imap","15739577527","nihao","imapsmsgd"));
        ma.put("signature",signature);
        HttpRequester hr = new HttpRequester();
        String postStr =null;
        try {
			 postStr= JsonUtil.convert2Json(ma);
		} catch (DataInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String result = null;
		try {
			result = hr.postRequest("http://115.29.211.3:8094/SMSSenderGateway/sms/send", postStr);
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(result);
	}

}
