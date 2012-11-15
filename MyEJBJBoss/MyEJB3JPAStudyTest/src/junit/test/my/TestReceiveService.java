package junit.test.my;

import junit.test.common.BaseTestEJB;
import com.my.model.RequestMsg;
import com.my.model.ResponseMsg;
import com.my.service.ReceiveParamService;

public class TestReceiveService extends BaseTestEJB{
	private ReceiveParamService receiveParamService;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		receiveParamService = (ReceiveParamService)ctx.lookup("ReceiveParamServiceBean/remote");
	}
	
	public void utestMsgTransform() {
		RequestMsg reqMsg = new RequestMsg();
		reqMsg.setReqDate("20121113");
		reqMsg.setReqTime("102314");
		reqMsg.setRpid("rpid000001");
		reqMsg.putParamValue("orderid", "000001");
		ResponseMsg retMsg = receiveParamService.transformMsg(reqMsg);
		System.out.println(retMsg);
	}
	
	public void utestMsgTransformNoReturn() {
		RequestMsg reqMsg = new RequestMsg();
		reqMsg.setReqDate("20121113");
		reqMsg.setReqTime("102314");
		reqMsg.setRpid("rpid000001");
		reqMsg.putParamValue("orderid", "000001");
		ResponseMsg retMsg = new ResponseMsg();
		retMsg.setRetCode("1010");
		receiveParamService.transformMsgNoReturn(reqMsg, retMsg);
		System.out.println(retMsg);
	}
	
	public void testMsgTransformEjbInvoke() {
		RequestMsg reqMsg = new RequestMsg();
		reqMsg.setReqDate("20121113");
		reqMsg.setReqTime("102314");
		reqMsg.setRpid("rpid000001");
		reqMsg.putParamValue("orderid", "000001");
		ResponseMsg retMsg = receiveParamService.transformMsgEjbInvokeLocalBean(reqMsg);
		System.out.println(retMsg);
		retMsg = receiveParamService.transformMsgEjbInvokeRemoteBean(reqMsg);
		System.out.println(retMsg);
	}
}
