package com.my.logger;

import java.lang.reflect.Method;
import org.apache.log4j.Logger;
import com.my.utils.ContextManager;


public class MyLogger{
	/**错误日志 */
	private static Logger errorLog = Logger.getLogger("errorLog"); 
	/**访问日志 */
	private static Logger accessLog = Logger.getLogger("accessLog"); 
	/**简要日志 */
	private static Logger simpleLog = Logger.getLogger("simpleLog");
	
	public static void error(Exception e){
		errorLog.error("系统错误", e);
	}
	
	public static void error(String errMsg){
		errorLog.error(ContextManager.getRpid() + ":" + errMsg);
	}
	
	public static void error(String errMsg,Exception e){
		errorLog.error(ContextManager.getRpid() + ":" + errMsg, e);
	}
	
	public static void error(Method method, Exception e){
		String className = method.getDeclaringClass().getSimpleName();
		if(isFiltered(className))
			return;
		errorLog.error("",e);
	}
	
	public static void simpleLog(String msg){
		simpleLog.info(ContextManager.getRpid() + ":" + msg);
	}
	
	public static void accessLog(String msg){
		accessLog.info(ContextManager.getRpid() + ":" + msg);
	}
	public static Logger getErrorLog() {
		return errorLog;
	}

	public static Logger getAccessLog() {
		return accessLog;
	}

	public static Logger getSimpleLog() {
		return simpleLog;
	}

	private static boolean isFiltered(String className){
//		return !className.contains("EBank");
		return false;
	}

}
