package com.umpay.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "umpay.TEST_PERSON")
public class Person implements Serializable{

	/**
	 * long:serialVersionUID 
	 */
	private static final long serialVersionUID = -4243983197450732268L;
	@Id
	private String personid;
	private String personname;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "personid")
	private Set<Card> cards = new HashSet<Card>();
	public Person() {
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}
	
	public Set<Card> getCards() {
		return cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

	public int hashCode() {
		return (this.personid == null) ? 0 : this.personid.hashCode();
	}

	public boolean equals(Object object) {
		if (object instanceof Person) {
			final Person obj = (Person) object;
			return (this.personid != null) ? this.personid.equals(obj.personid)
					: (obj.personid == null);
		}
		return false;
	}

}
