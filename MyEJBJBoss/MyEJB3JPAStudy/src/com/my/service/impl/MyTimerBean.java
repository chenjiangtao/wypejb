package com.my.service.impl;

import java.util.Collection;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import com.my.logger.MyDetailLogger;
import com.my.service.MyTimerBeanRemote;

@Stateless
@Remote(MyTimerBeanRemote.class)
public class MyTimerBean implements MyTimerBeanRemote{
	private static MyDetailLogger dtlLogger = MyDetailLogger.getLogger(MyTimerBean.class);
	private static String timerName = "MyTimerBean";
	@Resource       
	private TimerService timerService;

	public void scheduleTimer(long milliseconds) {
		//在调用之前先取消之前的定时任务，也就是说timerName作为主键由程序保证唯一
		cancelTimer();
		dtlLogger.info("创建定时器-begin");
		timerService.createTimer(new Date(new Date().getTime()+milliseconds),milliseconds,timerName);
		dtlLogger.info("创建定时器-end");
	} 
	
	@Timeout     
	public void doTask(Timer t) {
		dtlLogger.info("定时器发生:"+t.getInfo());
	}

	public void cancelTimer() {
		Collection<Timer> timers = timerService.getTimers();
		for (Timer timer : timers) {
			if(timerName.equals(timer.getInfo())) {
				dtlLogger.info("取消定时器-begin");
				timer.cancel();
				dtlLogger.info("取消定时器-end");
			}
		}
	}

}
