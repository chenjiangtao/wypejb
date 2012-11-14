package com.my.service.impl;

import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import com.my.service.MyTimerBeanRemote;

@Stateless
@Remote(MyTimerBeanRemote.class)
public class MyTimerBean implements MyTimerBeanRemote{

	@Resource       
	private TimerService timerService;

	public void scheduleTimer(long milliseconds) {
		timerService.createTimer(new Date(new Date().getTime()+milliseconds),milliseconds,"MyTimerBean");
	} 
	
	@Timeout     
	public void doTask(Timer t) {
		System.out.println("定时器发生:"+t.getInfo());
	}

}
