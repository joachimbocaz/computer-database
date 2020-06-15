package com.excilys.formation.java.cbd;

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
	
	public void addComputer(Computer pComputer) {
		this.computerList.add(pComputer);
	}
	
	public void deleteComputer(Computer pComputer) {
		this.computerList.remove(pComputer);
	}
}