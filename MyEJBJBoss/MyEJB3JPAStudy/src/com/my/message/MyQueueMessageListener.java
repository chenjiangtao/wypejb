package com.my.message;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import com.my.logger.MyDetailLogger;


@MessageDriven(activationConfig = {  
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),  
	@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/myQueue"),  
	// 当我们使用的是容器来管理事务的时候，acknowledgeMode这个属性设置也没什么意义，    
	// 这里可以省略掉 自动确认模式，也可以不要，。   
	@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})  

public class MyQueueMessageListener implements MessageListener{
	private static MyDetailLogger dtlLogger = MyDetailLogger.getLogger(MyQueueMessageListener.class);
	public void onMessage(Message message) {
		dtlLogger.info(message.toString());
	}

}
