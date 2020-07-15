package excilys.formation.java.cbd.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import excilys.formation.java.cbd.dao.ComputerDao;
import excilys.formation.java.cbd.dto.CompanieDto;
import excilys.formation.java.cbd.dto.ComputerDto;
import excilys.formation.java.cbd.mapper.ComputerDtoMapper;
import excilys.formation.java.cbd.model.Computer;
import excilys.formation.java.cbd.service.AddComputerService;
import excilys.formation.java.cbd.validator.Validator;

/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet(name = "AddComputerServlet", urlPatterns = "/addComputer")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AddComputerService addComputerService;
	
	@Autowired
	private ComputerDao computerDao;
	
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CompanieDto> companieDtoCollection = addComputerService.listCompanieToDto();

		request.setAttribute("companieDtoCollection", companieDtoCollection);
		request.getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request, response);   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String idComputer, computerName, introduced, discontinued, companyId;

		computerName = request.getParameter("computerName");
		introduced = request.getParameter("introduced");
		discontinued = request.getParameter("discontinued");
		companyId = request.getParameter("companyId");
		try {
			idComputer = String.valueOf(computerDao.maxId());
			Validator.validator(computerName, introduced, discontinued);
			ComputerDto computerDto = new ComputerDto(idComputer, computerName, companyId, introduced, discontinued);
			Computer computer = ComputerDtoMapper.dtoToComputer(computerDto);
			computerDao.create(computer);
		}catch (SQLException e) {
		 	e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			doGet(request, response);
		}

		request.getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request, response);
	}
}
