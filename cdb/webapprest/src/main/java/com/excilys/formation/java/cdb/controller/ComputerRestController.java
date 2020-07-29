package com.excilys.formation.java.cdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.java.cdb.model.Computer;
import com.excilys.formation.java.cdb.model.ComputerPage;
import com.excilys.formation.java.cdb.service.ComputerService;

@RestController	
public class ComputerRestController {
	@Autowired
	private static ComputerService computerService;

	@RequestMapping(value = {"/","/computer", "/computer/{id}"})
	public ResponseEntity<Object> getComputer(@PathVariable(required = false, name="id") Integer id) {
		if(id != null) {
			return getComputerById(id);
		}
		return new ResponseEntity<Object>(computerService.getAllComputers(), HttpStatus.OK);
		
	}
	
	private final static ResponseEntity<Object> getComputerById(Integer id) {
		System.out.println(id);
		if(id < 1 || id > computerService.getNbComputers()) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
		Computer computer = computerService.getComputer(id).get();
		return new ResponseEntity<Object>(computer, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/computerSearch/{search}/{nbElem}/{numPage}/{column}/{order}")
	public ResponseEntity<Object> getComputerSearch(@PathVariable(required = false, name="search") String search,
													@PathVariable(required = false, name="nbElem") int nbElem,
													@PathVariable(required = false, name="numPage")  int numPage,
													@PathVariable(required = false, name="column") String column,
													@PathVariable(required = false, name="order") String order) {
		System.out.println(search+nbElem+numPage+column+order);
		ComputerPage computerPage = new ComputerPage(numPage);
		computerPage.setNbElementByPage(nbElem);
		computerPage.setOffset();
		List<Computer> computerList = computerService.getComputersByPagesSearch(search, computerPage, column, order);
		return new ResponseEntity<Object>(computerList, HttpStatus.OK);	
	}

//	private final static ResponseEntity<Object> getComputerSearch2(String search,
//															int nbElem,
//															int numPage,
//															String column,
//															String order){
//		ComputerPage computerPage = new ComputerPage(numPage);
//		computerPage.setNbElementByPage(nbElem);
//		computerPage.setOffset();
//		List<Computer> computerList = computerService.getComputersByPagesSearch(search, computerPage, column, order);
//		return new ResponseEntity<Object>(computerList, HttpStatus.OK);	
//	}
}
