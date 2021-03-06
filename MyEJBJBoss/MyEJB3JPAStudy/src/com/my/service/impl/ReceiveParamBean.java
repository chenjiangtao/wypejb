package com.my.service.impl;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import com.my.interceptor.LoggerInterceptor;
import com.my.model.RequestMsg;
import com.my.model.ResponseMsg;
import com.my.service.EjbInvokeRemote;
import com.my.service.ReceiveParamService;
import com.my.service.local.EjbInvokeLocal;
import com.my.utils.ReadFileFromJarHelper;
import com.my.utils.SpayMySignUtil;

@Stateless(name="ReceiveParamServiceBean")
@Remote(ReceiveParamService.class)
@Interceptors(LoggerInterceptor.class)
public class ReceiveParamBean implements ReceiveParamService{
	@EJB(beanName="EjbInvokeService") private EjbInvokeLocal ejbInvokeLocal;
	@EJB(beanName="EjbInvokeService") private EjbInvokeRemote ejbInvokeRemote;
	public ResponseMsg transformMsg(RequestMsg reqMsg) {
		System.out.println(reqMsg.toString());
		ResponseMsg resMsg = new ResponseMsg();
		resMsg.setRetCode("0000");
		resMsg.setRetMsg("SUCCESS");
		resMsg.setCurTime(System.currentTimeMillis());
		return resMsg;
	}
	//java是传值，不是传引用，所以不能使用参数作为返回值，如果想返回多个值，可以使用复杂对象作为返回值
	public void transformMsgNoReturn(RequestMsg reqMsg, ResponseMsg retMsg) {
		System.out.println(reqMsg.toString());
		retMsg.setRetCode("0000");
		retMsg.setRetMsg("SUCCESS");
		retMsg.setCurTime(System.currentTimeMillis());
	}
	public ResponseMsg transformMsgEjbInvokeLocalBean(RequestMsg reqMsg) {
		//用来测试读取王阿尼教案
		ReadFileFromJarHelper.readFile();
		ReadFileFromJarHelper.readFileBuffer();
		return ejbInvokeLocal.invokeMethodLocal(reqMsg);
	}
	public ResponseMsg transformMsgEjbInvokeRemoteBean(RequestMsg reqMsg) {
		//用来测试读取证书文件
		SpayMySignUtil.main(null);
		return ejbInvokeRemote.invokeMethodRemote(reqMsg);
	}

}
