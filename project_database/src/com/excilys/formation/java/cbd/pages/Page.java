package com.excilys.formation.java.cbd.pages;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.java.cbd.model.Companie;
import com.excilys.formation.java.cbd.model.Computer;

public class Page {
	private static final int NBELEM = 25;
	private int maxElem;
	private List<Computer> computerL;
	private List<Companie> companieL;
	
	public Page(int maxElem, ArrayList<Computer> computerL,  ArrayList<Companie> companieL) {
		this.maxElem = maxElem;
		this.computerL = computerL;
		this.companieL = companieL;
	}	
		
	public int getMaxElem() {
		return this.maxElem;
	}
	
	public List<Computer> getComputerL(){
		return this.computerL;
	}
	
	public List<Companie> getCompanieL(){
		return this.companieL;
	}
}
