package excilys.formation.java.cbd.service.implemented;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import excilys.formation.java.cbd.dao.CompanieDao;
import excilys.formation.java.cbd.model.Companie;
import excilys.formation.java.cbd.model.CompaniePage;
import excilys.formation.java.cbd.service.CompanieService;

public class CompanieServiceImpl implements CompanieService {

	@Autowired
	private CompanieDao companieDao;
	
	@Override
	public List<Companie> getAllCompanie() {
		return companieDao.findAll();
	}

	@Override
	public Optional<Companie> getCompanie(Long id) {
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
	public void deleteCompanie(Long id) {
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
