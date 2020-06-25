package excilys.formation.java.cbd.mapper;

import excilys.formation.java.cbd.dao.Dao;
import excilys.formation.java.cbd.model.Companie;

public class CompanieDtoMapper extends AbstractDtoMapper {
	private String companieId, companieName;
	
	public void entity(Dao<Companie> dao, int idCompanie) {
		Companie companie = null;
		companie = dao.find(idCompanie);
		companieId  = String.valueOf(companie.getId());
		companieName = companie.getName().equals(null) ? "null" : companie.getName();
	}

	public String getCompanieId() {
		return companieId;
	}

	public void setCompanieId(String companieId) {
		this.companieId = companieId;
	}

	public String getCompanieName() {
		return companieName;
	}

	public void setCompanieName(String companieName) {
		this.companieName = companieName;
	}
}
