package com.umpay.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.umpay.dao.MsgEntiryDaoBeanLocal;
import com.umpay.model.MsgEntity;

@Stateless
public class MsgEntityDaoBean implements MsgEntiryDaoBeanLocal  {
	@PersistenceContext(unitName="MyTestEJBPU") protected EntityManager em;
	public void add(Object entity) {
		em.persist(entity);
	}
	public Object find(String rpid) {
		return em.find(MsgEntity.class, rpid);
	}

}
