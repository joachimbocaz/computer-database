package excilys.formation.java.cbd.model;

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
	private int idCompany;
	@Column(name = "name")
	private String name;
	private Integer manufacturer = null;
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
	
	public Computer(int id, String name, Integer manufacturer) {
		this.id = id;
		this.name = name;
		this.manufacturer = manufacturer;
	}
	
	public Computer(int id, String name, Integer manufacturer, LocalDate dateIn) {
		this.id = id;
		this.name = name;
		this.manufacturer = manufacturer;
		this.introduced = dateIn;
	}
	
	public Computer(int id, String name, Integer manufacturer, LocalDate dateIn, LocalDate dateOut) {
		this.id = id;
		this.name = name;
		this.manufacturer = manufacturer;
		
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
		
	public Computer(int id, String name, Integer manufacturer, LocalDate dateIn, LocalDate dateOut, Companie companie) {
		this.id = id;
		this.name = name;
		this.manufacturer = manufacturer;
		
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
	
	public Integer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Integer manufacturer) {
		this.manufacturer = manufacturer;
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
				" [Companie] : " + this.companie + 
				" [Date in / Date out] : " + this.introduced + "/" + this.discontinued;
	}

}
