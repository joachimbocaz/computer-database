package excilys.formation.java.cbd.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excilys.formation.java.cbd.dao.ComputerDao;
import excilys.formation.java.cbd.dao.Dao;
import excilys.formation.java.cbd.dto.ComputerDto;
import excilys.formation.java.cbd.mapper.ComputerDtoMapper;
import excilys.formation.java.cbd.model.Computer;
import excilys.formation.java.cbd.model.ComputerPage;
import excilys.formation.java.cbd.model.Page;
//import excilys.formation.java.cbd.service.DashBoardService;

/**
 * Servlet implementation class DashBoardServlet
 */
@WebServlet(name = "DashBoardServlet", urlPatterns = "/dashboard")
public class DashBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
//	private DashBoardService dashBoardService = new DashBoardService();
	private Page<Computer> computerPage;
	private Dao<Computer> computerDao;
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
		@SuppressWarnings("unused")
		int nbPage = 1;
		int nbByPage;
		List<ComputerDto> computerDtoCollection = new ArrayList<ComputerDto>();// = dashBoardService.listComputerToDto();
		
		computerPage = new ComputerPage();
		computerPage.setNbElementByPage(20);
		computerPage.setNumPage(1);
		computerPage.setOffset();
		
		try {
			computerDao = new ComputerDao();
			nbPage = computerPage.getNbPages(computerDao);
			
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
			computerPage.findAllEntity();
			
			for(Computer computer:computerPage.getEntity()) {
				computerDtoCollection.add(ComputerDtoMapper.computerToDto(computer));
			}
			
			request.setAttribute("page", computerPage);
			request.setAttribute("nbPagesMax", nbPage);
			request.setAttribute("nbComputers", computerDtoCollection.size());
			request.setAttribute("computerDtoCollection", computerDtoCollection);
			request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
