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

import com.excilys.formation.java.cdb.dto.ComputerDto;
import com.excilys.formation.java.cdb.mapper.ComputerDtoMapper;
import com.excilys.formation.java.cdb.model.Computer;
import com.excilys.formation.java.cdb.model.ComputerPage;
import com.excilys.formation.java.cdb.service.implemented.ComputerServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/computer")
public class ComputerRestController {
	@Autowired
	private ComputerServiceImpl computerService;

	@GetMapping(value = { "", "/" }, produces = "application/json")
	public List<Computer> listComputersPage() { 
		return computerService.getAllComputers();
	}
	
	@GetMapping(value = { "/nbComputer" }, produces = "application/json")
	public Integer getNbComputer() {
		return computerService.getNbComputers();
	}
	
	@GetMapping(value = { "/{id}" }, produces = "application/json")
	private ResponseEntity<Computer> getComputerById(@PathVariable Integer id) {
		if(id < 1 || id > computerService.getNbComputers()) {
			return new ResponseEntity<Computer>(HttpStatus.NOT_FOUND);
		}
		Computer computer = computerService.getComputer(id).get();
		return new ResponseEntity<Computer>(computer, HttpStatus.OK);
	}
	
	@GetMapping(value = { "/search" }, produces = "application/json")
	public ResponseEntity<Object> getComputerSearch(@RequestParam(required=false, name="search", defaultValue = "") String search,
													@RequestParam(required = false, name="nbElem", defaultValue = "10") int nbElem,
													@RequestParam(required = false, name="numPage", defaultValue = "1")  int numPage,
													@RequestParam(required = false, name="column", defaultValue = "name") String column,
													@RequestParam(required = false, name="order", defaultValue = "ASC") String order) {
		ComputerPage computerPage = new ComputerPage(numPage);
		computerPage.setNbElementByPage(nbElem);
		computerPage.setOffset();
		List<Computer> computerList = computerService.getComputersByPagesSearch(search, computerPage, column, order);
		return new ResponseEntity<Object>(computerList, HttpStatus.OK);	
	}

//	@RequestMapping(value = "/computerAdd")
//	public ResponseEntity<Computer> addComputer(@RequestParam(required = true, name="name") String name,
//												@RequestParam(required = false, name="dateIn", defaultValue = "") String dateIn,
//												@RequestParam(required = false, name="dateOut", defaultValue = "")  String dateOut,
//												@RequestParam(required = false, name="idCompanie", defaultValue = "") String idCompanie) {
//		Computer computer = new Computer();
//		String companieName = companieService.getCompanie(Integer.valueOf(idCompanie)).get().getName();
//		ComputerDto computerDto = new ComputerDto(name, dateIn, dateOut, idCompanie, companieName);
//		computer = ComputerDtoMapper.dtoToComputer(computerDto);
//		
//		return new ResponseEntity<Computer>(computerService.createComputer(computer), HttpStatus.OK);	
//	}
	
	@PostMapping(value = "/add" ,produces = "application/json")
	public ResponseEntity<String> createComputer(@RequestBody ComputerDto computerDto) {
//		System.out.println(computerDto);
//		computerService.createComputer(ComputerDtoMapper.dtoToComputer(computerDto));
//		return new ResponseEntity<Computer>(HttpStatus.OK);
		

		if(computerService.createComputer(ComputerDtoMapper.dtoToComputer(computerDto)) != null){

			return ResponseEntity.ok("{ok : true}");
		} else {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{error : insertion failed");
		}
	}
	
	@DeleteMapping(value = "/delete/{id}" ,produces = "application/json")
	public ResponseEntity<Computer> deleteComputer(@PathVariable int id) {
		computerService.deleteComputer(id);
		return new ResponseEntity<Computer>(HttpStatus.OK);
	}
}
