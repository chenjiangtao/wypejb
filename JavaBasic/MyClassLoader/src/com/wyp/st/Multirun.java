/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2012-12-19 下午04:12:12</p>
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
public class Multirun implements Runnable{
	public void run() {
		try {
			while (true) {
				// 每次都创建出一个新的类加载器
				// class需要放在自己package名字的文件夹下
				String url = System.getProperty("user.dir") + "/bin";// "/lib/yerasel/GetPI.jar";
				HotSwapCL cl = new HotSwapCL(url, new String[] { "com.wyp.st.GetPI" });
				Class cls = cl.loadClass("com.wyp.st.GetPI");
				Object foo = cls.newInstance();
				// 被调用函数的参数
				Method m = foo.getClass().getMethod("Output",
						new Class[] {});
				m.invoke(foo, new Object[] {});
				Thread.sleep(500);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}