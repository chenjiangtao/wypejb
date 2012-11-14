package com.my.service.local;

import com.my.model.RequestMsg;
import com.my.model.ResponseMsg;

public interface EjbInvokeLocal{
	public ResponseMsg invokeMethodLocal(RequestMsg reqMsg);
}
