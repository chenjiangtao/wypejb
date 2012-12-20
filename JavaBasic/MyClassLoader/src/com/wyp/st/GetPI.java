/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2012-12-19 下午04:08:03</p>
 * @author wangyunpeng
 * @version 1.0
 */
package com.wyp.st;

/**
 * desc: <p>创建人：wangyunpeng 创建日期：2012-12-19 </p>
 * 
 * @version V1.0
 */
public class GetPI{
	public static double Darts(int n) {
		int k = 0;
		double x = 0.0D;
		double y = 0.0D;
		for (int i = 0; i < n; i++) {
			x = Math.random();
			y = Math.random();

			if (x * x + y * y <= 1.0D)
				k++;
		}
		return 4 * k / n;
	}

	// 本热部署实验中，上面的Darts函数没有用到，请忽略
	public static void Output() {
		System.out.println("Output_modify");
	}

}
