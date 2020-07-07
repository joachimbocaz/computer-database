package excilys.formation.java.cbd.model;

public class Companie {
	private int id; 
	private String name;
	
	public Companie() {
		this.id = 0;
		this.name = "";
	}
	
	public Companie(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id; 
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "Companie : " + this.name + " Id : " + this.id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Companie other = (Companie) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
