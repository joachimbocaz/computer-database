package excilys.formation.java.cbd.dto;

import excilys.formation.java.cbd.dao.Dao;
import excilys.formation.java.cbd.model.Computer;

public class ComputerDto extends Dto<Computer>{

	public ComputerDto(Dao<Computer> dao, Computer computer) {
		super(dao, computer);
	}

	@Override
	public String createEntity() {
		return null;
	}

}
