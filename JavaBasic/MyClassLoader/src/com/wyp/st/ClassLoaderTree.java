/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2012-12-19 下午02:19:45</p>
 * @author wangyunpeng
 * @version 1.0
 */
package com.wyp.st;

/**
 * desc: <p>创建人：wangyunpeng 创建日期：2012-12-19 </p>
 * 
 * @version V1.0
 */
public class ClassLoaderTree{

	/**
	 * desc: <p>创建人：wangyunpeng , 2012-12-19 下午02:19:45</p>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ClassLoader loader = ClassLoaderTree.class.getClassLoader();
		while (loader != null) {
			System.out.println(loader.toString());
			loader = loader.getParent();
		}
	}

}
