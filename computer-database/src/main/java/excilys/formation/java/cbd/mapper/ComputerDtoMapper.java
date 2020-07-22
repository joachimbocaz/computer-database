package excilys.formation.java.cbd.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import excilys.formation.java.cbd.dto.ComputerDto;
import excilys.formation.java.cbd.model.Computer;

public class ComputerDtoMapper{
	
	public static ComputerDto computerToDto (Computer computer) {
		ComputerDto computerDto = new ComputerDto();
		computerDto.setId(String.valueOf(computer.getId()));
		computerDto.setName(computer.getName());
		computerDto.setIntroduced(String.valueOf(computer.getDateIn()));
		computerDto.setDiscontinued(String.valueOf(computer.getDateOut()));
		if(computer.getCompanie() != null) {
			computerDto.setNameCompany(computer.getCompanie().getName());
		}
		else {
			computerDto.setNameCompany("");
		}
		return computerDto;
	}
	
	public static Computer dtoToComputer(ComputerDto computerDto) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.FRENCH);
		Computer computer = new Computer(Integer.valueOf(computerDto.getId()),computerDto.getName());
		//computer.setNameCompany(computerDto.getNameCompany().equals("") ? "":computerDto.getNameCompany());
		computer.setDateIn(computerDto.getIntroduced().equals("") ? null:LocalDate.parse(computerDto.getIntroduced(), formatter));
		computer.setDateOut(computerDto.getDiscontinued().equals("") ? null:LocalDate.parse(computerDto.getDiscontinued(), formatter));
		return computer;
	}
}