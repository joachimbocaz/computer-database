package excilys.formation.java.cbd.mapper;

import excilys.formation.java.cbd.dto.CompanieDto;
import excilys.formation.java.cbd.model.Companie;

public class CompanieDtoMapper {
	
	public static CompanieDto companieToDto(Companie companie) {
		CompanieDto companieDto = new CompanieDto(String.valueOf(companie.getId()), companie.getName());
		return companieDto;
	}
	
	public static Companie dtoToCompanie(CompanieDto companieDto) {
		return new Companie(Integer.valueOf(companieDto.getId()), companieDto.getName());
	}
}
