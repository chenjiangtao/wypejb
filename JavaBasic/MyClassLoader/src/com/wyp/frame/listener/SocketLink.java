/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2012-12-20 上午11:15:28</p>
 * @author wangyunpeng
 * @version 1.0
 */
package com.wyp.frame.listener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.Socket;

/** 
 * desc:
 * <p>创建人：wangyunpeng 创建日期：2012-12-20 </p>
 * @version V1.0  
 */
public class SocketLink extends Thread{
	private Socket socket;

	/**
	 * wangyunpeng 2012-12-20 上午11:16:10
	 * @param socket
	 */
	public SocketLink(Socket socket) {
		this.socket = socket;
		start();
	}

	@Override
	public void run() {
		try {
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			OutputStreamWriter writer = new OutputStreamWriter(out);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String tempString = null;
			writer.write("\r\n=>:");
			writer.flush();
			while((tempString=reader.readLine()) != null) {
				if("quit".equals(tempString))
					break;
				writer.write("<=:");
				System.out.println(tempString);
				writer.write(tempString);
				writer.write("\r\n=>:");
				writer.flush();
			}
			out.close();
			in.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
