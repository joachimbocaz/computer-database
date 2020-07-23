package excilys.formation.java.cbd.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import excilys.formation.java.cbd.dto.CompanieDto;
import excilys.formation.java.cbd.dto.ComputerDto;
import excilys.formation.java.cbd.mapper.CompanieDtoMapper;
import excilys.formation.java.cbd.mapper.ComputerDtoMapper;
import excilys.formation.java.cbd.model.Companie;
import excilys.formation.java.cbd.model.Computer;
import excilys.formation.java.cbd.service.CompanieService;
import excilys.formation.java.cbd.service.ComputerService;
import excilys.formation.java.cbd.validator.Validator;

@Controller
@RequestMapping({"/editComputer"})
public class EditComputerController {
	
	@Autowired
	private CompanieService companieService;

	@Autowired
	private ComputerService computerService;
	
	@GetMapping
	public String initEdit(@RequestParam(required=false, name="idComputer") String idComputerParam,
							Model model) {
		
		System.out.println(idComputerParam);
		Integer idComputer = Integer.valueOf(idComputerParam);
		Computer computer = new Computer();
		ComputerDto computerDto;
		
		computerDto = new ComputerDto();
		computer = computerService.getComputer(idComputer).get();
		computerDto = ComputerDtoMapper.computerToDto(computer);
		
		List<CompanieDto> companieDtoCollection;
		List<Companie> listCompanie = companieService.getAllCompanie();
		companieDtoCollection = listCompanie.stream().map(companie -> CompanieDtoMapper.companieToDto(companie)).collect(Collectors.toList());

		model.addAttribute("companieDtoCollection", companieDtoCollection);
		model.addAttribute("computer", computerDto);
		model.addAttribute("idComputer", idComputer);
		return "/editComputer";
	}
	
	@PostMapping
	public String editComputer(@RequestParam(required=false, name="idComputer") String idComputerParam,
							   @RequestParam(required=false, name="computerName") String computerNameParam,
							   @RequestParam(required=false, name="introduced") String introducedParam,
							   @RequestParam(required=false, name="discontinued") String discontinuedParam,
							   @RequestParam(required=false, name="companyId") String companyIdParam) {

		try {
			Validator.validator(computerNameParam, introducedParam, discontinuedParam);
			ComputerDto computerDto = new ComputerDto(idComputerParam, computerNameParam, companyIdParam, introducedParam, discontinuedParam);
			Computer computer = ComputerDtoMapper.dtoToComputer(computerDto);
			computerService.updateComputer(computer);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "/editComputer";
	}
}
