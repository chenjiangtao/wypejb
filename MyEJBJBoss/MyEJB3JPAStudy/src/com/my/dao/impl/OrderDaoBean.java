package com.my.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.my.dao.local.OrderDaoBeanLocal;
import com.my.model.Order;

@Stateless
public class OrderDaoBean implements OrderDaoBeanLocal{
	@PersistenceContext(unitName="MyTestEJBPU") protected EntityManager em;

	public Order findByOrderId(String orderid) {
		return em.find(Order.class, orderid);
	}

	public void saveOrder(Order order) {
		em.persist(order);
	}

	public void updateOrder(Order order) {
		em.merge(order);
	}
	
	
}
