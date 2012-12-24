/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2012-12-21 下午03:21:18</p>
 * @author wangyunpeng
 * @version 1.0
 */
package com.wyp.st.loader;

/** 
 * desc:
 * <p>创建人：wangyunpeng 创建日期：2012-12-21 </p>
 * @version V1.0  
 */
public class TestLoad{
	public static void main(String[] args) {
		try {
			String classDataRootPath = "E:\\wypsmall\\Code\\Code_Myeclipse\\MyClassLoaderThird\\bin";
			FileSystemClassLoader fscl = new FileSystemClassLoader(classDataRootPath);
			String className = "com.wyp.service.st.PrintLogService";
			Class<?> clazz = fscl.loadClass(className);
			ClassLoader loader = fscl;
			while (loader != null) {
				System.out.println(loader.toString());
				loader = loader.getParent();
				Class<?> tmp = loader.loadClass(className);
				System.out.println(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
