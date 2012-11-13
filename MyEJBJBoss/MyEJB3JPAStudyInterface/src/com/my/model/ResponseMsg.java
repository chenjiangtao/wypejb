package com.my.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResponseMsg implements Serializable{

	/**
	 * long:serialVersionUID 
	 */
	private static final long serialVersionUID = 708107897521990602L;
	private String retCode;
	private String retMsg;
	private Long curTime;
	private Map<String, String> retParam = new HashMap<String, String>();
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	public Long getCurTime() {
		return curTime;
	}
	public void setCurTime(Long curTime) {
		this.curTime = curTime;
	}
	public Map<String, String> getRetParam() {
		return retParam;
	}
	public void setRetParam(Map<String, String> retParam) {
		this.retParam = retParam;
	}
	public String getParamValue(String key) {
		if(this.retParam != null)
			return this.retParam.get(key);
		else
			return null;
	}
	
	public void putParamValue(String key, String value) {
		if(this.retParam != null)
			this.retParam.put(key, value);
	}
	@Override
	public String toString() {
		return "ResponseMsg-[retCode:"+retCode+", retMsg:"+retMsg+", curTime:"+curTime+", "
				+"retParam:"+retParam+"]";
	}
	
	
}
