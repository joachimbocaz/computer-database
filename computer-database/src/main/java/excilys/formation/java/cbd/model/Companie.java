package excilys.formation.java.cbd.model;

public class Companie {
	private int id; 
	private String name;
	
	public Companie() {}
	
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
		return "Companie : " + this.name;
	}
}
