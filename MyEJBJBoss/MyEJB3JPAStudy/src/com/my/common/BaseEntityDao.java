package com.my.common;

public interface BaseEntityDao{
	public void insert(Object entity);
	public void update(Object entity);
	public Object findByKey(Class<Object> entityClass, Object primaryKey);
	public void delete(Object entity);
}
