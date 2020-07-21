package excilys.formation.java.cbd.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import excilys.formation.java.cbd.dto.ComputerDto;
import excilys.formation.java.cbd.mapper.ComputerDtoMapper;
import excilys.formation.java.cbd.model.Computer;
import excilys.formation.java.cbd.model.ComputerPage;
import excilys.formation.java.cbd.service.implemented.ComputerServiceImpl;

/**
 * Servlet implementation class DashBoardServlet
 */
@WebServlet(name = "toto", urlPatterns = "/oldDashboard")
public class DashBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(DashBoardServlet.class);
	private ComputerPage computerPage;
	@Autowired
	ComputerServiceImpl computerService;
	
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

	/**
     * @see HttpServ let#HttpServlet()
     */
    public DashBoardServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nbPage = 1;
		int nbByPage;
		int nbComputer;
		String column, order;
		List<ComputerDto> computerDtoCollection = new ArrayList<ComputerDto>();// = dashBoardService.listComputerToDto();
		
		computerPage = new ComputerPage();
		computerPage.setNbElementByPage(10);
		computerPage.setNumPage(1);
		computerPage.setOffset();
		
		if(request.getParameter("orderBy") != null && !request.getParameter("orderBy").equals("")) {
			ArrayList<String> styleOrder = computerService.splitOrder(request.getParameter("orderBy"));
			column = styleOrder.get(0);
			order = styleOrder.get(1);
		}
		else {
			column = "computer.id";
			order = "ASC";
		}
		
		nbPage = computerPage.getNbPages(computerService.getNbComputers());
		if(request.getParameter("nbByPage") != null) {
			nbByPage = Integer.parseInt(request.getParameter("nbByPage"));
			if(nbByPage < 1) {
				nbByPage = 1;
			}
			computerPage.setNbElementByPage(nbByPage);
		}

		if(request.getParameter("page") != null) {
			String pageWish = request.getParameter("page");
			computerPage.setNumPage(Integer.parseInt(pageWish));
		}
		
		computerPage.setOffset();
		
		if(request.getParameter("search") != null && !request.getParameter("search").equals("")) {
			
//			computerPage.findSearchEntity(request.getParameter("search"), column, order);
			computerPage.setEntity(computerService.getComputersByPagesSearch(request.getParameter("search"), computerPage, column, order));
			for(Computer computer:computerPage.getEntity()) {
				computerDtoCollection.add(ComputerDtoMapper.computerToDto(computer));
			}
			nbComputer = computerService.getNbComputersPagesSearch(computerPage, column, order, request.getParameter("search"));
			nbPage = computerPage.getNbPages(nbComputer);
		}
		else {
			computerPage.setEntity(computerService.getComputersByPage(computerPage, column, order));
			for(Computer computer:computerPage.getEntity()) {
				computerDtoCollection.add(ComputerDtoMapper.computerToDto(computer));
			}
			nbComputer = computerService.getNbComputers();
			nbPage = computerPage.getNbPages(nbComputer);
		}
		
		request.setAttribute("orderBy", request.getParameter("orderBy"));
		request.setAttribute("search", request.getParameter("search"));
		request.setAttribute("nbComputerDatabase", nbComputer);
		request.setAttribute("page", computerPage);
		request.setAttribute("nbPagesMax", nbPage);
		request.setAttribute("nbComputers", computerDtoCollection.size());
		request.setAttribute("computerDtoCollection", computerDtoCollection);
		request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("selection")!=null) {
			String[] computerIds = request.getParameter("selection").split(",");
			for(String c: computerIds) {
				try {
					computerService.deleteComputer(Long.valueOf(c));
				} catch (IllegalArgumentException e) {
					logger.error("Illegal arguments");
					logger.error("computer update not allowed",e);
				}
			}
		}
		doGet(request, response);
	}
}
