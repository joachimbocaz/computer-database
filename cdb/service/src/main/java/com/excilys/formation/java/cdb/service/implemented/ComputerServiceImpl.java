package com.excilys.formation.java.cdb.service.implemented;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.excilys.formation.java.cdb.dao.ComputerDao;
import com.excilys.formation.java.cdb.model.Computer;
import com.excilys.formation.java.cdb.model.ComputerPage;
import com.excilys.formation.java.cdb.service.ComputerService;

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
	public Optional<Computer> getComputer(int id) {
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
	public void deleteComputer(int id) {
		computerDao.deleteById(id);
	}

	@Override
	public List<Computer> getComputersByPage(ComputerPage page, String column, String order) {
//		return computerDao.findAllLimite(page, column, order);
		//return computerDao.findAll();
		if("ASC".equals(order)) {
			return computerDao.findAll(PageRequest.of(page.getNumPage() - 1, page.getNbElementByPage(), Sort.by(column).ascending())).getContent();
		}
		return computerDao.findAll(PageRequest.of(page.getNumPage() - 1, page.getNbElementByPage(), Sort.by(column).descending())).getContent();
	}

	@Override
	public Integer getComputersNbPages(ComputerPage page) {
		return page.getNbPages((int) computerDao.count());
	}

	@Override
	public int getNbComputers() {
		return (int) computerDao.count();
	}

	@Override
	public Integer getNbComputersPagesSearch(ComputerPage page, String column, String order, String search) {
		return computerDao.countByNameContaining(search);
	}

	@Override
	public List<Computer> getComputersByPagesSearch(String search, ComputerPage page, String column, String order) {
//		return computerDao.findByNameContaining(search, );
		if("ASC".equals(order)) {
			return computerDao.findByNameContaining(search, PageRequest.of(page.getOffSet(), page.getNbElementByPage() , Sort.by(column).ascending()));
		}
		
		return computerDao.findByNameContaining(search, PageRequest.of(page.getOffSet(), page.getNbElementByPage(), Sort.by(column).descending()));
	}

	@Override
	public List<String> splitOrder(String order) {
		return computerDao.splitOrder(order);
	}
	
	public Integer getNbSearchPages(ComputerPage page, String column, String order, String search) {
//		Integer nbEntries = computerDao.findNbSearchComputer(page, column, order, search);
//		Integer nbPages = nbEntries / page.getNbElementByPage();
//		return nbEntries % page.getNbElementByPage() == 0 ? nbPages:nbPages + 1;
		return null;
	}
}
