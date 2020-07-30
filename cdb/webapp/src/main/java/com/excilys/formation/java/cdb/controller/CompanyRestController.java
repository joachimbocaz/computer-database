package com.excilys.formation.java.cdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.java.cdb.dto.CompanieDto;
import com.excilys.formation.java.cdb.mapper.CompanieDtoMapper;
import com.excilys.formation.java.cdb.model.Companie;
import com.excilys.formation.java.cdb.model.CompaniePage;
import com.excilys.formation.java.cdb.model.Computer;
import com.excilys.formation.java.cdb.service.implemented.CompanieServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/companie")
public class CompanyRestController {
	@Autowired
	private CompanieServiceImpl companieService;

	@GetMapping(value = {"", "/"}, produces = "application/json")
	public ResponseEntity<Object> getCompany() {
		return new ResponseEntity<Object>(companieService.getAllCompanie(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Companie> getCompanyById(@PathVariable(name="id") Integer id) {
		Companie companie = companieService.getCompanie(id).get();
		return new ResponseEntity<Companie>(companie, HttpStatus.OK);
	}
	
	@GetMapping(value = "/nbCompanie", produces = "application/json")
	public ResponseEntity<Integer> getNbCompany() {
		return new ResponseEntity<Integer>(companieService.getNbCompanies(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/companieSearch/{search}/{nbElem}/{numPage}/{column}/{order}", produces = "application/json")
	public ResponseEntity<Object> getCompanieSearch(@RequestParam(required = false, name="search", defaultValue="") String search,
													@RequestParam(required = false, name="nbElem", defaultValue="10") int nbElem,
													@RequestParam(required = false, name="numPage", defaultValue="1")  int numPage,
													@RequestParam(required = false, name="column", defaultValue="id") String column,
													@RequestParam(required = false, name="order", defaultValue="ASC") String order) {
		CompaniePage companiePage = new CompaniePage(numPage);
		companiePage.setNbElementByPage(nbElem);
		companiePage.setOffset();
		List<Companie> computerList = companieService.getCompaniesByPagesSearch(search, companiePage, column, order);
		return new ResponseEntity<Object>(computerList, HttpStatus.OK);	
	}
	
	@PostMapping(value = "/add" ,produces = "application/json")
	public ResponseEntity<String> createComputer(@RequestBody CompanieDto companieDto) {
		if(companieService.createCompanie(CompanieDtoMapper.dtoToCompanie(companieDto)) != null){

			return ResponseEntity.ok("{ok : true}");
		} else {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{error : insertion failed");
		}
	}
	
	@DeleteMapping(value = "/delete/{id}" ,produces = "application/json")
	public ResponseEntity<Computer> deleteComputer(@PathVariable int id) {
		companieService.deleteCompanie(id);
		return new ResponseEntity<Computer>(HttpStatus.OK);
	}
}
