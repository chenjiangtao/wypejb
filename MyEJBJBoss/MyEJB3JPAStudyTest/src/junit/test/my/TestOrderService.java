package junit.test.my;

import java.util.HashMap;
import java.util.Map;
import junit.test.common.BaseTestEJB;
import com.my.service.OrderServiceBeanRemote;

public class TestOrderService extends BaseTestEJB{
	private OrderServiceBeanRemote orderService;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		orderService = (OrderServiceBeanRemote)ctx.lookup("OrderServiceBean/remote");
	}

	public void testSaveOrder() {
		try {
			
			Map<String, String> orderData = new HashMap<String, String>();
			orderData.put("orderid", "R121112161731002");
			orderData.put("amount", "100");
			orderData.put("trace", "T1211121617310022");
			orderService.saveOrder(orderData);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
}
