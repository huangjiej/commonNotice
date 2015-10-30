package com.hummingbird.babyspace.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hummingbird.babyspace.services.NotifyService;
import com.hummingbird.babyspace.vo.NotifyAddVO;
import com.hummingbird.babyspace.vo.NotifyQueryVO;
import com.hummingbird.babyspace.vo.UserVO;
import com.hummingbird.common.controller.BaseController;
import com.hummingbird.common.exception.ValidateException;
import com.hummingbird.common.ext.AccessRequered;
import com.hummingbird.common.util.RequestUtil;
import com.hummingbird.common.vo.ResultModel;
@Controller
@RequestMapping(value="/notify",method=RequestMethod.POST)
public class NotifyController extends BaseController  {
	@Autowired
	private NotifyService nfs;
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@AccessRequered(methodName = "添加通知接口")
	public @ResponseBody ResultModel addNotify(HttpServletRequest request,HttpServletResponse response) {
		String messagebase = "添加通知接口";
		NotifyAddVO transorder = null;
		int base = 310100;
		ResultModel rm = new ResultModel();
		String jsonstr =null;
		try {
		    jsonstr  = RequestUtil.getRequestPostData(request);
			request.setAttribute("rawjson", jsonstr);
			transorder = RequestUtil.convertJson2Obj(jsonstr, NotifyAddVO.class);
		} catch (Exception e) {
			log.error(String.format("获取参数出错"),e);
			rm.mergeException(ValidateException.ERROR_PARAM_FORMAT_ERROR.cloneAndAppend(null, "离职参数"));
			return rm;
		}
	    rm.setErrmsg(messagebase + "成功");
	    rm.setBaseErrorCode(base);
		try {
		String notifyType = transorder.getBody().getNotifyType();
		List<UserVO> vos = transorder.getBody().getUsers();
		String appid = transorder.getApp().getAppId();
        if(StringUtils.isBlank(notifyType)||vos==null||StringUtils.isBlank(jsonstr)){
             log.error("添加通知接口参数不正确");
             rm.setBaseErrorCode(base);
             rm.setErrmsg("添加通知接口参数不正确");
             return rm;
        }		
		Integer notifyId = nfs.SendNoticeFactory(notifyType,vos,appid,jsonstr);
		if(notifyId==0){
			log.error("添加通知接口sednnoticefactory方法错误");
			rm.setErrmsg("添加通知接口错误");
			rm.setErrcode(base);
			return rm;
		}else{
		  rm.put("notifyId",notifyId);
		}
		} catch (Exception e1) {
			log.error(String.format(messagebase+"失败"),e1);
			rm.mergeException(e1);
			rm.setErrmsg("接收通知失败，其它原因！");
		}
		return rm;
	}  
	@RequestMapping(value="/query",method=RequestMethod.POST)
	@AccessRequered(methodName = "通知接口查询")
	public @ResponseBody ResultModel queryNotify(HttpServletRequest request,HttpServletResponse response) {
		String messagebase = "查询通知接口";
		NotifyQueryVO transorder = null;
		int base = 310101;
		ResultModel rm = new ResultModel();
		String jsonstr =null;
		try {
			jsonstr  = RequestUtil.getRequestPostData(request);
			request.setAttribute("rawjson", jsonstr);
			transorder = RequestUtil.convertJson2Obj(jsonstr, NotifyQueryVO.class);
		} catch (Exception e) {
			log.error(String.format("获取参数出错"),e);
			rm.mergeException(ValidateException.ERROR_PARAM_FORMAT_ERROR.cloneAndAppend(null, "离职参数"));
			return rm;
		}
	    rm.setErrmsg(messagebase + "成功");
	    rm.setBaseErrorCode(base);
		try {
	     Integer notifyid = transorder.getBody().getNotifyId();
	     if(notifyid>0){
	      String result  = nfs.NoticeQuery(notifyid);
	      rm.put("results", result);
	     }
		} catch (Exception e1) {
			log.error(String.format(messagebase+"失败"),e1);
			rm.mergeException(e1);
			rm.setErrmsg("查询通知失败，其它原因！");
		}
		return rm;
	}  
}
