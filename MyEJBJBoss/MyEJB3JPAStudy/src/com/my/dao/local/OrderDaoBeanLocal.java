package com.my.dao.local;

import javax.ejb.Local;
import com.my.model.Order;

@Local
public interface OrderDaoBeanLocal{
	public void saveOrder(Order order);
	public Order findByOrderId(String orderid);
	public void updateOrder(Order order);
}
