package excilys.formation.java.cbd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import excilys.formation.java.cbd.dao.CompanieDao;
import excilys.formation.java.cbd.dto.CompanieDto;
import excilys.formation.java.cbd.mapper.CompanieDtoMapper;
import excilys.formation.java.cbd.model.Companie;

@Service
public class AddComputerService {
	
	@Autowired
	private CompanieDao companieDao;
	
	public AddComputerService () {
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
