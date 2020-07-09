package excilys.formation.java.cbd.service;

import java.util.ArrayList;
import java.util.List;

import excilys.formation.java.cbd.dao.ComputerDao;
import excilys.formation.java.cbd.dto.ComputerDto;
import excilys.formation.java.cbd.mapper.ComputerDtoMapper;
import excilys.formation.java.cbd.model.Computer;

public class DashBoardService {
	private ComputerDao computerDao;
	
	public DashBoardService () {
		this.computerDao = new ComputerDao();
	}
	
	public List<ComputerDto> listComputerToDto(){
		ArrayList<ComputerDto> computerDtoListe = new ArrayList<ComputerDto>();
		List<Computer> computerList = this.computerDao.findAll();
		
		for(Computer computer:computerList) {
			computerDtoListe.add(ComputerDtoMapper.computerToDto(computer));
		}
		return computerDtoListe;
	}
}
