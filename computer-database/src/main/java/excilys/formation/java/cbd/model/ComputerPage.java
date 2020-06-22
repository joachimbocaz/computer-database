package excilys.formation.java.cbd.model;

import java.util.List;

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
	public void setEntity(Dao<Computer> dao) {
		this.entity = dao.findAllLimite(NB_ELEMENTS_BY_PAGE, this.offset);
	}

	@Override
	public void printPage() {
		for(Computer elem:entity) {
			System.out.println(elem);
		}
	}
}
