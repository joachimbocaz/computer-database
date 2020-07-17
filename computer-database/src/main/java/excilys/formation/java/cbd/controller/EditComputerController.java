package excilys.formation.java.cbd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import excilys.formation.java.cbd.dto.CompanieDto;
import excilys.formation.java.cbd.dto.ComputerDto;
import excilys.formation.java.cbd.mapper.ComputerDtoMapper;
import excilys.formation.java.cbd.model.Computer;
import excilys.formation.java.cbd.service.AddComputerService;
import excilys.formation.java.cbd.service.implemented.ComputerServiceImpl;
import excilys.formation.java.cbd.validator.Validator;

@Controller
@RequestMapping({"/editComputer"})
public class EditComputerController {
	
	@Autowired
	private AddComputerService addComputerService;
	
	@Autowired
	private ComputerServiceImpl computerService;
	
	@GetMapping
	public String initEdit(@RequestParam(required=false, name="idComputerParam") String idComputerParam,
							Model model) {
		
		int idComputer = Integer.valueOf(idComputerParam);
		Computer computer = new Computer();
		ComputerDto computerDto;
		
		computerDto = new ComputerDto();
		computer = computerService.getComputer(idComputer);
		computerDto = ComputerDtoMapper.computerToDto(computer);
		
		List<CompanieDto> companieDtoCollection = addComputerService.listCompanieToDto();
		
		model.addAttribute("companieDtoCollection", companieDtoCollection);
		model.addAttribute("computer", computerDto);
		model.addAttribute("idComputer", idComputer);
		return "/editComputer";
	}
	
	@PostMapping
	public String editComputer(@RequestParam(required=false, name="idComputerParam") String idComputerParam,
							   @RequestParam(required=false, name="computerNameParam") String computerNameParam,
							   @RequestParam(required=false, name="introducedParam") String introducedParam,
							   @RequestParam(required=false, name="discontinuedParam") String discontinuedParam,
							   @RequestParam(required=false, name="companyIdParam") String companyIdParam) {

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
