package excilys.formation.java.cbd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import excilys.formation.java.cbd.model.Computer;
import excilys.formation.java.cbd.model.ComputerPage;

public interface ComputerService {

	List<Computer> getAllComputers();
	
	Optional<Computer> getComputer(Long id);
	
	Computer updateComputer(Computer computer);
	
	Computer createComputer(Computer computer);
	
	void deleteComputer(Long id);
	
	List<Computer> getComputersByPage(ComputerPage page, String order, String ascending);
	
	Integer getComputersNbPages(ComputerPage page);
	
	int getNbComputers();
	
	Integer getNbComputersPagesSearch(ComputerPage page, String column, String order, String search);
	
	List<Computer> getComputersByPagesSearch(String search, ComputerPage page, String order, String ascending);
	
	ArrayList<String> splitOrder(String order);
	
}
