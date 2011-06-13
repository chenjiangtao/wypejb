package com.ce.ejb3.service.impl;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.ce.ejb3.service.PrintService;
import com.ce.ejb3.service.PrintServiceLocal;
@Stateless
@Remote ({PrintService.class})
@Local ({PrintServiceLocal.class})
public class PrintServiceBean implements PrintService, PrintServiceLocal {

	public String receiveString(String param) {
		System.out.println(param);
		return "receive String is : "+param;
	}
	
}
