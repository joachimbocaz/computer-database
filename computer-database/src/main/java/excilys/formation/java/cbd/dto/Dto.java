package excilys.formation.java.cbd.dto;

import excilys.formation.java.cbd.dao.Dao;

public abstract class Dto <T>{
	 protected Dao<T> dao = null;
	 protected T entity = null;

	 public Dto(Dao<T> dao, T entity){
	    this.dao = dao;
	    this.entity = entity;
	  }
	 
	 public abstract String createEntity();
}



