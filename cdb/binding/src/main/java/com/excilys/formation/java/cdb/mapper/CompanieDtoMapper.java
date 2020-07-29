package com.excilys.formation.java.cdb.mapper;

import com.excilys.formation.java.cdb.dto.CompanieDto;
import com.excilys.formation.java.cdb.model.Companie;

public class CompanieDtoMapper {
	
	public static CompanieDto companieToDto(Companie companie) {
		CompanieDto companieDto = new CompanieDto(String.valueOf(companie.getId()), companie.getName());
		return companieDto;
	}
	
	public static Companie dtoToCompanie(CompanieDto companieDto) {
		return new Companie(Integer.valueOf(companieDto.getId()), companieDto.getName());
	}
}
