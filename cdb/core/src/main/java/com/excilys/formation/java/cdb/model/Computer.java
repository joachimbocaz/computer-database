package com.excilys.formation.java.cdb.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "computer")
public class Computer {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "introduced")
	private LocalDate introduced;
	@Column(name = "discontinued")
	private LocalDate discontinued;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Companie companie;
	
	public Computer() {}
	
	public Computer(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Computer(int id, String name, LocalDate dateIn) {
		this.id = id;
		this.name = name;
		this.introduced = dateIn;
	}
	
	public Computer(int id, String name, LocalDate dateIn, LocalDate dateOut) {
		this.id = id;
		this.name = name;
		
		if(dateIn == null) {
			this.introduced = null;
			this.discontinued = dateOut;
		}
		else if(dateOut == null) {
			this.introduced = dateIn;
			this.discontinued = null;
		}
		else {
			if(dateIn.isBefore(dateOut)) {
				this.introduced = dateIn;
				this.discontinued = dateOut;
			}
			else {
				this.introduced = null;
				this.discontinued = dateOut;
			}
		}
	}
		
	public Computer(int id, String name, LocalDate dateIn, LocalDate dateOut, Companie companie) {
		this.id = id;
		this.name = name;
		
		if(dateIn == null) {
			this.introduced = null;
			this.discontinued = dateOut;
		}
		else if(dateOut == null) {
			this.introduced = dateIn;
			this.discontinued = null;
		}
		else {
			if(dateIn.isBefore(dateOut)) {
				this.introduced = dateIn;
				this.discontinued = dateOut;
			}
			else {
				this.introduced = null;
				this.discontinued = dateOut;
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
		
	public LocalDate getDateIn() {
		return introduced;
	}

	public void setDateIn(LocalDate dateIn) {
		if(dateIn == null) {
			this.introduced = null;
		}
		else if(this.discontinued == null) {
			this.introduced = dateIn;
		}
		else if(dateIn.isBefore(this.discontinued)) {
			this.introduced = dateIn;
		}
	}

	public LocalDate getDateOut() {
		return discontinued;
	}

	public void setDateOut(LocalDate dateOut) {
		if(dateOut == null) {
			this.discontinued = null;
		}
		else if(this.introduced == null) {
			this.discontinued = dateOut;
		}
		else if(dateOut.isAfter(this.introduced)) {
			this.discontinued = dateOut;
		}
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
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
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
				" [Companie] : " + this.companie + 
				" [Date in / Date out] : " + this.introduced + "/" + this.discontinued;
	}

}
