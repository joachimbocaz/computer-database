package com.excilys.formation.java.cdb.service;

import java.util.List;
import java.util.Optional;

import com.excilys.formation.java.cdb.model.Computer;
import com.excilys.formation.java.cdb.model.ComputerPage;

public interface ComputerService {

	List<Computer> getAllComputers();
	
	Optional<Computer> getComputer(int id);
	
	Computer updateComputer(Computer computer);
	
	Computer createComputer(Computer computer);
	
	void deleteComputer(int id);
	
	List<Computer> getComputersByPage(ComputerPage page, String order, String ascending);
	
	Integer getComputersNbPages(ComputerPage page);
	
	int getNbComputers();
	
	Integer getNbComputersPagesSearch(ComputerPage page, String column, String order, String search);
	
	List<Computer> getComputersByPagesSearch(String search, ComputerPage page, String order, String ascending);
	
	List<String> splitOrder(String order);
	
}
