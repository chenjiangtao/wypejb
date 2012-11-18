/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2012-11-17 下午09:24:04</p>
 * @author wangyunpeng
 * @version 1.0
 */
package com.ce;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;

/** 
 * desc:
 * <p>创建人：wangyunpeng 创建日期：2012-11-17 </p>
 * @version V1.0  
 */
public class TestReadFile{

	/**
	 * desc:
	 * <p>创建人：wangyunpeng , 2012-11-17 下午09:24:04</p>
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			File file = new File("E:\\wypsmall\\Code\\Code_Myeclipse\\MyEJB3JPAStudy\\src\\cert\\testMer.key.p8");
			InputStream in = new FileInputStream(file);
			byte[] key = new byte[634];
			in.read(key);
			System.out.println(Arrays.toString(key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
