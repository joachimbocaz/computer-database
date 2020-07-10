package excilys.formation.java.cbd.service.implemented;

import java.sql.SQLException;
import java.util.List;

import excilys.formation.java.cbd.dao.ComputerDao;
import excilys.formation.java.cbd.model.Computer;
import excilys.formation.java.cbd.service.ComputerService;

public class ComputerServiceImpl implements ComputerService{
	
	private final ComputerDao computerDao;
	
	public ComputerServiceImpl() throws SQLException {
		this.computerDao = new ComputerDao();
	}
	
	@Override
	public List<Computer> getAllComputers(){
		return null;
	}

	@Override
	public Computer getComputer(Integer id) {
		return null;
	}

	@Override
	public Computer updateComputer(Computer computer) {
		return null;
	}

	@Override
	public Computer createComputer(Computer computer) {
		return null;
	}

	@Override
	public void deleteComputer(Integer id) {		
	}
}
