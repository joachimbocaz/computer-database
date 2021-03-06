package com.excilys.formation.java.cdb.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Database {
	private List<Companie> companieList;// = new LinkedList<Companie>();
	private List<Computer> computerList;// = new ArrayList<Computer>();
	
	public Database(LinkedList<Companie> companieList, ArrayList<Computer> computerList) {
		this.companieList = companieList;
		this.computerList = computerList;
	}
	
	public Computer findComputer(int idComputer) {
		for(Computer elem:computerList) {
			if(elem.getId() == idComputer) {
				return elem;
			}
		}
		return null;
	}
	
	public boolean addComputer(Computer pComputer) {
		return this.computerList.add(pComputer);
	}
	
	public boolean deleteComputer(Computer pComputer) {
		return this.computerList.remove(pComputer);
	}
	
	public void addCompanie(Companie pCompanie) {
		this.companieList.add(pCompanie);
	}
	
	public void showCompanies() {
		for(Companie elem:companieList) {
			System.out.println(elem);
		}
	}
	
	public void showComputers() {
		for(Computer elem:computerList) {
			System.out.println(elem);
		}
	}

}