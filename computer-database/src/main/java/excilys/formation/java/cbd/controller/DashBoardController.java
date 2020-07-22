package excilys.formation.java.cbd.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import excilys.formation.java.cbd.dto.ComputerDto;
import excilys.formation.java.cbd.mapper.ComputerDtoMapper;
import excilys.formation.java.cbd.model.Computer;
import excilys.formation.java.cbd.model.ComputerPage;
import excilys.formation.java.cbd.service.ComputerService;

@Controller
@RequestMapping({ "/","/dashboard"})
public class DashBoardController {
	
	private ComputerPage computerPage;
	private static Logger logger = LoggerFactory.getLogger(DashBoardController.class);

	
	@Autowired
	private ComputerService computerService;
	
    @GetMapping
    public String showDashboard(@RequestParam(required=false, name="orderBy") String orderBy, 
    							@RequestParam(required=false, name="nbByPage") String nbByPageParam,
    							@RequestParam(required=false, name="page") String pageParam,
    							@RequestParam(required=false, name="search") String searchParam,
    							Model model) {
    	int nbPage = 1;
		int nbByPage, nbComputer;
		String column, order;
		List<ComputerDto> computerDtoCollection = new ArrayList<ComputerDto>();// = dashBoardService.listComputerToDto();
		
		computerPage = new ComputerPage();
		computerPage.setNbElementByPage(10);
		computerPage.setNumPage(1);
		computerPage.setOffset();
		
		if(orderBy != null && !orderBy.equals("")) {
			ArrayList<String> styleOrder = computerService.splitOrder(orderBy);
			column = styleOrder.get(0);
			order = styleOrder.get(1);
		}
		else {
			column = "computer.id";
			order = "ASC";
		}
		
		nbPage = computerPage.getNbPages(computerService.getNbComputers());
		if(nbByPageParam != null) {
			nbByPage = Integer.parseInt(nbByPageParam);
			if(nbByPage < 1) {
				nbByPage = 1;
			}
			computerPage.setNbElementByPage(nbByPage);
		}

		if(pageParam != null) {
			String pageWish = pageParam;
			computerPage.setNumPage(Integer.parseInt(pageWish));
		}
		
		computerPage.setOffset();
		
		if(searchParam != null && !searchParam.equals("")) {
			
//			computerPage.findSearchEntity(searchParam, column, order);
			computerPage.setEntity(computerService.getComputersByPagesSearch(searchParam, computerPage, column, order));
			for(Computer computer:computerPage.getEntity()) {
				computerDtoCollection.add(ComputerDtoMapper.computerToDto(computer));
			}
			nbComputer = computerService.getNbComputersPagesSearch(computerPage, column, order, searchParam);
			nbPage = computerPage.getNbPages(nbComputer);
		}
		else {
			//computerPage.setEntity(computerService.getComputersByPage(computerPage, column, order));
			for(Computer computer:computerService.getAllComputers()) {//computerPage.getEntity()) {
				computerDtoCollection.add(ComputerDtoMapper.computerToDto(computer));
			}
			nbComputer = computerService.getNbComputers();
			nbPage = computerPage.getNbPages(nbComputer);
		}
		model.addAttribute("orderBy", orderBy);
		model.addAttribute("search", searchParam);
		model.addAttribute("nbComputerDatabase", nbComputer);
		model.addAttribute("page", computerPage);
		model.addAttribute("nbPagesMax", nbPage);
		model.addAttribute("nbComputers", computerDtoCollection.size());
		model.addAttribute("computerDtoCollection", computerDtoCollection);
    	return "dashboard";
    }
    
    @PostMapping
    public String deleteComputer(@RequestParam(required=false, name="selection") String selection, Model model) {
		if(selection != null) {
			String[] computerIds = selection.split(",");
			for(String c: computerIds) {
				try {
					computerService.deleteComputer(Long.valueOf(c));
				} catch (IllegalArgumentException e) {
					logger.error("Illegal arguments");
					logger.error("computer update not allowed",e);
				}
			}
		}
    	return "redirect:/dashboard";
    }
}
