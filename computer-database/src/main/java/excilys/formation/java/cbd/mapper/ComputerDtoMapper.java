package excilys.formation.java.cbd.mapper;

import excilys.formation.java.cbd.dao.Dao;
import excilys.formation.java.cbd.model.Computer;

public class ComputerDtoMapper extends AbstractDtoMapper{
	private String computerId, computerName, computerIdCompanie, computerIdDateIn, computerIdDateOut;
	
	public void entity(Dao<Computer> dao, int idComputer) {
		Computer computer = null;
		computer = dao.find(idComputer);
		computerId = String.valueOf(computer.getId());
		computerName = computer.getName().equals(null) ? "null" : computer.getName();
		computerIdCompanie = String.valueOf(computer.getManufacturer());
		computerIdDateIn = String.valueOf(computer.getDateIn());
		computerIdDateIn = String.valueOf(computer.getDateOut());
	}

	public String getCompanieId() {
		return computerId;
	}

	public void setCompanieId(String companieId) {
		this.computerId = companieId;
	}

	public String getCompanieName() {
		return computerName;
	}

	public void setCompanieName(String companieName) {
		this.computerName = companieName;
	}

	public String getComputerIdCompanie() {
		return computerIdCompanie;
	}

	public void setComputerIdCompanie(String computerIdCompanie) {
		this.computerIdCompanie = computerIdCompanie;
	}

	public String getComputerIdDateIn() {
		return computerIdDateIn;
	}

	public void setComputerIdDateIn(String computerIdDateIn) {
		this.computerIdDateIn = computerIdDateIn;
	}

	public String getComputerIdDateOut() {
		return computerIdDateOut;
	}

	public void setComputerIdDateOut(String computerIdDateOut) {
		this.computerIdDateOut = computerIdDateOut;
	}
}
