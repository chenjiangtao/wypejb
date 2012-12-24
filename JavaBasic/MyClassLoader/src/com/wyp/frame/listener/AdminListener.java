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
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/** 
 * desc:
 * <p>创建人：wangyunpeng 创建日期：2012-12-20 </p>
 * @version V1.0  
 */
public class AdminListener extends Thread{
	private ServerSocket serverSocket;
	protected static Hashtable<SocketLink, Date> links = new Hashtable<SocketLink, Date>();
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
	}

	@Override
	public void run() {
		while(true) {
			try {
				checkLinks();
				Socket socket = serverSocket.accept();
				SocketLink link = new SocketLink(socket);
				links.put(link, new Date());
				link.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static synchronized void checkLinks() {
		try {
			Set<SocketLink> keys = links.keySet();
			List<SocketLink> delKeys = new ArrayList<SocketLink>();
			for (SocketLink socketLink : keys) {
				if(socketLink.isClosed()) {
					delKeys.add(socketLink);
				}
			}
			for (SocketLink del : delKeys) {
				links.remove(del);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String showLinks() {
		checkLinks();
		StringBuffer buf = new StringBuffer();
		Set<SocketLink> keys = links.keySet();
		for (SocketLink socketLink : keys) {
			buf.append(socketLink.toString()).append("\r\n");
		}
		buf.append("total : " + links.size());
		return buf.toString();
	}
}
