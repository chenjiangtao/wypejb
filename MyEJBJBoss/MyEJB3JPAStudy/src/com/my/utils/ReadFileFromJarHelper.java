/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2012-11-18 下午07:20:27</p>
 * @author wangyunpeng
 * @version 1.0
 */
package com.my.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.apache.log4j.Logger;

/** 
 * desc:
 * <p>创建人：wangyunpeng 创建日期：2012-11-18 </p>
 * @version V1.0  
 */
public class ReadFileFromJarHelper{
	private static Logger log = Logger.getLogger(SpayMySignUtil.class);
	private static String filePath = "cert/3568_GuangDongYiDongShiChangBu.key.p8";
	public static void readFile() {
		byte[] buf = new byte[640];
		
		InputStream in = null;
		try{
			in = PropsUtil.class.getClassLoader().getResourceAsStream(filePath);
			in.read(buf);
			log.info("-read-1-"+Arrays.toString(buf));
		}catch(Exception e){
			log.error("read file error:",e);
		}finally{
			if(in != null)
	            try {
	                in.close();
                } catch (IOException e) { 
                	log.error("read file error:",e);
                }
		}
	}
	
	public static void readFileBuffer() {
		int readSize = 512;
		byte[] buffer = new byte[readSize];
		byte[] all = new byte[640];
		int count = 0;
		int seek = 0;
		InputStream in = null;
		try{
			in = PropsUtil.class.getClassLoader().getResourceAsStream(filePath);
			while((count = in.read(buffer))!= -1){
				System.arraycopy(buffer, 0, all, seek, count);
				seek += count;
            }  
			log.info("-read-2-"+Arrays.toString(all));
		}catch(Exception e){
			log.error("read file error:",e);
		}finally{
			if(in != null)
	            try {
	                in.close();
                } catch (IOException e) { 
                	log.error("read file error:",e);
                }
		}
	}
}
