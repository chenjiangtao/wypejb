package com.my.service;

import java.math.BigDecimal;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import com.my.dao.local.OrderDaoBeanLocal;
import com.my.model.Order;

@Stateless
public class OrderServiceBean implements OrderServiceBeanRemote{
	@EJB (beanName="OrderDaoBean")OrderDaoBeanLocal orderDaoBeanLocal;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveOrder(Map<String, String> data) {
		saveOrderProcess(data);
	}
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void saveOrderNT(Map<String, String> data) {
		saveOrderProcess(data);
	}
	
	private void saveOrderProcess(Map<String, String> data) {
		Order order = new Order();
		order.setOrderid(data.get("orderid"));
		order.setAmount(new BigDecimal(data.get("amount")));
		try {
			orderDaoBeanLocal.insert(order);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
		order.setTrace(data.get("trace"));
		try {
			orderDaoBeanLocal.update(order);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
