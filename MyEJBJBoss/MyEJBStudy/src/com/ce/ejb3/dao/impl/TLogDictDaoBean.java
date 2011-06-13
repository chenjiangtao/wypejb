package com.ce.ejb3.dao.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ce.ejb3.dao.TLogDictDao;
import com.ce.ejb3.model.TLogDict;

@Stateless
@Remote (TLogDictDao.class)
public class TLogDictDaoBean implements TLogDictDao{
	@PersistenceContext(unitName="foshanshop") protected EntityManager em;
	public void deleteTLogDict(int logdictid) {
		TLogDict logDict = em.find(TLogDict.class, logdictid);
		if(logDict != null) em.remove(logDict);
	}

	public TLogDict getTLogDictByID(int logdictid) {
		return em.find(TLogDict.class, logdictid);
	}

	@SuppressWarnings("unchecked")
	public List<TLogDict> getTLogDictList() {
		Query query = em.createQuery("select dict from TLogDict dict");
		return (List<TLogDict>)query.getResultList();
//		return null;
	}

	public void insertTLogDict(TLogDict logdict) {
		em.persist(logdict);
	}

	public void mergeTLogDict(TLogDict logdict) {
		em.merge(logdict);
	}

	public void updateName(String newname, int logdictid) {
		TLogDict logDict = em.find(TLogDict.class, logdictid);
		if(logDict != null) logDict.setShowname(newname);
	}

}
