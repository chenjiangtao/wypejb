/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2012-12-24 上午10:20:36</p>
 * @author wangyunpeng
 * @version 1.0
 */
package com.wyp.frame.listener;

/** 
 * desc:
 * <p>创建人：wangyunpeng 创建日期：2012-12-24 </p>
 * @version V1.0  
 */
public class AdminCmd{
	private String cmd;
	private String output = "";
	
	
	/**
	 * wangyunpeng 2012-12-24 上午10:30:00
	 * @param cmd
	 */
	public AdminCmd(String cmd) {
		super();
		this.cmd = cmd;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	
	public boolean excuteCmd() {
		if("quit".equals(cmd)) {
			output = cmd;
			return true;
		} else if("showlinks".equals(cmd)) {
			output = AdminListener.showLinks();
			return false;
		} else {
			output = "can't excute this cmd : " + cmd + "!";
			return false;
		}
	}
}
