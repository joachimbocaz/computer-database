package com.excilys.formation.java.cbd.dao;

import java.sql.Connection;
import java.util.List;

public abstract class Dao<T> {
	 protected Connection connect = null;
	   
	  public Dao(Connection conn){
	    this.connect = conn;
	  }
	  
	   public abstract boolean create(T obj);

	   public abstract boolean delete(T obj);

	   public abstract boolean update(T obj);

	   public abstract T find(int id);
	   
	   public abstract List<T> findAll();
}
