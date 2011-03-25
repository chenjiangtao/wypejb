package junit.test;
import static org.junit.Assert.*;

import java.util.Properties;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.foshanshop.ejb3.HelloWorld;
/**
 * ��Ԫ�������������и�����ʱ������Ҫ��[Jboss��װĿ¼]/client�µ�jar���뵽��Ŀ����·���¡�
 *
 */
public class HelloWorldTest {
    protected static HelloWorld helloworld;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Properties props = new Properties();        
        props.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
        props.setProperty("java.naming.provider.url", "localhost:1099");
        InitialContext ctx = new InitialContext(props);
        helloworld = (HelloWorld)ctx.lookup("HelloWorldBean/remote");
    }

    @Test
    public void testSayHello() {
    	String result = helloworld.SayHello("��ɽ��");
    	//System.out.println(result);
        assertEquals("��ɽ��˵�����!����,�����ҵĵ�һ��EJB3Ŷ.", result);
    }
}