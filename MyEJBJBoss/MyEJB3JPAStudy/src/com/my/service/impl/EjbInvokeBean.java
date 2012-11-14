package com.my.service.impl;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import com.my.interceptor.LoggerInterceptor;
import com.my.logger.MyDetailLogger;
import com.my.model.RequestMsg;
import com.my.model.ResponseMsg;
import com.my.service.EjbInvokeRemote;
import com.my.service.local.EjbInvokeLocal;

@Stateless(name="EjbInvokeService")
@Local(EjbInvokeLocal.class)
@Remote(EjbInvokeRemote.class)
@Interceptors(LoggerInterceptor.class)
public class EjbInvokeBean implements EjbInvokeLocal, EjbInvokeRemote{
	private static MyDetailLogger dtlLogger = MyDetailLogger.getLogger(EjbInvokeBean.class);
	public ResponseMsg invokeMethodLocal(RequestMsg reqMsg) {
		dtlLogger.info("use the method invokeMethodLocal");
		ResponseMsg resMsg = new ResponseMsg();
		resMsg.setRetCode("0000");
		resMsg.setRetMsg("SUCCESS");
		resMsg.setCurTime(System.currentTimeMillis());
		return resMsg;
	}

	public ResponseMsg invokeMethodRemote(RequestMsg reqMsg) {
		dtlLogger.info("use the method invokeMethodRemote");
		ResponseMsg resMsg = new ResponseMsg();
		resMsg.setRetCode("0000");
		resMsg.setRetMsg("SUCCESS");
		resMsg.setCurTime(System.currentTimeMillis());
		return resMsg;
	}

}
