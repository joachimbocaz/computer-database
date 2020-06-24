package com.excilys.formation.java.cbd.model;

import java.util.List;

import com.excilys.formation.java.cbd.dao.Dao;

public class CompaniePage extends Page<Companie>{
	public CompaniePage(int numPage) {
		super(numPage);
	}

	@Override
	public List<Companie> getEntity() {
		return this.entity;
	}

	@Override
	public void setEntity(Dao<Companie> dao) {
		this.entity = dao.findAllLimite(NB_ELEMENTS_BY_PAGE, this.offset);
	}

	@Override
	public void printPage() {
		for(Companie elem:entity) {
			System.out.println(elem);
		}
	}
}
