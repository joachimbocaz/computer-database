package excilys.formation.java.cbd.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import excilys.formation.java.cbd.dao.ComputerDao;
import excilys.formation.java.cbd.dao.Dao;

public class ComputerPage extends Page<Computer>{
	
	@Autowired
	ComputerDao computerDao;
	
	public ComputerPage() {
	}
	
	public ComputerPage(int numPage) {
		super(numPage);
	}


	@Override
	public List<Computer> findAllEntity(String column, String order) {
		List<Computer> computerList = new ArrayList<Computer>();
//		Dao<Computer> computerDao = new ComputerDao();
		computerList = computerDao.findAllLimite(this, column, order);
		this.setEntity(computerList);
		return computerList;
	}
	
	public List<Computer> findAllEntity2(List<Computer> computerList) {
		this.setEntity(computerList);
		return computerList;
	}
		
	public List<Computer> findAllEntity2(List<Computer> computerList, String column, String order) {
//		ComputerDao computerDao = new ComputerDao();
		computerList = computerDao.findAllLimite(this, column, order);
		this.setEntity(computerList);
		return computerList;
	}
	
	public List<Computer> findSearchEntity(String search, String column, String order) {
		List<Computer> computerList = new ArrayList<Computer>();
//		ComputerDao computerDao = new ComputerDao();
		computerList = computerDao.searchComputer(search, this, column, order);
		this.setEntity(computerList);
		return computerList;
	}
	
//	public List<Computer> findSearchEntity(String search) {
//		List<Computer> computerList = new ArrayList<Computer>();
////		ComputerDao computerDao = new ComputerDao();
//		computerList = computerDao.searchComputer(search, this);
//		this.setEntity(computerList);
//		return computerList;
//	}
	
//	public Integer getNbSearchPages(String search) {
////		computerDao = new ComputerDao();
//		Integer nbEntries = computerDao.findNbSearchComputer(search);
//		Integer nbPages = nbEntries / this.getNbElementByPage();
//		return nbEntries % this.getNbElementByPage() == 0 ? nbPages:nbPages + 1;
//	}
//	
	
	@Override
	public void printPage() {
		for(Computer elem:getEntity()) {
			System.out.println(elem);
		}
	}



}
