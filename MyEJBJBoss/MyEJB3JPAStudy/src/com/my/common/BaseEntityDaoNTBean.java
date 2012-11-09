package com.my.common;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/** 
 * desc:无事务持久化基类
 * <p>创建人：wangyunpeng 创建日期：2012-11-9 </p>
 * @version V1.0  
 */
public abstract class BaseEntityDaoNTBean{
	@PersistenceContext(unitName="MyTestEJBPU") protected EntityManager em;
	
	public void insert(Object entity) {
		em.persist(entity);
	}
	
	public void update(Object entity) {
		
	}
}
