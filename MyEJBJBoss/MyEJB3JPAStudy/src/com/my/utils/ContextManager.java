package com.my.utils;

public class ContextManager{
	private static ThreadLocal<String> rpid = new ThreadLocal<String>();
	private static ThreadLocal<String> methodName = new ThreadLocal<String>();
	
	/**
     * 获得当前任务号
     * 
     * @return
     */
    public static String getRpid(){
        return  rpid.get();
    }
    
    /**
     * 设置当前任务号
     * 
     * @return
     */
    public static void setRpid(String value){
    	rpid.set(value);
    }
    /**
     * 设置当前调用的方法名
     */
    public static void setMethodName(String value){
    	methodName.set(value);
    }
    /**
     * 取得当前调用的方法名
     */
    public static String getMethodName(){
    	return methodName.get();
    }
}
