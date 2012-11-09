package com.umpay.dao.impl;

import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.umpay.dao.EntityCommonDaoBeanLocal;
import com.umpay.dao.EntityCommonDaoBeanRemote;
import com.umpay.model.Card;
import com.umpay.model.Person;

@Stateless
public class EntityCommonDaoBean implements EntityCommonDaoBeanLocal,
		EntityCommonDaoBeanRemote{
	@PersistenceContext(unitName="MyTestEJBPU") protected EntityManager em;
	public Person findById(String personid) {
		Person person = em.find(Person.class, personid);
		Set<Card> cards = person.getCards();
		for (Card card : cards) {
			System.out.println(card);
		}
		return person;
	}

}
