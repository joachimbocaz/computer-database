package com.excilys.formation.java.cdb.model;

import java.util.List;

public abstract class Page <T>{
	private List<T> entity;
	private int NB_ELEMENTS_BY_PAGE = 10;
	private int numPage;
	private int offset;
	
	public Page() {
		
	}
	
	public Page(int numPage) {
		this.numPage = numPage;
	}
	
	public int getOffSet() {
		return this.offset;
	}
	
	public int getNumPage() {
		return this.numPage;
	}
	
	public void setNumPage(int numPage) {
		this.numPage = numPage;
	}
	
	public void setOffset() {
		this.offset = (this.numPage - 1) * NB_ELEMENTS_BY_PAGE;
	}
	
	public Integer getNbPages(int nbElem) {
		Integer nbEntries = nbElem;
		Integer nbPages = nbEntries/NB_ELEMENTS_BY_PAGE;
		return nbEntries % NB_ELEMENTS_BY_PAGE == 0?nbPages:nbPages+1;
	}
	
	public List<T> getEntity(){
		return this.entity;
	}
	
	public int getNbElementByPage() {
		return this.NB_ELEMENTS_BY_PAGE;
	}
	
	public void setNbElementByPage(int NB_ELEMENTS_BY_PAGE) {
		this.NB_ELEMENTS_BY_PAGE = NB_ELEMENTS_BY_PAGE;
	}
	
	public void setEntity(List<T> entity) {
		this.entity = entity;
	}
		
	public abstract List<T> findAllEntity(String column, String order);

	
	public abstract void printPage();

	@Override
	public String toString() {
		return "Page [NB_ELEMENTS_BY_PAGE=" + NB_ELEMENTS_BY_PAGE + ", numPage=" + numPage + ", offset=" + offset + "nb entity" + entity.size() + "]";
	}
	
	
}
