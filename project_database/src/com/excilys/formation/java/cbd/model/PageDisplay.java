package com.excilys.formation.java.cbd.model;

public class PageDisplay {
	private Page page;
	
	public PageDisplay(Page page) {
		this.page = page;
	}
	
	public void printPageComputer() {
		for(Computer elem:page.getComputerL()) {
			System.out.println(elem);
		}
	}
	
	public void printPageCompanie() {
		for(Companie elem:page.getCompanieL()) {
			System.out.println(elem);
		}
	}
}
