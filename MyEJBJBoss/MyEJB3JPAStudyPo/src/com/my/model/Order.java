package com.my.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "umpay.TEST_ORDER")
public class Order implements Serializable{

	/**
	 * long:serialVersionUID 
	 */
	private static final long serialVersionUID = -1252928705435716365L;
	@Id
	private String orderid;
	private String trace;
	private BigDecimal amount;
	public Order() {
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getTrace() {
		return trace;
	}
	public void setTrace(String trace) {
		this.trace = trace;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public int hashCode() {
		return (this.orderid == null) ? 0 : this.orderid.hashCode();
	}

	public boolean equals(Object object) {
		if (object instanceof Order) {
			final Order obj = (Order) object;
			return (this.orderid != null) ? this.orderid.equals(obj.orderid)
					: (obj.orderid == null);
		}
		return false;
	}

}
