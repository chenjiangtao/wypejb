package com.my.logger;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.my.utils.ContextManager;

public class MyDetailLogger{
	private  Logger detailLog = null;
	private static Map<String, MyDetailLogger> logMap = new HashMap<String, MyDetailLogger>();
	
	private MyDetailLogger(Class<?> handler){
		this.detailLog = Logger.getLogger(handler);
	}
	
	public static MyDetailLogger getLogger(Class<?> handler){
		String key = handler.toString();
		if (!logMap.containsKey(key)) {
			MyDetailLogger value = new MyDetailLogger(handler);
			logMap.put(key, value);
		}
		return (MyDetailLogger) logMap.get(key);
	}
	/**
	 * 记录到详细日志中
	 */
	public  void debug(String msg) {
		detailLog.debug(" rpid=["+ContextManager.getRpid()+"] "+ msg);
	}

	/**
	 * 记录到详细日志中
	 */
	public  void info(String msg) { 
		
		detailLog.info(" rpid=["+ContextManager.getRpid()+"] "+ msg);
	}
	public  void info(Object msg) { 
		
		detailLog.info(" rpid=["+ContextManager.getRpid()+"] "+ msg);
	}
	
	public void info(Object msg,Exception e){
		detailLog.info(" rpid=["+ContextManager.getRpid()+"] "+ msg,e);
	}
	
}
