/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2012-12-5 下午02:16:02</p>
 * @author wangyunpeng
 * @version 1.0
 */
package com.ce.smsr.st;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.ce.util.PropsUtil;
import com.huawei.insa2.comm.cmpp.message.CMPPSubmitMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPSubmitRepMessage;
import com.huawei.insa2.util.Args;

/** 
 * desc:
 * <p>创建人：wangyunpeng 创建日期：2012-12-5 </p>
 * @version V1.0  
 */
public class MySender{
	
	public static void main(String[] args) {
		try {
			sendMsg();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void sendMsg() throws Exception {
		Map<String, String> cmppArgs = new HashMap<String, String>();
		String strKeys = PropsUtil.getMessage("CMPPConnect");
		String[] argKeys = strKeys.split(",");
		for (String key : argKeys) {
			System.out.println(key + ":" + PropsUtil.getMessage(key));
			cmppArgs.put(key, PropsUtil.getMessage(key));
		}
		Args args = new Args(cmppArgs);
		MySMProxy proxy = new MySMProxy(args);
		String[] rcvMobile = new String[]{"13800010001"};
		CMPPSubmitMessage msg = new CMPPSubmitMessage(
				1, 		// 1  @pk_Total 相同msg_Id消息总条数
				1, 		// 2  @pk_Number 相同msg_Id的消息序号
				0, 		// 3  @registered_Delivery 是否要求返回状态报告
				1, 		// 4  @msg_Level  信息级别
				"websms",	// 5  @service_Id 业务类型
				1, 		// 6  @fee_UserType 计费用户类型字段
				"", 	// 7  @fee_Terminal_Id 被计费用户的号码
				0, 		// 9  @tp_Pid GSM协议类型
				0, 		// 10  @tp_Udhi GSM协议类型
				15, 		// 11 @msg_Fmt 消息格式
				"websms",	// 12 @msg_Src 消息内容来源
				"02", 	// 13 @fee_Type 资费类别
				"10", 	// 14 @fee_Code 资费代码(以分为单位)
				new Date(System.currentTimeMillis() + (long)0xa4cb800), // 15 @valid_Time 存活有效期
				null, 	// 16 @at_Time 定时发送时间
				"888813512345678",	// 17 @src_Terminal_Id 源号码
				rcvMobile, 			// 18 @dest_Terminal_Id 接收短信的MSISDN号码
				"你好test".getBytes("GBK"), // 20 @msg_Content 消息内容
				"" 		// 21 @reserve    点播业务使用的LinkID
				);
		CMPPSubmitRepMessage repMsg = (CMPPSubmitRepMessage)proxy.send(msg);
		byte[] msgid=repMsg.getMsgId();
		System.out.println(msgid.length);
		System.out.println(repMsg);
		System.out.println("success!");
		proxy.close();
		System.out.println("close!");
	}
}
