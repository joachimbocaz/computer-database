package excilys.formation.java.cbd.model;

import java.util.List;

import excilys.formation.java.cbd.dao.Dao;

public abstract class Page <T>{
	protected List<T> entity;
	protected static final int NB_ELEMENTS_BY_PAGE = 3;
	private int numPage;
	protected int offset;
	
	public Page() {
		
	}
	
	public Page(int numPage) {
		this.numPage = numPage;
	}
	
	public int getOffSet() {
		return this.offset;
	}
	
	public int getNumPage() {
		return numPage;
	}

	public void setNumPage(int numPage) {
		this.numPage = numPage;
	}
	
	public void setOffset() {
		this.offset = (this.numPage - 1) * NB_ELEMENTS_BY_PAGE;
	}
	
	public Integer getNbPages(Dao<T> dao) {
		Integer nbEntries = dao.findNbElem();
		Integer nbPages = nbEntries/NB_ELEMENTS_BY_PAGE;
		return nbEntries%NB_ELEMENTS_BY_PAGE == 0?nbPages:nbPages+1;
	}
	
	public abstract List<T> getEntity();
	
	public abstract void setEntity(Dao<T> dao);
	
	public abstract void printPage();
}
