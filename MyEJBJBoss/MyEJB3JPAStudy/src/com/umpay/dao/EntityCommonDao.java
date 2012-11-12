package com.umpay.dao;

import com.umpay.model.Person;

public interface EntityCommonDao{
	public Person findById(String personid);
}
