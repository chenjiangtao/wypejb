/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2012-11-17 下午06:31:28</p>
 * @author wangyunpeng
 * @version 1.0
 */
package com.my.utils;

/** 
 * desc:
 * <p>创建人：wangyunpeng 创建日期：2012-11-17 </p>
 * @version V1.0  
 */
public class Base64{
	 public static final byte[] decode(String paramString)
	  {
	    if (paramString == null) return null;

	    return decode(paramString.getBytes());
	  }

	  public static final byte[] decode(byte[] abyte0)
	  {
	        int i;
	        for(i = abyte0.length; abyte0[i - 1] == 61; i--);
	        byte abyte1[] = new byte[i - abyte0.length / 4];
	        for(int j = 0; j < abyte0.length; j++)
	            if(abyte0[j] == 61)
	                abyte0[j] = 0;
	            else
	            if(abyte0[j] == 47)
	                abyte0[j] = 63;
	            else
	            if(abyte0[j] == 43)
	                abyte0[j] = 62;
	            else
	            if(abyte0[j] >= 48 && abyte0[j] <= 57)
	                abyte0[j] = (byte)(abyte0[j] - -4);
	            else
	            if(abyte0[j] >= 97 && abyte0[j] <= 122)
	                abyte0[j] = (byte)(abyte0[j] - 71);
	            else
	            if(abyte0[j] >= 65 && abyte0[j] <= 90)
	                abyte0[j] = (byte)(abyte0[j] - 65);

	        int k = 0;
	        int l;
	        for(l = 0; l < abyte1.length - 2; l += 3)
	        {
	            abyte1[l] = (byte)(abyte0[k] << 2 & 255 | abyte0[k + 1] >>> 4 & 3);
	            abyte1[l + 1] = (byte)(abyte0[k + 1] << 4 & 255 | abyte0[k + 2] >>> 2 & 15);
	            abyte1[l + 2] = (byte)(abyte0[k + 2] << 6 & 255 | abyte0[k + 3] & 63);
	            k += 4;
	        }

	        if(l < abyte1.length)
	            abyte1[l] = (byte)(abyte0[k] << 2 & 255 | abyte0[k + 1] >>> 4 & 3);
	        if(++l < abyte1.length)
	            abyte1[l] = (byte)(abyte0[k + 1] << 4 & 255 | abyte0[k + 2] >>> 2 & 15);
	        return abyte1;
	  }

	  public static final String encode(byte[] paramArrayOfByte)
	  {
	    if (paramArrayOfByte == null) return null;

	    byte[] arrayOfByte1 = new byte[paramArrayOfByte.length + 2];
	    System.arraycopy(paramArrayOfByte, 0, arrayOfByte1, 0, paramArrayOfByte.length);
	    byte[] arrayOfByte2 = new byte[arrayOfByte1.length / 3 * 4];

	    int i = 0; for (int j = 0; i < paramArrayOfByte.length; j += 4) {
	      arrayOfByte2[j] = (byte)(arrayOfByte1[i] >>> 2 & 0x3F);
	      arrayOfByte2[(j + 1)] = (byte)(arrayOfByte1[(i + 1)] >>> 4 & 0xF | arrayOfByte1[i] << 4 & 0x3F);
	      arrayOfByte2[(j + 2)] = (byte)(arrayOfByte1[(i + 2)] >>> 6 & 0x3 | arrayOfByte1[(i + 1)] << 2 & 0x3F);
	      arrayOfByte2[(j + 3)] = (byte)(arrayOfByte1[(i + 2)] & 0x3F);

	      i += 3;
	    }

	    for (int k = 0; k < arrayOfByte2.length; k++) {
	      if (arrayOfByte2[k] < 26) arrayOfByte2[k] = (byte)(arrayOfByte2[k] + 65);
	      else if (arrayOfByte2[k] < 52) arrayOfByte2[k] = (byte)(arrayOfByte2[k] + 97 - 26);
	      else if (arrayOfByte2[k] < 62) arrayOfByte2[k] = (byte)(arrayOfByte2[k] + 48 - 52);
	      else if (arrayOfByte2[k] < 63) arrayOfByte2[k] = 43; else {
	        arrayOfByte2[k] = 47;
	      }
	    }

	    for (int m = arrayOfByte2.length - 1; m > paramArrayOfByte.length * 4 / 3; m--) {
	      arrayOfByte2[m] = 61;
	    }
	    return new String(arrayOfByte2);
	  }


}
