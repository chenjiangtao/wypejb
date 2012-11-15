package junit.test.my;

import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import junit.test.common.BaseTestEJB;

public class TestMessageSender extends BaseTestEJB{
	private QueueSession session;
	private QueueConnection conn;
	private Destination destination;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		try {   
		    // 查找 QUeue类型的连接工厂。   
		    QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");   
		    conn = factory.createQueueConnection();   
		    // 创建一个 到该地址的会话， 第二个参数：消息的确认模式，这里用自动的确认模式。   
		    session = conn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);   
		    // 目标地址的连接名称。   
		    destination = (Destination) ctx.lookup("queue/myQueue");   
		} catch (Exception e) {   
		    e.printStackTrace();   
		}  
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		try {    
		    session.close();   
		    conn.close();   
		} catch (Exception e) {   
		    e.printStackTrace();   
		}
	}
	
	public void testSendMessage() {
		try { 
			 // 建立消息的发送者。   
		    MessageProducer producer = session.createProducer(destination);   
		    TextMessage msg = session.createTextMessage("您好，这是我的第一个消息驱动Bean");   
		    producer.send(msg);   
		} catch (Exception e) {   
		    e.printStackTrace();   
		}
	}
}
