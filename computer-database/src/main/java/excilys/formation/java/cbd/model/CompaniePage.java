package excilys.formation.java.cbd.model;

import java.sql.SQLException;
import java.util.ArrayList;
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
	public List<Companie> findAllEntity() {
		List<Companie> compagnieList = new ArrayList<Companie>();
		try {
			Dao<Companie> companieDao = new CompanieDao();
			compagnieList = companieDao.findAllLimite(this.getNbElementByPage(), this.getOffSet());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.setEntity(compagnieList);
		return compagnieList;
	}
	
	@Override
	public void printPage() {
		for(Companie elem:getEntity()) {
			System.out.println(elem);
		}
	}
}
