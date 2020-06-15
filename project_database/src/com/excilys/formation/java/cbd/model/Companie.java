package com.excilys.formation.java.cbd.model;

public class Companie {
	private String name;
	
	public Companie(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "Companie : " + this.name;
	}
}
