package junit.test.my;

import junit.test.common.BaseTestEJB;
import com.my.service.MyTimerBeanRemote;

public class TestTimerService extends BaseTestEJB{
	private MyTimerBeanRemote myTimerService ;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		myTimerService = (MyTimerBeanRemote)ctx.lookup("MyTimerBean/remote"); 
	}

	
	public void utestStartTimer() {
		try { 
			myTimerService.scheduleTimer(5000); 
		} catch (Exception e) {   
		    e.printStackTrace();   
		}
	}
	
	public void testCancelTimer() {
		try { 
			myTimerService.cancelTimer();
		} catch (Exception e) {   
		    e.printStackTrace();   
		}
	}
}
