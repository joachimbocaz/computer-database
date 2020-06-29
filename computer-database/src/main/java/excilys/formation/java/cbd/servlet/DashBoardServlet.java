package excilys.formation.java.cbd.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excilys.formation.java.cbd.dto.ComputerDto;
import excilys.formation.java.cbd.model.Computer;
import excilys.formation.java.cbd.service.DashBoardService;

/**
 * Servlet implementation class DashBoardServlet
 */
@WebServlet(name = "DashBoardServlet", urlPatterns = "/dashboard")
public class DashBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
//	private ComputerDtoMapper computerDtoMapper;
	private DashBoardService dashBoardService = new DashBoardService();
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
		
//		ComputerDto computer;
		List<ComputerDto> computerDtoCollection = dashBoardService.listComputerToDto();
		List<Computer> cL = new ArrayList<Computer>();
		LocalDate ld = LocalDate.of(2023, 1, 25);
		LocalDate ln = LocalDate.of(2043, 1, 25);
		Computer c1 = new Computer(0, "jojo", 2, ld, ln);
		cL.add(c1);
		request.setAttribute("computerDtoCollection", computerDtoCollection);
		request.setAttribute("nbComputer", computerDtoCollection.size());

//		request.setAttribute("cL", cL);
//		request.setAttribute("nbComputer", cL.size());


		request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
