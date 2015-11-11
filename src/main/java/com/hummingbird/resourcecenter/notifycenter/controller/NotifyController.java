package com.hummingbird.resourcecenter.notifycenter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hummingbird.common.controller.BaseController;
import com.hummingbird.common.exception.ValidateException;
import com.hummingbird.common.ext.AccessRequered;
import com.hummingbird.common.util.RequestUtil;
import com.hummingbird.common.vo.ResultModel;
import com.hummingbird.resourcecenter.notifycenter.services.NotifyService;
import com.hummingbird.resourcecenter.notifycenter.vo.NotifyAddVO;
import com.hummingbird.resourcecenter.notifycenter.vo.NotifyQueryVO;
import com.hummingbird.resourcecenter.notifycenter.vo.UserVO;

@Controller
@RequestMapping(value = "/notify", method = RequestMethod.POST)
public class NotifyController extends BaseController {
	@Autowired
	NotifyService nfs;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@AccessRequered(methodName = "添加通知接口")
	public @ResponseBody ResultModel addNotify(HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) {
			log.debug("进入通知接口中");
		}
		String messagebase = "添加通知接口";
		NotifyAddVO transorder = null;
		ResultModel rm = new ResultModel();
		String jsonstr = null;
		try {
			jsonstr = RequestUtil.getRequestPostData(request);
			request.setAttribute("rawjson", jsonstr);
			transorder = RequestUtil.convertJson2Obj(jsonstr, NotifyAddVO.class);
		} catch (Exception e) {
			log.error(String.format("获取参数出错"), e);
			rm.mergeException(ValidateException.ERROR_PARAM_FORMAT_ERROR.cloneAndAppend(null, "添加通知接口参数"));
			return rm;
		}
		rm.setErrmsg(messagebase + "成功");
		String notifyType = transorder.getBody().getNotifyType();
		List<UserVO> vos = transorder.getBody().getUsers();
		String appid = transorder.getApp().getAppId();
		if (StringUtils.isBlank(notifyType) || vos == null || StringUtils.isBlank(jsonstr)) {
			log.error("添加通知接口参数不正确：参数含空");
			rm.mergeException(ValidateException.ERROR_PARAM_FORMAT_ERROR.cloneAndAppend(null,
					"参数有空无法调用insertRecordsIntoDatabase方法"));
			return rm;
		}
		Integer notifyId = nfs.insertRecordsIntoDatabase(notifyType, vos, appid, jsonstr);
		if (notifyId == null) {
			log.error("添加通知接口sednnoticefactory方法错误");
			rm.mergeException(
					ValidateException.ERROR_PARAM_FORMAT_ERROR.cloneAndAppend(null, "insertRecordsIntoDatabase方法返回空"));
			return rm;
		} 
		if (notifyId == 0) {
			log.error("insertRecordsIntoDatabase里面调用出现错误:出现空参数");
		}
		else {
			rm.put("notifyId", notifyId);
			if (log.isDebugEnabled()) {
				log.debug("添加通知借口插入数据库成功，返回" + notifyId);
			}
		}
		return rm;
	}

	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@AccessRequered(methodName = "通知接口查询")
	public @ResponseBody ResultModel queryNotify(HttpServletRequest request, HttpServletResponse response) {
		String messagebase = "查询通知接口";
		NotifyQueryVO transorder = null;
		ResultModel rm = new ResultModel();
		String jsonstr = null;
		try {
			jsonstr = RequestUtil.getRequestPostData(request);
			request.setAttribute("rawjson", jsonstr);
			transorder = RequestUtil.convertJson2Obj(jsonstr, NotifyQueryVO.class);
		} catch (Exception e) {
			log.error(String.format("获取参数出错"), e);
			rm.mergeException(ValidateException.ERROR_PARAM_FORMAT_ERROR.cloneAndAppend(null, "离职参数"));
			return rm;
		}
		rm.setErrmsg(messagebase + "成功");
		try {
			Integer notifyid = transorder.getBody().getNotifyId();
			if (notifyid > 0) {
				String result = nfs.NoticeQuery(notifyid);
				rm.put("results", result);
			}
		} catch (Exception e1) {
			log.error(String.format(messagebase + "失败"), e1);
			rm.mergeException(ValidateException.ERROR_PARAM_FORMAT_ERROR.cloneAndAppend(null, "NoticeQuery抛异常"));
			rm.setErrmsg("查询通知失败，其它原因！");
		}
		return rm;
	}

}
