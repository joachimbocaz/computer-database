package excilys.formation.java.cbd.model;

import java.time.LocalDate;

public class Computer {
	private int id, idCompany;
	private String name;
	private Integer manufacturer = null;
	private LocalDate dateIn, dateOut;
	private Companie companie;
	
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
		this.id = id;
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
			}
		}
	}
		
	public Computer(int id, String name, Integer manufacturer, LocalDate dateIn, LocalDate dateOut, Companie companie) {
		this.id = id;
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
			}
		}
		
		this.setCompanie(companie);
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
	}
	
	public int getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}

	public Companie getCompanie() {
		return companie;
	}

	public void setCompanie(Companie companie) {
		this.companie = companie;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (dateIn == null) {
			if (other.dateIn != null)
				return false;
		} else if (!dateIn.equals(other.dateIn))
			return false;
		if (dateOut == null) {
			if (other.dateOut != null)
				return false;
		} else if (!dateOut.equals(other.dateOut))
			return false;
		if (id != other.id)
			return false;
		if (idCompany != other.idCompany)
			return false;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[Ordinateur] : " + this.name +
				" [ID] : " + this.id +
				" [Companie] : " + this.manufacturer + 
				" [Date in / Date out] : " + this.dateIn + "/" + this.dateOut;
	}


}
