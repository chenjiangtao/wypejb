package junit.test;

import java.util.Properties;

import javax.naming.InitialContext;

import junit.framework.TestCase;

import com.umpay.model.MsgEntity;
import com.umpay.service.MsgEntityService;

public class TestMsgEntityService extends TestCase {
	private MsgEntityService msgEntityService;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		Properties props = new Properties();        
        props.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
        props.setProperty("java.naming.provider.url", "localhost:1099");
        InitialContext ctx = new InitialContext(props);
        msgEntityService = (MsgEntityService)ctx.lookup("MsgEntityServiceBean/remote");
	}
	
	public void testAddMsgEntity() {
		
		MsgEntity msg = new MsgEntity();
		String rpid = "0"+System.currentTimeMillis();
		msg.setRpid(rpid);
		byte[] data = new byte[10];
		msg.setMsg(data);
		msgEntityService.addMsgEntity(msg);
		
		Object obj = msgEntityService.findMsgEntity(rpid);
		MsgEntity res = (MsgEntity)obj;
		System.out.println(res);
	}
}
