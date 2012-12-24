/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2012-12-20 上午10:38:00</p>
 * @author wangyunpeng
 * @version 1.0
 */
package com.wyp.frame.admin;

import java.net.ServerSocket;
import com.wyp.frame.listener.AdminListener;

/** 
 * desc:
 * <p>创建人：wangyunpeng 创建日期：2012-12-20 </p>
 * @version V1.0  
 */
public class AdminManager{
	public static void main(String[] args) {
		try {
			AdminListener adminListener = new AdminListener();
			adminListener.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
