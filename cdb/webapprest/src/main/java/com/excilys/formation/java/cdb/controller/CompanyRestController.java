package com.excilys.formation.java.cdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.java.cdb.model.Companie;
import com.excilys.formation.java.cdb.model.CompaniePage;
import com.excilys.formation.java.cdb.service.implemented.CompanieServiceImpl;

@RestController
public class CompanyRestController {
	@Autowired
	private static CompanieServiceImpl companieService;

	@RequestMapping(value = {"/companie", "/companie/{id}"})
	public ResponseEntity<Object> getCompany(@PathVariable(required = false, name="id") Integer id) {
		if(id != null) {
			return getCompanyById(id);
		}
		return new ResponseEntity<Object>(companieService.getAllCompanie(),HttpStatus.OK);
	}
	
	private final static ResponseEntity<Object> getCompanyById(Integer id) {
		System.out.println(id);
		if(id < 1 || id > companieService.getNbCompanies()) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
		Companie companie = companieService.getCompanie(id).get();
		return new ResponseEntity<Object>(companie, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/companieSearch/{search}/{nbElem}/{numPage}/{column}/{order}")
	public ResponseEntity<Object> getComputerSearch(@PathVariable(required = false, name="search") String search,
													@PathVariable(required = false, name="nbElem") int nbElem,
													@PathVariable(required = false, name="numPage")  int numPage,
													@PathVariable(required = false, name="column") String column,
													@PathVariable(required = false, name="order") String order) {
		CompaniePage companiePage = new CompaniePage(numPage);
		companiePage.setNbElementByPage(nbElem);
		companiePage.setOffset();
		List<Companie> computerList = companieService.getCompaniesByPagesSearch(search, companiePage, column, order);
		return new ResponseEntity<Object>(computerList, HttpStatus.OK);	
	}
}
