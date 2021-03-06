package com.excilys.formation.java.cdb.service.implemented;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Companie> getCompaniesByPage(CompaniePage page, String order, String ascending) {
		return null;
	}

	@Override
	public Integer getCompaniesNbPages(CompaniePage page) {
		return null;
	}

	@Override
	public int getNbCompanies() {
		return (int) companieDao.count();
	}

	@Override
	public Integer getNbCompaniesPagesSearch(CompaniePage page, String column, String order, String search) {
		return null;
	}

	@Override
	public List<Companie> getCompaniesByPagesSearch(String search, CompaniePage page, String order, String ascending) {
		return null;
	}

	@Override
	public ArrayList<String> splitOrder(String order) {
		return null;
	}

}
