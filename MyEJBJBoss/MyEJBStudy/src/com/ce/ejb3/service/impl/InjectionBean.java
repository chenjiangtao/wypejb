package com.ce.ejb3.service.impl;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.ce.ejb3.service.Injection;
import com.ce.ejb3.service.PrintServiceLocal;
@Stateless
@Remote (Injection.class)
public class InjectionBean implements Injection {
	@EJB (beanName="PrintServiceBean") PrintServiceLocal printServiceLocal;
	public String iReceiveString(String input) {
		System.out.println("iReceiveString");
		return printServiceLocal.receiveString(input);
	}

}
