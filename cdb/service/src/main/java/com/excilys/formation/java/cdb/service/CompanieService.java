package com.excilys.formation.java.cdb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.formation.java.cdb.model.Companie;
import com.excilys.formation.java.cdb.model.CompaniePage;

public interface CompanieService {

	List<Companie> getAllCompanie();
	
	Optional<Companie> getCompanie(Integer id);
	
	Companie updateCompanie(Companie Companie);
	
	Companie createCompanie(Companie Companie);
	
	void deleteCompanie(Integer id);
	
	List<Companie> getCompaniesByPage(CompaniePage page, String order, String ascending);
	
	Integer getCompaniesNbPages(CompaniePage page);
	
	int getNbCompanies();
	
	Integer getNbCompaniesPagesSearch(CompaniePage page, String column, String order, String search);
	
	List<Companie> getCompaniesByPagesSearch(String search, CompaniePage page, String order, String ascending);
	
	ArrayList<String> splitOrder(String order);
}
