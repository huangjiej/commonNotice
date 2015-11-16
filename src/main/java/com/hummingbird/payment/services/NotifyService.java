package com.hummingbird.payment.services;

import java.util.List;

import com.hummingbird.payment.vo.UserVO;

//nfs.SendNoticeFactory(notifyType,content, vos,apppId);
public interface NotifyService {
	/**
	 * 发送通知
	 */
	public Integer insertRecordsIntoDatabase(String type, List<UserVO> vos, String appId, String jsonStr);

	/**
	 * 查询通知
	 */
	public String NoticeQuery(Integer notifyId);
}
