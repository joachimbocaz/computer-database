package com.excilys.formation.java.cdb.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.java.cdb.dto.CompanieDto;
import com.excilys.formation.java.cdb.dto.ComputerDto;
import com.excilys.formation.java.cdb.mapper.CompanieDtoMapper;
import com.excilys.formation.java.cdb.mapper.ComputerDtoMapper;
import com.excilys.formation.java.cdb.model.Companie;
import com.excilys.formation.java.cdb.model.Computer;
import com.excilys.formation.java.cdb.service.CompanieService;
import com.excilys.formation.java.cdb.service.ComputerService;
// import com.excilys.formation.java.cdb.validator.Validator;

@Controller
@RequestMapping({ "/addComputer"})
public class AddComputerController {
	@Autowired
	private CompanieService companieService;

	@Autowired
	private ComputerService computerService;
	
	@GetMapping
	public String initAddComputer(Model model) {
		
		List<CompanieDto> companieDtoCollection;
		List<Companie> listCompanie = companieService.getAllCompanie();
		companieDtoCollection = listCompanie.stream().map(companie -> CompanieDtoMapper.companieToDto(companie)).collect(Collectors.toList());

		model.addAttribute("companieDtoCollection", companieDtoCollection);
		return "/addComputer";
	}
	
	@PostMapping
	public String addComputer( @RequestParam(required=false, name="computerName") String computerName,
							  @RequestParam(required=false, name="introduced") String introduced,
							  @RequestParam(required=false, name="discontinued") String discontinued,
							  @RequestParam(required=false, name="companyId") String companyId) {
		try {
			// Validator.validator(computerName, introduced, discontinued);
			ComputerDto computerDto = new ComputerDto("0", computerName, companyId, introduced, discontinued);
			Computer computer = ComputerDtoMapper.dtoToComputer(computerDto);
			computerService.createComputer(computer);
		// }catch (SQLException e) {
		//  	e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "/addComputer";
	}
}
