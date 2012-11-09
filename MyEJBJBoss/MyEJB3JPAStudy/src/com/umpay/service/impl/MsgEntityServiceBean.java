package com.umpay.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.umpay.dao.MsgEntiryDaoBeanLocal;
import com.umpay.service.MsgEntityServiceBeanRemote;

@Stateless
public class MsgEntityServiceBean implements MsgEntityServiceBeanRemote {
	@EJB (beanName="MsgEntityDaoBean")MsgEntiryDaoBeanLocal daoBean;

	public void addMsgEntity(Object msg) {
		daoBean.add(msg);
	}

	public Object findMsgEntity(String rpid) {
		return daoBean.find(rpid);
	}
	
}
