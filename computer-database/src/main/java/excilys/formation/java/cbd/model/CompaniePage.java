package excilys.formation.java.cbd.model;

import java.sql.SQLException;
import java.util.List;

import excilys.formation.java.cbd.dao.CompanieDao;
import excilys.formation.java.cbd.dao.Dao;

public class CompaniePage extends Page<Companie>{
	
	public CompaniePage() {
	}
	
	public CompaniePage(int numPage) {
		super(numPage);
	}

	@Override
	public List<Companie> getEntity() {
		return this.entity;
	}

	@Override
	public void setEntity() {
		try {
			Dao<Companie> companieDao = new CompanieDao();
			this.entity = companieDao.findAllLimite(NB_ELEMENTS_BY_PAGE, this.offset);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void printPage() {
		for(Companie elem:entity) {
			System.out.println(elem);
		}
	}
}
