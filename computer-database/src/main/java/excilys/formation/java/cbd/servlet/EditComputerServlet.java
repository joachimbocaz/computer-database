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
import excilys.formation.java.cbd.service.implemented.ComputerServiceImpl;
import excilys.formation.java.cbd.validator.Validator;

/**
 * Servlet implementation class EditComputerServlet
 */
@WebServlet(name = "EditComputerServlet", urlPatterns = "/oldEditComputer")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Autowired
	private ComputerDao computerDao;
	
	@Autowired
	private AddComputerService addComputerService;
	
	@Autowired
	private ComputerServiceImpl computerService;
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
    
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
		
		ComputerDto computerDto;
		


		computerDto = new ComputerDto();
		computer = computerService.getComputer(idComputer);
		computerDto = ComputerDtoMapper.computerToDto(computer);
		
		List<CompanieDto> companieDtoCollection = addComputerService.listCompanieToDto();
		
		request.setAttribute("companieDtoCollection", companieDtoCollection);
		request.setAttribute("computer", computerDto);
		request.setAttribute("idComputer", idComputer);
		request.getRequestDispatcher("/WEB-INF/editComputer.jsp").forward(request, response);

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
			Validator.validator(computerName, introduced, discontinued);
			ComputerDto computerDto = new ComputerDto(idComputer, computerName, companyId, introduced, discontinued);
			Computer computer = ComputerDtoMapper.dtoToComputer(computerDto);
			computerService.updateComputer(computer);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			doGet(request, response);
		}
	}
}
