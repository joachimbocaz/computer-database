package excilys.formation.java.cbd.service;

import java.util.List;

import excilys.formation.java.cbd.model.Computer;

public interface ComputerService {

	List<Computer> getAllComputers();
	
	Computer getComputer(Integer id);
	
	Computer updateComputer(Computer computer);
	
	Computer createComputer(Computer computer);
	
	void deleteComputer(Integer id);
	
//	List<Computer> getComputersByPage(Page page, String order, String ascending);
//	
//	Integer getComputersNbPages(Page page);
//	
//	Integer getNbComputers();
//	
//	Integer getNbComputersPagesSearch(Page page, String search);
//	
//	List<Computer> getComputersByPagesSearch(Page page, String search, String order, String ascending);
	
}
