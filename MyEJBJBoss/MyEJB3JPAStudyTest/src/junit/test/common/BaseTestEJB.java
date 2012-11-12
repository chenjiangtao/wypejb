package junit.test.common;

import java.util.Properties;
import javax.naming.InitialContext;
import junit.framework.TestCase;

public class BaseTestEJB extends TestCase{
	protected InitialContext ctx;
	
	protected void setUp() throws Exception {
		try {
			Properties props = new Properties();        
			props.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
			props.setProperty("java.naming.provider.url", "localhost:1099");
			ctx = new InitialContext(props);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
