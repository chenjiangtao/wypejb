package com.my.service;

public interface MyTimerBeanRemote{
	public void scheduleTimer(long milliseconds);
	public void cancelTimer();
}
