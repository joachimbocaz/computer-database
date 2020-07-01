package excilys.formation.java.cbd.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excilys.formation.java.cbd.dao.ComputerDao;
import excilys.formation.java.cbd.dto.CompanieDto;
import excilys.formation.java.cbd.dto.ComputerDto;
import excilys.formation.java.cbd.mapper.ComputerDtoMapper;
import excilys.formation.java.cbd.model.Computer;
import excilys.formation.java.cbd.service.AddComputerService;
import excilys.formation.java.cbd.validator.Validator;

/**
 * Servlet implementation class EditComputerServlet
 */
@WebServlet(name = "EditComputerServlet", urlPatterns = "/editComputer")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditComputerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

//	private String name;
//	private String introduced;
//	private String discontinued;
//	private String idCompanie;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idComputer = Integer.valueOf(request.getParameter("idComputer"));
		Computer computer = new Computer();
		ComputerDao computerDao;
		ComputerDto computerDto;
		AddComputerService addComputerService = new AddComputerService();

		try {
			computerDao = new ComputerDao();
			computerDto = new ComputerDto();
			computer = computerDao.find(idComputer);
			computerDto = ComputerDtoMapper.computerToDto(computer);
			
			List<CompanieDto> companieDtoCollection = addComputerService.listCompanieToDto();
			
			request.setAttribute("companieDtoCollection", companieDtoCollection);
			request.setAttribute("computer", computerDto);
			request.setAttribute("idComputer", idComputer);
			request.getRequestDispatcher("/WEB-INF/editComputer.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String idComputer, computerName, introduced, discontinued, companyId;

	    idComputer = request.getParameter("id");
		computerName = request.getParameter("computerName");
		introduced = request.getParameter("introduced");
		discontinued = request.getParameter("discontinued");
		companyId = request.getParameter("companyId");

		try {
			ComputerDao computerDao = new ComputerDao();
			Validator.validator(computerName, introduced, discontinued);
			ComputerDto computerDto = new ComputerDto(idComputer, computerName, companyId, introduced, discontinued);
			Computer computer = ComputerDtoMapper.dtoToComputer(computerDto);
			computerDao.update(computer);
		}catch (SQLException e) {
		 	e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			doGet(request, response);
		}
	}
}
