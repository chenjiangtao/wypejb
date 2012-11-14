package com.my.interceptor;

import java.util.Arrays;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import com.my.logger.MyDetailLogger;
import com.my.logger.MyLogger;

public class LoggerInterceptor{
	private static MyDetailLogger dtlLog = MyDetailLogger.getLogger(LoggerInterceptor.class);
	@AroundInvoke
	public Object interceptorMethod(InvocationContext ctx) throws Exception{
		Object res = null;
		try {
			Class<?> retType = ctx.getMethod().getReturnType();
			Object[] paras = ctx.getParameters();
			dtlLog.info(Arrays.toString(paras));
			MyLogger.simpleLog(retType.toString());
			MyLogger.accessLog("interceptorMethod-"+System.currentTimeMillis());
			
		
			res = ctx.proceed();
		} catch (RuntimeException e) {
			MyLogger.error(e);
		}
		return res;
	}
}
