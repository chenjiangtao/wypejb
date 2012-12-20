/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2012-12-19 下午04:08:55</p>
 * @author wangyunpeng
 * @version 1.0
 */
package com.wyp.st.loader;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * desc: <p>创建人：wangyunpeng 创建日期：2012-12-19 </p>
 * 
 * @version V1.0
 */
public class TestHotLoad{
	public static Method initAddMethod() {
		try {
			Method add = URLClassLoader.class.getDeclaredMethod("addURL",
					new Class[] { URL.class });
			add.setAccessible(true);
			return add;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {

		// 热部署测试代码
		Thread t;
		t = new Thread(new Multirun());
		t.start();
	}

}
