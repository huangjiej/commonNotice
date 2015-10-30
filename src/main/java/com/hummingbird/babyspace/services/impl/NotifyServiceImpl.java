package com.hummingbird.babyspace.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hummingbird.babyspace.entity.NotifyData;
import com.hummingbird.babyspace.entity.NotifyRecords;
import com.hummingbird.babyspace.entity.RawNotifyRecords;
import com.hummingbird.babyspace.mapper.NotifyRecordsMapper;
import com.hummingbird.babyspace.mapper.RawNotifyRecordsMapper;
import com.hummingbird.babyspace.services.NotifyService;
import com.hummingbird.babyspace.util.Jedis.JedisPoolUtils;
import com.hummingbird.babyspace.vo.NotifyQueryResultBodyVO;
import com.hummingbird.babyspace.vo.UserVO;
import com.hummingbird.common.exception.DataInvalidException;
import com.hummingbird.common.util.DateUtil;
import com.hummingbird.common.util.JsonUtil;
import com.hummingbird.common.util.convertor.DateAdapter;

import redis.clients.jedis.Jedis;
@Service
public class NotifyServiceImpl implements NotifyService{
    @Autowired 
    private NotifyRecordsMapper nrmDao;
    @Autowired
    private RawNotifyRecordsMapper rnrDao;
    org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class,value="txManager")
	public Integer SendNoticeFactory(String type,List<UserVO> uvos,String appId,String jsonStr) {
		Integer notifyId=null;
		List<NotifyRecords> nrs = new ArrayList<NotifyRecords>(0);
		DateAdapter da = new DateAdapter();
		//向rawnotifyrecords插入记录
		if(log.isDebugEnabled()){
			log.debug("开始向rawnotifyrecords插入记录");
		}
		RawNotifyRecords rnr = new RawNotifyRecords();
		rnr.setAppId(appId);
		rnr.setData(jsonStr);
		rnr.setInsertTime(new Date());
		rnr.setStatus("OK#");
		rnrDao.insert(rnr);
		if(log.isDebugEnabled()){
			log.debug("向rawnotifyrecords插入记录结束");
		}
		notifyId = rnr.getId();
		for(UserVO uv : uvos){
			NotifyRecords  nr= new NotifyRecords();
			if(StringUtils.isNotBlank(appId))
			      nr.setAppId(appId);
			      nr.setInsertTime(new Date());     
			      nr.setStatus("CRT");
			      nr.setNotifyType(type);
				  HashMap hms = new HashMap<String,String>();
				  hms.put("attrs",uv.getAttrs());
				  hms.put("content",uv.getContent());
				  if(StringUtils.isNotBlank(type)){
				   hms.put("notifyType", type);
				  }else{
					  if(log.isDebugEnabled()){
						  log.debug(uv+"不处理发送通知发送类型为空");
					  }
					  return 0;
				  }
				  if(StringUtils.isNotBlank(uv.getIdentity())){
				   hms.put("identity",uv.getIdentity());
				  }else{
					  if(log.isDebugEnabled()){
						  log.debug(uv+"不处理发送通知发送类型为空");
					  }
					  return 0;
				  }
				  try {
						nr.setNotifyData(JsonUtil.convert2Json(hms));
				  } catch (DataInvalidException e) {
						// TODO Auto-generated catch block
						 log.error(String.format("notifyService map转化数据失败"),e);
				         return 0;
				  }
			nr.setNotifyId(notifyId);
			nrmDao.insert(nr);
			try {
				 String note =  JsonUtil.convert2Json(nr);
				 if(log.isDebugEnabled()){
					 log.debug("redis数据库连接中");
				 }
				 Jedis jedis = JedisPoolUtils.getJedis();
				 jedis.rpush("notice",note);  
				 JedisPoolUtils.returnRes(jedis);
				 if(log.isDebugEnabled()){
					 log.debug("redis数据库释放。存入"+note);
				 }
			} catch (DataInvalidException e) {
				// TODO Auto-generated catch block
				log.error(String.format("notifyService map转化数据失败"),e);
			    return 0;
			}
		}
		return notifyId;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class,value="txManager")
	public String NoticeQuery(Integer notifyId) {
		DateAdapter da = new DateAdapter();
		StringBuffer sb = new StringBuffer("[");
		List<NotifyRecords> nrs = nrmDao.selectByNotifyId(notifyId);
		NotifyQueryResultBodyVO nb =null;
		List<NotifyQueryResultBodyVO> nqrbs = new ArrayList<NotifyQueryResultBodyVO>(0);
		for(NotifyRecords nr : nrs){
			nb=new NotifyQueryResultBodyVO();
   		    NotifyData nd = null;
   		    if(StringUtils.isNotBlank(nr.getNotifyData())){
   		        try {
					nd = JsonUtil.convertJson2Obj(nr.getNotifyData(), NotifyData.class);
				} catch (DataInvalidException e) {
					// TODO Auto-generated catch block
					 log.error(String.format("notifyService 转化数据失败"),e);
				}
			    String identity= nd.getIdentity();
			    String notifyType = nd.getNotifyType();
			    String content = nd.getContent();
			    Map attrs = nd.getAttrs();
			    if(attrs!=null){
			    	nb.setAttrs(attrs);
			    }
			    if(StringUtils.isNotBlank(identity)) {
			    	nb.setIdentity(identity);}
			    if(StringUtils.isNotBlank(content)){
			    	nb.setContent(content);}
                if(StringUtils.isNotBlank(notifyType)){
                	nb.setNotifyType(notifyType);}
                if(notifyId>0){
			     nb.setNotifyId(notifyId.toString());}
                if(StringUtils.isNotBlank(nr.getStatus())){
		    	 nb.setStatus(nr.getStatus());}
                if(nr.getId()>0){
		    	 nb.setSubNotifyId(nr.getId().toString());
		    	}
                if(nr.getUpdateTime()!=null){
		    	 nb.setNotifyTime(DateUtil.format(nr.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
                }
               }
   		   nqrbs.add(nb);
	   }
		String result=null;
		try {
			 result = JsonUtil.convert2Json(nqrbs);
			 System.out.println(result);
		} catch (DataInvalidException e) {
			 log.error(String.format("notifyService result 转化数据失败"),e);
		}
		return result;
	}
}
