package excilys.formation.java.cbd.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import excilys.formation.java.cbd.dao.CompanieDao;
import excilys.formation.java.cbd.dto.CompanieDto;
import excilys.formation.java.cbd.mapper.CompanieDtoMapper;
import excilys.formation.java.cbd.model.Companie;

public class AddComputerService {
	private CompanieDao companieDao;
	
	public AddComputerService () {
		try {
			this.companieDao = new CompanieDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<CompanieDto> listCompanieToDto(){
		ArrayList<CompanieDto> companieDtoListe = new ArrayList<CompanieDto>();
		List<Companie> companieList = this.companieDao.findAll();
		
		for(Companie companie:companieList) {
			companieDtoListe.add(CompanieDtoMapper.companieToDto(companie));
		}
		return companieDtoListe;
	}
}
