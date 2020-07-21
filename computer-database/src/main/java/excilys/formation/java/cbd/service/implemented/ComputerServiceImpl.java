package excilys.formation.java.cbd.service.implemented;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import excilys.formation.java.cbd.dao.ComputerDao;
import excilys.formation.java.cbd.model.Computer;
import excilys.formation.java.cbd.model.ComputerPage;
import excilys.formation.java.cbd.service.ComputerService;

@Service
public class ComputerServiceImpl implements ComputerService{
	
	@Autowired
	private ComputerDao computerDao;
	
	@Autowired
	public ComputerServiceImpl(ComputerDao computerDao) {
		this.computerDao = computerDao;
	}
	
	@Override
	public List<Computer> getAllComputers(){
		return computerDao.findAll();
	}

	@Override
	public Optional<Computer> getComputer(Long id) {
		System.out.println("tototototototo");
		return computerDao.findById(id);
	}

	@Override
	public Computer updateComputer(Computer computer) {
		return computerDao.save(computer);
	}

	@Override
	public Computer createComputer(Computer computer) {
		return computerDao.save(computer);
	}

	@Override
	public void deleteComputer(Long id) {
		computerDao.deleteById(id);
	}

	@Override
	public List<Computer> getComputersByPage(ComputerPage page, String column, String order) {
//		return computerDao.findAllLimite(page, column, order);
		return null;
	}

	@Override
	public Integer getComputersNbPages(ComputerPage page) {
//		return page.getNbPages(computerDao.findNbElem());
		return null;
	}

	@Override
	public int getNbComputers() {
		return (int) computerDao.count();
	}

	@Override
	public Integer getNbComputersPagesSearch(ComputerPage page, String column, String order, String search) {
//		return computerDao.findNbSearchComputer(page, column, order, search);
		return null;
	}

	@Override
	public List<Computer> getComputersByPagesSearch(String search, ComputerPage page, String column, String order) {
//		return computerDao.searchComputer(search, page, column, order);
		return null;
	}

	@Override
	public ArrayList<String> splitOrder(String order) {
//		return computerDao.splitOrder(order);
		return null;
	}
	
	public Integer getNbSearchPages(ComputerPage page, String column, String order, String search) {
//		Integer nbEntries = computerDao.findNbSearchComputer(page, column, order, search);
//		Integer nbPages = nbEntries / page.getNbElementByPage();
//		return nbEntries % page.getNbElementByPage() == 0 ? nbPages:nbPages + 1;
		return null;
	}
}
