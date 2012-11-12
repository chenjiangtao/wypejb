package com.my.service;

import java.math.BigDecimal;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.my.dao.local.OrderDaoBeanLocal;
import com.my.model.Order;

@Stateless
public class OrderServiceBean implements OrderServiceBeanRemote{
	@EJB (beanName="OrderDaoBean")OrderDaoBeanLocal orderDaoBeanLocal;
	
	public void saveOrder(Map<String, String> data) {
		Order order = new Order();
		order.setOrderid(data.get("orderid"));
		order.setAmount(new BigDecimal(data.get("amount")));
		orderDaoBeanLocal.insert(order);
		order.setTrace(data.get("trace"));
		orderDaoBeanLocal.update(order);
	}
}
