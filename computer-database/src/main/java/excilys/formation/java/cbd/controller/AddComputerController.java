package excilys.formation.java.cbd.controller;

import java.sql.SQLException;
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
@RequestMapping({ "/addComputer"})
public class AddComputerController {
	@Autowired
	private AddComputerService addComputerService;
	
	@Autowired
	ComputerServiceImpl computerService;
	
	@GetMapping
	public String initAddComputer(Model model) {
		List<CompanieDto> companieDtoCollection = addComputerService.listCompanieToDto();

		model.addAttribute("companieDtoCollection", companieDtoCollection);
		return "/addComputer";
	}
	
	@PostMapping
	public String addComputer( @RequestParam(required=false, name="computerName") String computerName,
							  @RequestParam(required=false, name="introduced") String introduced,
							  @RequestParam(required=false, name="discontinued") String discontinued,
							  @RequestParam(required=false, name="companyId") String companyId) {
		try {
			Validator.validator(computerName, introduced, discontinued);
			ComputerDto computerDto = new ComputerDto("0", computerName, companyId, introduced, discontinued);
			Computer computer = ComputerDtoMapper.dtoToComputer(computerDto);
			computerService.createComputer(computer);
		}catch (SQLException e) {
		 	e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "/addComputer";
	}
}
