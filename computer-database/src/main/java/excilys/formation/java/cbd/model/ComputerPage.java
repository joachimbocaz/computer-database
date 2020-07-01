package excilys.formation.java.cbd.model;

import java.sql.SQLException;
import java.util.ArrayList;
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
	public List<Computer> findAllEntity() {
		List<Computer> computerList = new ArrayList<Computer>();
		try {
			Dao<Computer> computerDao = new ComputerDao();
			computerList = computerDao.findAllLimite(this.getNbElementByPage(), this.getOffSet());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.setEntity(computerList);
		return computerList;
	}
	
	@Override
	public void printPage() {
		for(Computer elem:getEntity()) {
			System.out.println(elem);
		}
	}

}
