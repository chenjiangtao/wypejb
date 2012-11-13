package com.my.service;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import com.my.model.RequestMsg;
import com.my.model.ResponseMsg;

@Stateless(name="ReceiveParamServiceBean")
@Remote(ReceiveParamService.class)
public class ReceiveParamBean implements ReceiveParamService{

	public ResponseMsg transformMsg(RequestMsg reqMsg) {
		System.out.println(reqMsg.toString());
		ResponseMsg resMsg = new ResponseMsg();
		resMsg.setRetCode("0000");
		resMsg.setRetMsg("SUCCESS");
		resMsg.setCurTime(System.currentTimeMillis());
		return resMsg;
	}

}
