/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2012-12-20 上午11:15:28</p>
 * @author wangyunpeng
 * @version 1.0
 */
package com.wyp.frame.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
				AdminCmd cmd = new AdminCmd(tempString);
				boolean isExit = cmd.excuteCmd();
				writer.write("=========================================================\r\n");
				writer.write(cmd.getOutput());
				writer.write("\r\n=========================================================");
				writer.write("\r\n=>:");
				writer.flush();
				if(isExit)
					break;
			}
			out.close();
			in.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isClosed() {
		if(null != socket) {
			return socket.isClosed();
		} else {
			return true;
		}
	}
	
	public void close() {
		try {
			if(socket != null)
				socket.close();
			socket = null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			socket = null;
		}
	}

	@Override
	public String toString() {
		if(socket != null)
			return "[name:"+this.getName()+", ip:"+this.socket+"]";
		else
			return "[name:"+this.getName()+"]";
	}
	
	
}
