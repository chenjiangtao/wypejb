package com.umpay.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "umpay.TEST_CARD")
public class Card implements Serializable{

	/**
	 * long:serialVersionUID 
	 */
	private static final long serialVersionUID = -444088280600184456L;
	@Id
	private String cardid;
	private String cardno;
	
	public Card() {
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public int hashCode() {
		return (this.cardid == null) ? 0 : this.cardid.hashCode();
	}

	public boolean equals(Object object) {
		if (object instanceof Card) {
			final Card obj = (Card) object;
			return (this.cardid != null) ? this.cardid.equals(obj.cardid)
					: (obj.cardid == null);
		}
		return false;
	}

}
