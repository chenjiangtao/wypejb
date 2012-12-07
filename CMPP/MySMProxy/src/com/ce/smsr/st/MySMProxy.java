/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2012-12-5 下午02:14:01</p>
 * @author wangyunpeng
 * @version 1.0
 */
package com.ce.smsr.st;

import com.huawei.insa2.comm.cmpp.message.CMPPDeliverMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPMessage;
import com.huawei.insa2.util.Args;
import com.huawei.smproxy.SMProxy;

/** 
 * desc:
 * <p>创建人：wangyunpeng 创建日期：2012-12-5 </p>
 * @version V1.0  
 */
public class MySMProxy extends SMProxy{

	/**
	 * wangyunpeng 2012-12-5 下午02:14:06
	 * @param args
	 */
	public MySMProxy(Args args) {
		super(args);
	}

	@Override
	public CMPPMessage onDeliver(CMPPDeliverMessage msg) {
		System.out.println("=====================================>");
		return super.onDeliver(msg);
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}

}
