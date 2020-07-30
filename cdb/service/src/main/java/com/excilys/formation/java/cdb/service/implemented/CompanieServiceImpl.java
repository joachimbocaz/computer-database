package com.excilys.formation.java.cdb.service.implemented;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.excilys.formation.java.cdb.dao.CompanieDao;
import com.excilys.formation.java.cdb.model.Companie;
import com.excilys.formation.java.cdb.model.CompaniePage;
import com.excilys.formation.java.cdb.service.CompanieService;

@Service
public class CompanieServiceImpl implements CompanieService  {

	@Autowired
	private CompanieDao companieDao;
	
	@Override
	public List<Companie> getAllCompanie() {
		return companieDao.findAll();
	}

	@Override
	public Optional<Companie> getCompanie(Integer id) {
		return companieDao.findById(id);
	}

	@Override
	public Companie updateCompanie(Companie companie) {
		return companieDao.save(companie);
	}

	@Override
	public Companie createCompanie(Companie companie) {
		return companieDao.save(companie);
	}

	@Override
	public void deleteCompanie(Integer id) {
		companieDao.deleteById(id);
	}

	@Override
	public List<Companie> getCompaniesByPage(CompaniePage page, String column, String order) {
		if("ASC".equals(order)) {
			return companieDao.findAll(PageRequest.of(page.getNumPage() - 1, page.getNbElementByPage(), Sort.by(column).ascending())).getContent();
		}
		return companieDao.findAll(PageRequest.of(page.getNumPage() - 1, page.getNbElementByPage(), Sort.by(column).descending())).getContent();

	}

	@Override
	public Integer getCompaniesNbPages(CompaniePage page) {
		return page.getNbPages((int) companieDao.count());
	}

	@Override
	public int getNbCompanies() {
		return (int) companieDao.count();
	}

	@Override
	public Integer getNbCompaniesPagesSearch(CompaniePage page, String column, String order, String search) {
		return companieDao.countByNameContaining(search);
	}

	@Override
	public List<Companie> getCompaniesByPagesSearch(String search, CompaniePage page, String column, String order) {
		if("ASC".equals(order)) {
			return companieDao.findByNameContaining(search, PageRequest.of(page.getOffSet(), page.getNbElementByPage() , Sort.by(column).ascending()));
		}
		
		return companieDao.findByNameContaining(search, PageRequest.of(page.getOffSet(), page.getNbElementByPage(), Sort.by(column).descending()));

	}

	@Override
	public List<String> splitOrder(String order) {
		return null;
	}

}
