/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2012-11-17 下午08:58:18</p>
 * @author wangyunpeng
 * @version 1.0
 */
package com.my.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import org.apache.log4j.Logger;

/** 
 * desc:
 * <p>创建人：wangyunpeng 创建日期：2012-11-17 </p>
 * @version V1.0  
 */
public class SpayMySignUtil{
	private static Logger log = Logger.getLogger(SpayMySignUtil.class);

	private static byte[] key = new byte[20480]; //634
	
	private static byte[] spCert = new byte[20480];
	
	private static int readSize = 512;
	
	private static String curCertFileName;
	
	private static String curKeyFileName;
	
    /**
     * desc:载入公钥
     * <p>创建人：wangyunpeng , 2012-11-8 下午02:24:39</p>
     * @throws Exception
     */
    public synchronized static void loadCert() throws Exception{
    	String certPath = PropsUtil.getMessage("SPAY4.platPublicKey");
    	if(!certPath.equals(curCertFileName)){
    		InputStream in = null;
    		int count = 0;
    		int seek = 0;
    		byte[] buffer = new byte[readSize];
    		try{
    			in = PropsUtil.class.getClassLoader().getResourceAsStream(certPath);
    			//in.read(spCert);
    			while((count = in.read(buffer))!= -1){
    				System.arraycopy(buffer, 0, spCert, seek, count);
    				seek += count;
                }
    			curCertFileName = certPath;
    		}catch(Exception e){
    			log.error(e);
    		}finally{
    			if(in != null)
    	            try {
    	                in.close();
                    } catch (IOException e) { 
                    	log.error(e);
                    }
    		}
    	}
    }
    /**
     * desc:载入私钥
     * <p>创建人：wangyunpeng , 2012-11-8 下午02:25:18</p>
     * @param merId
     * @throws Exception
     */
    public synchronized static void loadKey(String merId) throws Exception{
    	String keyPath = PropsUtil.getMessage("SPAY4.merPrivateKey."+merId);
    	System.out.println("aa"+keyPath);
    	if(!keyPath.equals(curKeyFileName)){
    		InputStream in = null;
    		int count = 0;
    		int seek = 0;
    		byte[] buffer = new byte[readSize];
    		try{
    			in = PropsUtil.class.getClassLoader().getResourceAsStream(keyPath);
//    			in.read(key);
    			while((count = in.read(buffer))!= -1){
    				System.arraycopy(buffer, 0, key, seek, count);
    				seek += count;
                }  
//    			URL url= PropsUtil.class.getClassLoader().getSystemResource(keyPath);
//    			File file = new File(url.getPath());
//    			in = new FileInputStream(file);
//    			in.read(key);
//    			System.out.println(url);
//    			System.out.println("-file--"+Arrays.toString(key));
    			
    			curKeyFileName = keyPath;
    		}catch(Exception e){
    			log.error(e);
    		}finally{
    			if(in != null)
    	            try {
    	                in.close();
                    } catch (IOException e) { 
                    	log.error(e);
                    }
    		}
    	}
    }
	/**
	 * desc:签名
	 * <p>创建人：wangyunpeng , 2012-11-8 下午02:25:36</p>
	 * @param content
	 * @param merId
	 * @return
	 * @throws Exception
	 */
	public static String platSign(String content, String merId) throws Exception {
		
		loadKey(merId); 
		
		if (key == null)
			throw new Exception("无签名证书");

		PrivateKey pk = MySignUtil.genPrivateKey(key);
		byte[] signData = MySignUtil.sign(pk, content.getBytes());
		String sign = Base64.encode(signData);
		return sign;
	}

	/**
	 * desc:验签
	 * <p>创建人：wangyunpeng , 2012-11-8 下午02:25:26</p>
	 * @param unsign
	 * @param sign
	 * @throws Exception
	 */
	public static void checkPlatSign(String unsign, String sign)
	        throws Exception {
		log.info("平台签名 plain[" + unsign + "],  sign[" + sign + "]");
		
		loadCert();
		
		if (spCert == null)
			throw new Exception("无签名证书");

		X509Certificate x509cert = MySignUtil.genCertificate(spCert);
		byte[] bytesign = sign.getBytes();
		byte[] bSign = Base64.decode(bytesign);
		if (!MySignUtil.verify(x509cert, unsign.getBytes(), bSign)) {
			throw new Exception("平台验签失败");
		}
	}

	public static void main(String[] args) {
		try {
			//mer_date=20121026&mer_id=9996&order_id=1210261329053760&ret_code=0000&version=4.0
			//&sign=ZnX/ogcksGfB5M3fwlMAgwIetuNr1ZQX81XDOrOXZlJ/sLYRrBpoC1y21k92IJys50mo16QXu+1XgMmHRZXpRsuAq1oOauSEZe5z0Wv2MPNa59cOd/C+Nqjg2bd8e42ERUcHBfYtWIV5BGpZBo6ElMAVARTu3QTd8wZIGd/MGxw=&sign_type=RSA
			String plat = "amount=100&amt_type=RMB&charset=UTF-8&mer_date=20121117&mer_id=9996&mer_priv=merid:9996;orderid:P12345&notify_url=http://221.179.44.30:7070/gdDemo/spay4Notify.do&order_id=GD20121117192152&pay_type=B2CBANK&ret_url=http://221.179.44.30:7070/gdDemo/spay4Ret.do&service=pay_req_split_front&version=4.0";
			System.out.println(plat);
			System.out.println(platSign(plat, "9996"));
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
