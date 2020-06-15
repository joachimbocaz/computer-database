package com.excilys.formation.java.cbd;

import java.time.LocalDate;

public class Computer {
	private String name;
	private Companie manufacturer;
	private LocalDate dateIn, dateOut;
	
	public Computer(String name) {
		this.name = name;
	}
	
	public Computer(String name, Companie manufacturer, LocalDate dateIn, LocalDate dateOut) {
		this.name = name;
		this.manufacturer = manufacturer;
		
		if(dateIn.isBefore(dateOut)) {
			this.dateIn = dateIn;
			this.dateOut = dateOut;
		}
		else {
			this.dateIn = null;
			this.dateOut = null;
			throw new IllegalArgumentException("Date out need to be after Date in");
		}
		
	}

	public String getName() {
		return name;
	}

	public Companie getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Companie manufacturer) {
		this.manufacturer = manufacturer;
	}

	public LocalDate getDateIn() {
		return dateIn;
	}

	public void setDateIn(LocalDate dateIn) {
		if(dateIn.isBefore(this.dateOut)) {
			this.dateIn = dateIn;
		}
		else {
			throw new IllegalArgumentException("Date in need to be before Date out");
		}
	}

	public LocalDate getDateOut() {
		return dateOut;
	}

	public void setDateOut(LocalDate dateOut) {
		if(dateOut.isAfter(this.dateIn)) {
			this.dateOut = dateOut;
		}
		else {
			throw new IllegalArgumentException("Date out need to be after Date in");
		}
	}
	
}
