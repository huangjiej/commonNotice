package com.hummingbird.babyspace.services;

import java.util.List;

import com.hummingbird.babyspace.entity.NotifyRecords;
import com.hummingbird.babyspace.entity.RawNotifyRecords;
import com.hummingbird.babyspace.vo.UserVO;
//nfs.SendNoticeFactory(notifyType,content, vos,apppId);
public interface NotifyService {
	/**
	 * 发送通知
	 * */
     public Integer SendNoticeFactory(String type,List<UserVO> vos,String appId,String jsonStr);
     /**
      * 查询通知
      * */
     public String NoticeQuery(Integer notifyId);
}
