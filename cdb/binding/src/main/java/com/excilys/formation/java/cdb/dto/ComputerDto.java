package com.excilys.formation.java.cdb.dto;


public class ComputerDto{
	private String id;
	private String name;
	private String introduced;
	private String discontinued;
	private String idCompanie;
	private String nameCompany;
	
	public ComputerDto() {
	}
	
	public ComputerDto (String id, String name, String idCompanie, String introduced, String discontinued) {
		this.id = id;
		this.name = name;
		if(introduced.equals("null")) {
			this.introduced = "";
		}
		else {
			this.introduced = introduced;
		}
		if(discontinued.equals("null")) {
			this.discontinued = "";
		}
		else {
			this.discontinued = discontinued;
		}
		if(idCompanie.equals("0")) {
			this.idCompanie = "";
		}
		else {
			this.idCompanie = idCompanie;
		}
	}

	public ComputerDto (String id, String name, String idCompanie, String introduced, String discontinued, String nameCompany) {
		this.id = id;
		this.name = name;
		if(introduced.equals("null")) {
			this.introduced = "";
		}
		else {
			this.introduced = introduced;
		}
		if(discontinued.equals("null")) {
			this.discontinued = "";
		}
		else {
			this.discontinued = discontinued;
		}
		if(idCompanie.equals("0")) {
			this.idCompanie = "";
		}
		else {
			this.idCompanie = idCompanie;
			this.nameCompany = nameCompany;
		}
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		if(introduced.equals("null")) {
			this.introduced = "";
		}
		else {
			this.introduced = introduced;
		}
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		if(discontinued.equals("null")) {
			this.discontinued = "";
		}
		else {
			this.discontinued = discontinued;
		}
	}

	public String getIdCompanie() {
		return idCompanie;
	}

	public void setIdCompanie(String idCompanie) {
		if(idCompanie.equals("0")) {
			this.idCompanie = "";
		}
		else {
			this.idCompanie = idCompanie;
		}
	}

	public String getNameCompany() {
		return nameCompany;
	}

	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComputerDto other = (ComputerDto) obj;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idCompanie == null) {
			if (other.idCompanie != null)
				return false;
		} else if (!idCompanie.equals(other.idCompanie))
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
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
		return "ComputerDto [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", idCompanie=" + idCompanie + "]";
	}


}
