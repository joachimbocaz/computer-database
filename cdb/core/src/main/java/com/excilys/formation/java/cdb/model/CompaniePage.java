package com.excilys.formation.java.cdb.model;

import java.util.List;

public class CompaniePage extends Page<Companie>{
	
	public CompaniePage() {
	}
	
	public CompaniePage(int numPage) {
		super(numPage);
	}

	@Override
	public List<Companie> findAllEntity(String column, String order) {
//		List<Companie> compagnieList = new ArrayList<Companie>();
//		Dao<Companie> companieDao = new CompanieDao();
//		compagnieList = companieDao.findAllLimite(this.getNbElementByPage(), this.getOffSet());
//		this.setEntity(compagnieList);
//		return compagnieList;
		return null;		
	}
	
	@Override
	public void printPage() {
		for(Companie elem:getEntity()) {
			System.out.println(elem);
		}
	}
}
