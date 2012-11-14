package com.my.service;

import com.my.model.RequestMsg;
import com.my.model.ResponseMsg;


public interface EjbInvokeRemote{
	public ResponseMsg invokeMethodRemote(RequestMsg reqMsg);
}
