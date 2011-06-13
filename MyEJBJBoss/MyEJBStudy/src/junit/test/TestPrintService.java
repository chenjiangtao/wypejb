package junit.test;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;

import junit.framework.TestCase;

import com.ce.ejb3.dao.TLogDictDao;
import com.ce.ejb3.model.TLogDict;
import com.ce.ejb3.service.Injection;
import com.ce.ejb3.service.PrintService;

public class TestPrintService extends TestCase {
	private PrintService printService;
	private Injection injection;
	private TLogDictDao logDictDao;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		Properties props = new Properties();        
        props.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
        props.setProperty("java.naming.provider.url", "localhost:1099");
        InitialContext ctx = new InitialContext(props);
        printService = (PrintService)ctx.lookup("PrintServiceBean/remote");
        injection = (Injection)ctx.lookup("InjectionBean/remote");
        logDictDao = (TLogDictDao)ctx.lookup("TLogDictDaoBean/remote");
	}
	
	public void utestReceiveString() {
		String res = printService.receiveString("strinput");
		System.out.println(res);
	}
	
	public void utestiReceiveString() {
		String res = injection.iReceiveString("injection strinput");
		System.out.println(res);
	}
	
	public void testDao() {
		TLogDict logDict = new TLogDict();
		logDict.setShowname("ejb1-showname"+new Date());
		logDict.setClassname("ejb1-classname"+new Date());
		logDict.setMethodname("ejb1-methodname"+new Date());
		//logDictDao.insertTLogDict(logDict);
		//logDictDao.deleteTLogDict(2);
		//logDictDao.getTLogDictByID(3);
		//logDict.setId(3);
		//logDictDao.mergeTLogDict(logDict);
		//logDictDao.updateName("update-"+new Date(), 4);
		List<TLogDict> res = logDictDao.getTLogDictList();
		System.out.println(Arrays.toString(res.toArray()));
	}
}
