package excilys.formation.java.cbd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import excilys.formation.java.cbd.model.Companie;
import excilys.formation.java.cbd.model.CompaniePage;

public interface CompanieService {

	List<Companie> getAllCompanie();
	
	Optional<Companie> getCompanie(Long id);
	
	Companie updateCompanie(Companie Companie);
	
	Companie createCompanie(Companie Companie);
	
	void deleteCompanie(Long id);
	
	List<Companie> getCompaniesByPage(CompaniePage page, String order, String ascending);
	
	Integer getCompaniesNbPages(CompaniePage page);
	
	int getNbCompanies();
	
	Integer getNbCompaniesPagesSearch(CompaniePage page, String column, String order, String search);
	
	List<Companie> getCompaniesByPagesSearch(String search, CompaniePage page, String order, String ascending);
	
	ArrayList<String> splitOrder(String order);
}
