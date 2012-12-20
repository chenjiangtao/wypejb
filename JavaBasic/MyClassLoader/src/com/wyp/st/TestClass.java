/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2012-12-19 下午02:26:45</p>
 * @author wangyunpeng
 * @version 1.0
 */
package com.wyp.st;

import java.lang.reflect.Method;

/** 
 * desc:
 * <p>创建人：wangyunpeng 创建日期：2012-12-19 </p>
 * @version V1.0  
 */
public class TestClass{

	public static void main(String[] args) {
		String classDataRootPath = "E:\\wypsmall\\classtmp";
		FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath);
		FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath);
	    String className = "com.wyp.st.Sample";  
	    try {
	        Class<?> class1 = fscl1.loadClass(className);
	        Object obj1 = class1.newInstance();
	        Class<?> class2 = fscl2.loadClass(className);
	        Object obj2 = class2.newInstance();
	        Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);
	        setSampleMethod.invoke(obj1, obj2);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	}
}
