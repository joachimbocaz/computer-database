package excilys.formation.java.cbd.service.implemented;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import excilys.formation.java.cbd.dao.ComputerDao;
import excilys.formation.java.cbd.model.Computer;
import excilys.formation.java.cbd.model.ComputerPage;
import excilys.formation.java.cbd.service.ComputerService;

@Service
public class ComputerServiceImpl implements ComputerService{
	
	private final ComputerDao computerDao;
	
	@Autowired
	public ComputerServiceImpl(ComputerDao computerDao) {
		this.computerDao = computerDao;
	}
	
	@Override
	public List<Computer> getAllComputers(){
		return computerDao.findAll();
	}

	@Override
	public Computer getComputer(Integer id) {
		return computerDao.find(id);
	}

	@Override
	public Computer updateComputer(Computer computer) {
		return computerDao.update(computer);
	}

	@Override
	public Boolean createComputer(Computer computer) {
		return computerDao.create(computer);
	}

	@Override
	public void deleteComputer(Integer id) {
		computerDao.delete(id);
	}

	@Override
	public List<Computer> getComputersByPage(ComputerPage page, String column, String order) {
		return computerDao.findAllLimite(page, column, order);
	}

	@Override
	public Integer getComputersNbPages(ComputerPage page) {
		return page.getNbPages(computerDao.findNbElem());
	}

	@Override
	public Integer getNbComputers() {
		return computerDao.findNbElem();
	}

	@Override
	public Integer getNbComputersPagesSearch(ComputerPage page, String column, String order, String search) {
		return computerDao.findNbSearchComputer(page, column, order, search);
	}

	@Override
	public List<Computer> getComputersByPagesSearch(String search, ComputerPage page, String column, String order) {
		return computerDao.searchComputer(search, page, column, order);
	}

	@Override
	public ArrayList<String> splitOrder(String order) {
		return computerDao.splitOrder(order);
	}
	
	public Integer getNbSearchPages(ComputerPage page, String column, String order, String search) {
		Integer nbEntries = computerDao.findNbSearchComputer(page, column, order, search);
		Integer nbPages = nbEntries / page.getNbElementByPage();
		return nbEntries % page.getNbElementByPage() == 0 ? nbPages:nbPages + 1;
	}
}
