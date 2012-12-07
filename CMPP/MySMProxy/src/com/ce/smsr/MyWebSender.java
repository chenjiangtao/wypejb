/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2012-12-5 下午07:10:53</p>
 * @author wangyunpeng
 * @version 1.0
 */
package com.ce.smsr;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.ce.util.PropsUtil;
import com.huawei.insa2.comm.cmpp.message.CMPPDeliverMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPDeliverRepMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPSubmitMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPSubmitRepMessage;
import com.huawei.insa2.util.Args;
import com.huawei.insa2.util.TypeConvert;
import com.huawei.smproxy.SMProxy;

/** 
 * desc:
 * <p>创建人：wangyunpeng 创建日期：2012-12-5 </p>
 * @version V1.0  
 */
public class MyWebSender extends SMProxy{

	private static MyWebSender instance;
	private static Args arg = null;
	
    public static MyWebSender getInstance()
    {
    	if(arg == null) {
    		Map<String, String> cmppArgs = new HashMap<String, String>();
    		String strKeys = PropsUtil.getMessage("CMPPConnect");
    		String[] argKeys = strKeys.split(",");
    		for (String key : argKeys) {
    			System.out.println(key + ":" + PropsUtil.getMessage(key));
    			cmppArgs.put(key, PropsUtil.getMessage(key));
    		}
    		arg = new Args(cmppArgs);
    		System.out.println("param init ok!");
    	}
        try {
			if(instance == null) {
			    instance = new MyWebSender();
			    System.out.println("websender init ok!");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
        return instance;
    }
	private MyWebSender() {
		super(arg);
	}
	
	
    public void OnTerminate()
    {
        System.out.println("Connection have been breaked! ");
    }

    public CMPPMessage onDeliver(CMPPDeliverMessage msg)
    {
        byte msgId[] = msg.getMsgId();
        if(msg.getRegisteredDeliver() == 1)
        {
            if(String.valueOf(msg.getStat()).equalsIgnoreCase("DELIVRD"))
            {
                System.out.println(String.valueOf(String.valueOf((new StringBuffer("\t\treceived DELIVRD message msgid=[")).append(msg.getMsgId()).append("]"))));
                long submitMsgId = TypeConvert.byte2long(msg.getStatusMsgId());
                PreparedStatement stat = null;
                try
                {
                    CMPPDeliverRepMessage cmppdeliverrepmessage = new CMPPDeliverRepMessage(msgId, 0);
                    return cmppdeliverrepmessage;
                }
                catch(Exception ex)
                {
                    CMPPDeliverRepMessage cmppdeliverrepmessage1 = new CMPPDeliverRepMessage(msgId, 9);
                    return cmppdeliverrepmessage1;
                }
            } else
            {
                return new CMPPDeliverRepMessage(msgId, 9);
            }
        } else
        {
            System.out.println(String.valueOf(String.valueOf((new StringBuffer("\t\treceived non DELIVRD message msgid=[")).append(msg.getMsgId()).append("]"))));
            return new CMPPDeliverRepMessage(msgId, 9);
        }
    }

    private boolean sendMsg(CMPPSubmitMessage msg) {
		if (msg == null)
			return false;
		CMPPSubmitRepMessage reportMsg = null;
		try {
			reportMsg = (CMPPSubmitRepMessage) super.send(msg);
			if(reportMsg.getResult() != 0)
				return false;
		} catch (IOException ex) {
			System.out.println("send message error!");
			ex.printStackTrace();
			return false;
		}
		return true;
	}
    
    public boolean sendMsg(String msg, String[] rcvMobile) {
		if (msg == null || msg.length() == 0)
			return false;
		if(rcvMobile == null || rcvMobile.length == 0)
			return false;
		System.out.println("message is: [" + msg +"]");
		System.out.println("mobiles is: [" + Arrays.toString(rcvMobile) +"]");
		CMPPSubmitMessage cmppMsg = constructMessage(msg, rcvMobile);
		CMPPSubmitRepMessage reportMsg = null;
		try {
			reportMsg = (CMPPSubmitRepMessage) super.send(cmppMsg);
			if(reportMsg.getResult() != 0) {
				System.out.println("server return failed!");
				return false;
			}
		} catch (IOException ex) {
			System.out.println("send message error!");
			ex.printStackTrace();
			return false;
		}
		System.out.println("send message ok!");
		return true;
	}
    
	private CMPPSubmitMessage constructMessage(String msg, String[] rcvMobile) {
		CMPPSubmitMessage message = new CMPPSubmitMessage(
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
				//new String[]{} 当发送号码设置为空数据，则服务器返回非零
				rcvMobile, 			// 18 @dest_Terminal_Id 接收短信的MSISDN号码
				msg.getBytes(), // 20 @msg_Content 消息内容
				"" 		// 21 @reserve    点播业务使用的LinkID
				);
		return message;
	}
}
