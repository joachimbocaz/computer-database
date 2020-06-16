package com.excilys.formation.java.cbd.model;

import java.sql.Date;

public class Computer {
	private int id;
	private String name;
	private int manufacturer;
	private Date dateIn, dateOut;
	
	public Computer() {}
	
	public Computer(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Computer(int id, String name, int manufacturer, Date dateIn, Date dateOut) {
		this.name = name;
		this.manufacturer = manufacturer;
		
		if(dateIn.before(dateOut)) {
			this.dateIn = dateIn;
			this.dateOut = dateOut;
		}
		else {
			this.dateIn = null;
			this.dateOut = null;
			throw new IllegalArgumentException("Date out need to be after Date in");
		}
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public int getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(int manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		if(dateIn.before(this.dateOut)) {
			this.dateIn = dateIn;
		}
		else {
			throw new IllegalArgumentException("Date in need to be before Date out");
		}
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		if(dateOut.after(this.dateIn)) {
			this.dateOut = dateOut;
		}
		else {
			throw new IllegalArgumentException("Date out need to be after Date in");
		}
	}
	
	@Override
	public String toString() {
		return "Ordinateur : " + this.name + "\nCompanie : " + this.manufacturer + "\nDate in/Date out : " + this.dateIn.toString() + " " + this.dateOut.toString();
	}
}
