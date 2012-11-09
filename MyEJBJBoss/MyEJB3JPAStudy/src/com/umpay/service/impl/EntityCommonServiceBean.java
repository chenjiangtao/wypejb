package com.umpay.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.umpay.dao.EntityCommonDaoBeanLocal;
import com.umpay.model.Person;
import com.umpay.service.EntityCommonServiceBeanLocal;
import com.umpay.service.EntityCommonServiceBeanRemote;

@Stateless
public class EntityCommonServiceBean implements EntityCommonServiceBeanLocal, EntityCommonServiceBeanRemote{
	@EJB (beanName="EntityCommonDaoBean")EntityCommonDaoBeanLocal entityCommonDaoBeanLocal;

	public Person findById(String personid) {
//		Person person = entityCommonDaoBeanLocal.findById(personid);
//		Set<Card> cards = person.getCards();
//		return person;
		return entityCommonDaoBeanLocal.findById(personid);
	}
}
