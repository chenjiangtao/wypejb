package junit.test;

import com.my.model.RequestMsg;

public class TestTmp{

	/**
	 * desc:
	 * <p>创建人：wangyunpeng , 2012-11-13 下午04:08:14</p>
	 * @param args
	 */
	public static void main(String[] args) {
		int a = 5;
		testParam(a);
		System.out.println(a);
		
		Integer b = 7;
		testParamInteger(b);
		System.out.println(b);
		
		RequestMsg reqMsg = new RequestMsg();
		reqMsg.setRpid("1111");
		System.out.println(reqMsg);
		testParamRequestMsg(reqMsg);
		System.out.println(reqMsg);
	}

	private static void testParamRequestMsg(RequestMsg reqMsg) {
		reqMsg = new RequestMsg();
		reqMsg.setRpid("2222");
	}
	
	private static void testParam(int i) {
		i = 10;
	}
	
	private static void testParamInteger(Integer i) {
		i = 10;
	}
}
