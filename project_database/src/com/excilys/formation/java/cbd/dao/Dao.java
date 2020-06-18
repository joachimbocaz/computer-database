package com.excilys.formation.java.cbd.dao;

import java.util.List;

import com.excilys.formation.java.cbd.service.ConnectDB;

public abstract class Dao<T> {
	 protected ConnectDB connect = null;
	   
	 public Dao(ConnectDB conn){
	    this.connect = conn;
	  }
	  
	 public abstract boolean create(T obj);

	 public abstract boolean delete(T obj);

	 public abstract T update(T obj);
	
	 public abstract T find(int id);
	   
	 public abstract List<T> findAll();
	   
	 public abstract List<T> findAllLimite(int limite, int offset);
	
	 public abstract int findNbElem();
}
