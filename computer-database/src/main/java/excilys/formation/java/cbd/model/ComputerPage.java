package excilys.formation.java.cbd.model;

import java.sql.SQLException;
import java.util.List;

import excilys.formation.java.cbd.dao.ComputerDao;
import excilys.formation.java.cbd.dao.Dao;

public class ComputerPage extends Page<Computer>{
	
	public ComputerPage() {
	}
	
	public ComputerPage(int numPage) {
		super(numPage);
	}

	@Override
	public List<Computer> getEntity() {
		return this.entity;
	}

	@Override
	public void setEntity() {
		try {
			Dao<Computer> computerDao = new ComputerDao();
			this.entity = computerDao.findAllLimite(NB_ELEMENTS_BY_PAGE, this.offset);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void printPage() {
		for(Computer elem:entity) {
			System.out.println(elem);
		}
	}
}
