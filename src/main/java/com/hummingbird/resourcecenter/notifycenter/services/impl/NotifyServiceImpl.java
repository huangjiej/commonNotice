package com.hummingbird.resourcecenter.notifycenter.services.impl;

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

import com.hummingbird.common.exception.DataInvalidException;
import com.hummingbird.common.util.DateUtil;
import com.hummingbird.common.util.JsonUtil;
import com.hummingbird.common.util.convertor.DateAdapter;
import com.hummingbird.resourcecenter.notifycenter.entity.NotifyData;
import com.hummingbird.resourcecenter.notifycenter.entity.NotifyRecords;
import com.hummingbird.resourcecenter.notifycenter.entity.RawNotifyRecords;
import com.hummingbird.resourcecenter.notifycenter.mapper.NotifyRecordsMapper;
import com.hummingbird.resourcecenter.notifycenter.mapper.RawNotifyRecordsMapper;
import com.hummingbird.resourcecenter.notifycenter.services.NotifyService;
import com.hummingbird.resourcecenter.notifycenter.util.JedisPoolUtils;
import com.hummingbird.resourcecenter.notifycenter.vo.NotifyQueryResultBodyVO;
import com.hummingbird.resourcecenter.notifycenter.vo.UserVO;

import redis.clients.jedis.Jedis;

@Service
public class NotifyServiceImpl implements NotifyService {
	@Autowired
	private NotifyRecordsMapper nrmDao;
	@Autowired
	private RawNotifyRecordsMapper rnrDao;
	org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());

	/*
	 * 插入数据到rawnotifyrecords和notifyRecords表中
	 **/
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, value = "txManager")
	public Integer insertRecordsIntoDatabase(String type, List<UserVO> uvos, String appId, String jsonStr) {
		Integer notifyId = null;
		List<NotifyRecords> nrs = new ArrayList<NotifyRecords>(0);
		DateAdapter da = new DateAdapter();
		// 向rawnotifyrecords插入记录
		if (log.isDebugEnabled()) {
			log.debug("开始向rawnotifyrecords插入记录");
		}
		if (StringUtils.isBlank(jsonStr) || StringUtils.isBlank(appId) || StringUtils.isBlank(type)) {
			log.error("insertRecordsIntoDatabase方法调用失败，有空参数");
			return 0;
		}
		RawNotifyRecords rnr = new RawNotifyRecords();
		rnr.setAppId(appId);
		rnr.setData(jsonStr);
		rnr.setInsertTime(new Date());
		rnr.setStatus("OK#");
		rnrDao.insert(rnr);
		if (log.isDebugEnabled()) {
			log.debug("向rawnotifyrecords插入记录结束");
		}
		notifyId = rnr.getId();
		Jedis jedis = JedisPoolUtils.getJedis();
		for (UserVO uv : uvos) {
			NotifyRecords nr = new NotifyRecords();
			if (StringUtils.isNotBlank(appId))
				nr.setAppId(appId);
			nr.setInsertTime(new Date());
			nr.setStatus("CRT");
			nr.setNotifyType(type);
			HashMap hms = new HashMap<String, String>();
			hms.put("attrs", uv.getAttrs());
			hms.put("content", uv.getContent());
			hms.put("notifyType", type);
			if (StringUtils.isNotBlank(uv.getIdentity())) {
				hms.put("identity", uv.getIdentity());
			} else {
				if (log.isDebugEnabled()) {
					log.debug(uv + "不处理发送通知:发送地址为空");
				}
				nr.setStatus("FLS");
				nr.setSendMsg("发送通知地址为空");
			}
			try {
				nr.setNotifyData(JsonUtil.convert2Json(hms));
			} catch (DataInvalidException e) {
				// TODO Auto-generated catch block
				log.error(String.format("插入数据NotifyRecords,"), e);
				nr.setStatus("FLS");
				nr.setSendMsg("map转NotifyData失败");
			}
			nr.setNotifyId(notifyId);
			nrmDao.insert(nr);
			try {
				if (nr.getStatus().equals("CRT")) {
					String note = JsonUtil.convert2Json(nr);
					jedis.rpush("notice", note);
				}
			} catch (DataInvalidException e) {
				log.error(String.format("存入redis时对象转字符串出错"), e);
			}
		}
		JedisPoolUtils.returnRes(jedis);
		return notifyId;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, value = "txManager")
	public String NoticeQuery(Integer notifyId) {
		if (log.isDebugEnabled()) {
			log.debug("进入NoticeQuery 的service方法中");
		}
		DateAdapter da = new DateAdapter();
		List<NotifyRecords> nrs = nrmDao.selectByNotifyId(notifyId);
		NotifyQueryResultBodyVO nb = null;
		List<NotifyQueryResultBodyVO> nqrbs = null;
		if (nrs == null) {
			log.error("NoticeQuery:" + notifyId + "对应的记录不存在");
			return null;
		}
		for (NotifyRecords nr : nrs) {
			nb = new NotifyQueryResultBodyVO();
			NotifyData nd = null;
			if (StringUtils.isNotBlank(nr.getNotifyData())) {
				try {
					nd = JsonUtil.convertJson2Obj(nr.getNotifyData(), NotifyData.class);
				} catch (DataInvalidException e) {
					log.error(String.format("notifyService 转化数据失败"), e);
				}
				String identity = nd.getIdentity();
				String notifyType = nd.getNotifyType();
				String content = nd.getContent();
				Map attrs = nd.getAttrs();
				if (attrs != null) {
					nb.setAttrs(attrs);
				}
				if (StringUtils.isNotBlank(identity)) {
					nb.setIdentity(identity);
				}
				if (StringUtils.isNotBlank(content)) {
					nb.setContent(content);
				}
				if (StringUtils.isNotBlank(notifyType)) {
					nb.setNotifyType(notifyType);
				}
				if (notifyId > 0) {
					nb.setNotifyId(notifyId.toString());
				}
				if (StringUtils.isNotBlank(nr.getStatus())) {
					nb.setStatus(nr.getStatus());
				}
				if (nr.getId() > 0) {
					nb.setSubNotifyId(nr.getId().toString());
				}
				if (nr.getUpdateTime() != null) {
					nb.setNotifyTime(DateUtil.format(nr.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
				}
			}
			nqrbs.add(nb);
		}
		String result = null;
		try {
			result = JsonUtil.convert2Json(nqrbs);
		} catch (DataInvalidException e) {
			log.error(String.format("notifyService 结果数组转化字符串失败"), e);
			return "";
		}
		if (log.isDebugEnabled()) {
			log.debug("NoticeQuery 的service方法处理成功返回：" + result);
		}
		return result;
	}

}
