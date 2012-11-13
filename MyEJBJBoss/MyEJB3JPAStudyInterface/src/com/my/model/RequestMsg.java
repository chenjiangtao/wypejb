package com.my.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RequestMsg implements Serializable{

	/**
	 * long:serialVersionUID 
	 */
	private static final long serialVersionUID = -3522793793919672296L;
	private String rpid;
	private String reqDate;
	private String reqTime;
	private Map<String, String> reqParam = new HashMap<String, String>();
	public String getRpid() {
		return rpid;
	}
	public void setRpid(String rpid) {
		this.rpid = rpid;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	public String getReqTime() {
		return reqTime;
	}
	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}
	public Map<String, String> getReqParam() {
		return reqParam;
	}
	public void setReqParam(Map<String, String> reqParam) {
		this.reqParam = reqParam;
	}
	
	public String getParamValue(String key) {
		if(this.reqParam != null)
			return this.reqParam.get(key);
		else
			return null;
	}
	
	public void putParamValue(String key, String value) {
		if(this.reqParam != null)
			this.reqParam.put(key, value);
	}
	@Override
	public String toString() {
		return "RequestMsg-[rpid:"+rpid+", reqDate:"+reqDate+", reqTime:"+reqTime+", "
				+"reqParam:"+reqParam+"]";
	}
}
