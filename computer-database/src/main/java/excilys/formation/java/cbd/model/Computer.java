package excilys.formation.java.cbd.model;

import java.time.LocalDate;

public class Computer {
	private int id;
	private String name;
	private Integer manufacturer = null;
	private LocalDate dateIn, dateOut;
	
	public Computer() {}
	
	public Computer(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Computer(int id, String name, Integer manufacturer) {
		this.id = id;
		this.name = name;
		this.manufacturer = manufacturer;
	}
	
	public Computer(int id, String name, Integer manufacturer, LocalDate dateIn) {
		this.id = id;
		this.name = name;
		this.manufacturer = manufacturer;
		this.dateIn = dateIn;
	}
	
	public Computer(int id, String name, Integer manufacturer, LocalDate dateIn, LocalDate dateOut) {
		this.name = name;
		this.manufacturer = manufacturer;
		
		if(dateIn == null) {
			this.dateIn = null;
			this.dateOut = dateOut;
		}
		else if(dateOut == null) {
			this.dateIn = dateIn;
			this.dateOut = null;
		}
		else {
			if(dateIn.isBefore(dateOut)) {
				this.dateIn = dateIn;
				this.dateOut = dateOut;
			}
			else {
				this.dateIn = null;
				this.dateOut = dateOut;
				//throw new IllegalArgumentException("Date out need to be after Date in");
			}
		}
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public Integer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Integer manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public LocalDate getDateIn() {
		return dateIn;
	}

	public void setDateIn(LocalDate dateIn) {
		if(dateIn == null) {
			this.dateIn = null;
		}
		else if(this.dateOut == null) {
			this.dateIn = dateIn;
		}
		else if(dateIn.isBefore(this.dateOut)) {
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
		if(dateOut == null) {
			this.dateOut = null;
		}
		else if(this.dateIn == null) {
			this.dateOut = dateOut;
		}
		else if(dateOut.isAfter(this.dateIn)) {
			this.dateOut = dateOut;
		}
		else {
			throw new IllegalArgumentException("Date out need to be after Date in");
		}
	}
	
	@Override
	public String toString() {
		return "[Ordinateur] : " + this.name +
				" [ID] : " + this.id +
				" [Companie] : " + this.manufacturer + 
				" [Date in / Date out] : " + this.dateIn + "/" + this.dateOut;
	}
}