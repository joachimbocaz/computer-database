package excilys.formation.java.cbd.mapper;

import java.util.ArrayList;
import java.util.List;

import excilys.formation.java.cbd.dao.ComputerDao;
import excilys.formation.java.cbd.dto.ComputerDto;
import excilys.formation.java.cbd.model.Computer;

public class ComputerDtoMapper{
	private ComputerDao computerDao;
	
	public static ComputerDto computerToDto (Computer computer) {
		ComputerDto computerDto = new ComputerDto();
		computerDto.setId(String.valueOf(computer.getId()));
		computerDto.setName(computer.getName());
		computerDto.setIntroduced(String.valueOf(computer.getDateIn()));
		computerDto.setDiscontinued(String.valueOf(computer.getDateOut()));
		computerDto.setIdCompanie(String.valueOf(computer.getManufacturer()));
		return computerDto;
	}
	
	public List<ComputerDto> listComputerToDto(){
		ArrayList<ComputerDto> computerDtoListe = new ArrayList<ComputerDto>();
		List<Computer> computerList = computerDao.findAll();
		
		for(Computer computer:computerList) {
			computerDtoListe.add(ComputerDtoMapper.computerToDto(computer));
		}
		return computerDtoListe;
	}
}