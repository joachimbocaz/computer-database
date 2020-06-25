package excilys.formation.java.cbd.dto;

import excilys.formation.java.cbd.dao.Dao;
import excilys.formation.java.cbd.model.Companie;

public class CompanieDto extends Dto<Companie>{
	private Companie companie;
	
	public CompanieDto(Dao<Companie> dao, Companie companie) {
		super(dao, companie);
	}

	@Override
	public String createEntity() {
		this.dao.find(1);
		return null;
	}
	
	
}
