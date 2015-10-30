package com.hummingbird.babyspace.services.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hummingbird.babyspace.entity.NotifyData;
import com.hummingbird.babyspace.entity.NotifyRecords;
import com.hummingbird.babyspace.mapper.NotifyRecordsMapper;
import com.hummingbird.babyspace.services.CustomNotifyService;
import com.hummingbird.babyspace.util.WChatNotifyUtil;
import com.hummingbird.common.exception.DataInvalidException;
import com.hummingbird.common.util.EmailUtil;
import com.hummingbird.common.util.JsonUtil;

@Service
public class CustomNotifyServiceImpl implements CustomNotifyService {
	@Autowired
	private NotifyRecordsMapper nrmDao;
	org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class,value="txManager")
	public void ToCustomers() {
		// TODO Auto-generated method stub
		//更新多条记录
		List<NotifyRecords> nrs = nrmDao.selectNeedNotifyRecords();
		System.out.println(nrs.size());
		String type =null;
		String content = null;
		String identity = null;
		NotifyData nd = null;
		for (NotifyRecords nr : nrs) {
			String jsonStr = nr.getNotifyData();
			try {
				if (jsonStr != null) {
					nd = JsonUtil.convertJson2Obj(jsonStr, NotifyData.class);
				}
			} catch (DataInvalidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();log.error(String.format("发送通知数据格式错误"), e);
			}
			 if(nd!=null){
			 type= nd.getNotifyType();
			 content= nd.getContent();
			 identity = nd.getIdentity();
			 }
			if (StringUtils.isNotBlank(identity) && StringUtils.isNotBlank(type) && StringUtils.isNotBlank(content)) {
				if (type.equals("EMAIL")) {
					try {
						EmailUtil.sendEmail(identity, "", content);
						nr.setInsertTime(new Date());
						nr.setStatus("OK#");
					} catch (EmailException e) {
						nr.setStatus("FLS");
						// TODO Auto-generated catch block
						log.error(String.format("邮件发送错误"), e);
					}
				}
				if (type.equals("SMS")) {

				}
				if (type.equals("WECHAT")) {
					String appId = null;
				    HashMap params = nd.getAttrs();
					if(params!=null){
						nr.setInsertTime(new Date());
						nr.setStatus("OK#");
						String wechatAppId = (String) params.get("wechat_appId");
						WChatNotifyUtil.notifyToCustomer(identity, content,  wechatAppId);
						}
						
					}else{
						nr.setStatus("FLS");
					}
				}
			nrmDao.updateByPrimaryKeySelective(nr);
			}

		}
	

}
