/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2012-11-17 下午08:55:43</p>
 * @author wangyunpeng
 * @version 1.0
 */
package com.my.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/** 
 * desc:
 * <p>创建人：wangyunpeng 创建日期：2012-11-17 </p>
 * @version V1.0  
 */
public class PropsUtil{
	private static final String[] files = {"spay_config.properties"};
	private static Properties properties = new Properties();
	private static long refreshInterval = 1000*5;
	private static Map<String,Long> modMap = new HashMap<String,Long>();
	public static long lastTime = System.currentTimeMillis();
	
	static {		
		load();
	}

	/**
	 * 
	* <p>方法名称: load|描述:循环加载配置文件 </p>
	 */
	private static void load() {
		File configFile = null;
		InputStream in = null;
		for(String file : files){
			URL url = PropsUtil.class.getClassLoader().getResource(file);
			System.out.println(url.getPath());
			configFile = new File(URLDecoder.decode(url.getPath()));
			//最新修改时间
			Long lastModifiedTime = configFile.lastModified();
			//最后一次修改时间
			Long oldModifiedTime = modMap.get(file);
			//若未改动，则不再加载
			if(oldModifiedTime != null && lastModifiedTime.longValue() ==  oldModifiedTime.longValue())
				continue;
			//更新更改时间
			modMap.put(file, lastModifiedTime);
			//加载
			try{
				in = new FileInputStream(configFile);
				properties.load(in);
				in.close();
			}catch (IOException e){
				e.printStackTrace();
				System.err.println("加载配置文件异常");
			}finally{
				if(null!=in){
					try{
						in.close();
					}catch (IOException e){
						e.printStackTrace();
						System.err.println("加载配置文件时，关闭输入流异常");
					}
					in =null;
				}
			}
		}
	}
	
	/**
	 * 
	* <p>方法名称: reflesh|描述:刷新配置文件 </p>
	 */
	private static void reflesh(){
		long now = System.currentTimeMillis();
		if(now - lastTime > refreshInterval){
			load();
			lastTime = now;
		}
	}
	
	/**
	 * 
	* <p>方法名称: getMessage|描述:根据键获取键值 </p>
	* @param key
	* @return 
	 */
	public static String getMessage(String key) {
		reflesh();
		return  null == properties.getProperty(key) ? "" : properties.getProperty(key).trim();
	}
}
