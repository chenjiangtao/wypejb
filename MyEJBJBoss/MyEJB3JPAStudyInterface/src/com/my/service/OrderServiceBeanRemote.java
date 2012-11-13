package com.my.service;

import java.util.Map;
import javax.ejb.Remote;

@Remote
public interface OrderServiceBeanRemote{
	public void saveOrder(Map<String, String> data);
	public void saveOrderNT(Map<String, String> data);
	public void saveOrderDefault(Map<String, String> data);
}
