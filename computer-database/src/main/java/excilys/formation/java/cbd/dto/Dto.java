package excilys.formation.java.cbd.dto;

import excilys.formation.java.cbd.dao.Dao;

public abstract class Dto <T>{
	 protected Dao<T> dao = null;

	 public Dto(Dao<T> dao){
	    this.dao = dao;
	  }
	 
	 public abstract void createEntity(T entity);
}



