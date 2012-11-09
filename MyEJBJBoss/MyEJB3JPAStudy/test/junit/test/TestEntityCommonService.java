package junit.test;

import java.util.Properties;
import java.util.Set;
import javax.naming.InitialContext;
import junit.framework.TestCase;
import com.umpay.dao.EntityCommonDao;
import com.umpay.model.Card;
import com.umpay.model.Person;
import com.umpay.service.EntityCommonService;

public class TestEntityCommonService extends TestCase {
	private EntityCommonService entityCommonService;
	private EntityCommonDao entityCommonDao;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		Properties props = new Properties();        
        props.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
        props.setProperty("java.naming.provider.url", "localhost:1099");
        InitialContext ctx = new InitialContext(props);
        entityCommonService = (EntityCommonService)ctx.lookup("EntityCommonServiceBean/remote");
        entityCommonDao = (EntityCommonDao)ctx.lookup("EntityCommonDaoBean/remote");
	}
	
	public void utestFindById() {
		//Person person = entityCommonDao.findById("001");
		Person person = entityCommonService.findById("001");
		Set<Card> cards = person.getCards();
		System.out.println(cards.size());
	}
	
}
