package com.my.service;

import com.my.model.RequestMsg;
import com.my.model.ResponseMsg;


public interface ReceiveParamService{
	public ResponseMsg transformMsg(RequestMsg reqMsg);
	public void transformMsgNoReturn(RequestMsg reqMsg, ResponseMsg retMsg);
}
