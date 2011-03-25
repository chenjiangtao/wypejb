package junit.test;
import static org.junit.Assert.*;

import java.util.Properties;

import javax.naming.InitialContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.foshanshop.ejb3.HelloWorld;
/**
 * 单元测试用例，运行该用例时，你需要把[Jboss安装目录]/client下的jar加入到项目的类路径下。
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
    	String result = helloworld.SayHello("佛山人");
    	//System.out.println(result);
        assertEquals("佛山人说：你好!世界,这是我的第一个EJB3哦.", result);
    }
}