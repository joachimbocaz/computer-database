package excilys.formation.java.cbd.service;

import java.util.ArrayList;
import java.util.List;

import excilys.formation.java.cbd.model.Computer;
import excilys.formation.java.cbd.model.ComputerPage;

public interface ComputerService {

	List<Computer> getAllComputers();
	
	Computer getComputer(Integer id);
	
	Computer updateComputer(Computer computer);
	
	Computer createComputer(Computer computer);
	
	void deleteComputer(Integer id);
	
	List<Computer> getComputersByPage(ComputerPage page, String order, String ascending);
	
	Integer getComputersNbPages(ComputerPage page);
	
	Integer getNbComputers();
	
	Integer getNbComputersPagesSearch(String search);
	
	List<Computer> getComputersByPagesSearch(String search, ComputerPage page, String order, String ascending);
	
	ArrayList<String> splitOrder(String order);
	
}
