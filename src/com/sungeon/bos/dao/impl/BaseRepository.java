package com.sungeon.bos.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

@Repository
public abstract class BaseRepository<T extends Serializable> {
	
	public void query() {
		System.out.println("Test Query");
	}

	public abstract void save(T t);

}
