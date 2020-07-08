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
	
	public List<Computer> findAllEntity2(List<Computer> computerList) {
		this.setEntity(computerList);
		return computerList;
	}
	
	public List<Computer> findAllEntity(String column, String order) {
		List<Computer> computerList = new ArrayList<Computer>();
		try {
			ComputerDao computerDao = new ComputerDao();
			computerList = computerDao.findAllLimite(this.getNbElementByPage(), this.getOffSet(), column, order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.setEntity(computerList);
		return computerList;
	}
	
	public List<Computer> findAllEntity2(List<Computer> computerList, String column, String order) {
		try {
			ComputerDao computerDao = new ComputerDao();
			computerList = computerDao.findAllLimite(this.getNbElementByPage(), this.getOffSet(), column, order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.setEntity(computerList);
		return computerList;
	}
	
	public List<Computer> findSearchEntity(String search, String column, String order) {
		List<Computer> computerList = new ArrayList<Computer>();
		try {
			ComputerDao computerDao = new ComputerDao();
			computerList = computerDao.searchComputer(search, this.getNbElementByPage(), this.getOffSet(), column, order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.setEntity(computerList);
		return computerList;
	}
	
	public List<Computer> findSearchEntity(String search) {
		List<Computer> computerList = new ArrayList<Computer>();
		try {
			ComputerDao computerDao = new ComputerDao();
			computerList = computerDao.searchComputer(search, this.getNbElementByPage(), this.getOffSet());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.setEntity(computerList);
		return computerList;
	}
	
	public Integer getNbSearchPages(String search) {
		ComputerDao computerDao;
		try {
			computerDao = new ComputerDao();
			Integer nbEntries = computerDao.findNbSearchComputer(search);
			Integer nbPages = nbEntries / this.getNbElementByPage();
			return nbEntries % this.getNbElementByPage() == 0 ? nbPages:nbPages + 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	@Override
	public void printPage() {
		for(Computer elem:getEntity()) {
			System.out.println(elem);
		}
	}

}
