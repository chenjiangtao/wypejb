/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2012-12-20 上午10:42:29</p>
 * @author wangyunpeng
 * @version 1.0
 */
package com.wyp.frame.listener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/** 
 * desc:
 * <p>创建人：wangyunpeng 创建日期：2012-12-20 </p>
 * @version V1.0  
 */
public class AdminListener extends Thread{
	private ServerSocket serverSocket;
	/**
	 * wangyunpeng 2012-12-20 上午10:43:09
	 */
	public AdminListener() throws Exception {
		try {
			serverSocket = new ServerSocket(1070);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		start();
	}

	@Override
	public void run() {
		while(true) {
			try {
				Socket socket = serverSocket.accept();
				new SocketLink(socket);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
