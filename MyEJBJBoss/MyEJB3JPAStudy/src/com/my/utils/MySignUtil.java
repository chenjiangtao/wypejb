/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2012-11-17 下午08:57:02</p>
 * @author wangyunpeng
 * @version 1.0
 */
package com.my.utils;

import java.io.ByteArrayInputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;

/** 
 * desc:
 * <p>创建人：wangyunpeng 创建日期：2012-11-17 </p>
 * @version V1.0  
 */
public class MySignUtil{
	   public MySignUtil()
	    {
	    }

	    public static PrivateKey genPrivateKey(byte key[])
	    {
	        PrivateKey pk = null;
	        try
	        {
	        	
	            PKCS8EncodedKeySpec peks = new PKCS8EncodedKeySpec(key);
	            KeyFactory kf = KeyFactory.getInstance("RSA");
	            pk = kf.generatePrivate(peks);
	        }
	        catch(Exception e)
	        {
	            return null;
	        }
	        return pk;
	    }

	    public static byte[] sign(PrivateKey pk, byte data[])
	    {
	        byte sb[] = (byte[])null;
	        try
	        {
	            Signature sig = Signature.getInstance("SHA1withRSA");
	            sig.initSign(pk);
	            //System.out.println(Arrays.toString(data));
	            sig.update(data);
	            sb = sig.sign();
	            //System.out.println(Arrays.toString(sb));
	        }
	        catch(Exception e)
	        {
	            return null;
	        }
	        return sb;
	    }

	    public static X509Certificate genCertificate(byte certData[])
	    {
	        ByteArrayInputStream bais = new ByteArrayInputStream(certData);
	        X509Certificate cert = null;
	        try
	        {
	            CertificateFactory cf = CertificateFactory.getInstance("X.509");
	            cert = (X509Certificate)cf.generateCertificate(bais);
	        }
	        catch(Exception e)
	        {
	            return null;
	        }
	        return cert;
	    }

	    public static boolean verify(X509Certificate cert, byte plain[], byte signData[])
	    {
	        try
	        {
	            Signature sig = Signature.getInstance("SHA1withRSA");
	            sig.initVerify(cert);
	            sig.update(plain);
	            return sig.verify(signData);
	        }
	        catch(Exception e)
	        {
	            return false;
	        }
	    }
}
