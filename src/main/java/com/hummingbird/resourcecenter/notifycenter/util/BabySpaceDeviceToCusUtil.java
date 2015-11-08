package com.hummingbird.resourcecenter.notifycenter.util;

import java.util.Date;

import com.hummingbird.common.constant.CommonStatusConst;
import com.hummingbird.common.exception.DataInvalidException;
import com.hummingbird.common.util.JsonUtil;
import com.hummingbird.common.util.SpringBeanUtil;
import com.hummingbird.common.util.ValidateUtil;
import com.hummingbird.common.vo.ResultModel;
import com.hummingbird.resourcecenter.notifycenter.entity.NotifyData;
import com.hummingbird.resourcecenter.notifycenter.entity.NotifyRecords;
import com.hummingbird.resourcecenter.notifycenter.exception.NONotifySenderException;
import com.hummingbird.resourcecenter.notifycenter.mapper.NotifyRecordsMapper;

public class BabySpaceDeviceToCusUtil {
	static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory
			.getLog(BabySpaceDeviceToCusUtil.class);

	/**
	 * 发送通知
	 * 
	 * @param not
	 * @throws DataInvalidException
	 */
	public static void sendDevice(NotifyRecords not) throws DataInvalidException {
		ValidateUtil.assertNull(not, "通知对象");
		String dataStr = not.getNotifyData();
		//
		NotifyData nf = JsonUtil.convertJson2Obj(dataStr, NotifyData.class);
		NotifySender sender = null;
		try {
			sender = NotifyFactory.getNotifySender(not);
			ResultModel sendresult = sender.sendNotify(new NotifyParameter(not));
			not.setUpdateTime(new Date());
			if (sendresult.isSuccessed()) {
				not.setStatus(CommonStatusConst.STATUS_OK);
			} else {
				not.setStatus(CommonStatusConst.STATUS_FAIL);
				not.setSendMsg(sendresult.getErrmsg());
			}
			NotifyRecordsMapper nrDao = SpringBeanUtil.getInstance().getBean(NotifyRecordsMapper.class);
			nrDao.updateByPrimaryKeySelective(not);
		} catch (NONotifySenderException e) {
			log.error("没有找到对应的发送器" + e.getMessage());
			not.setStatus(CommonStatusConst.STATUS_FAIL);
			not.setSendMsg(e.getMessage());
		}
	}

}
