package com.excilys.formation.java.cdb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.java.cdb.dao.ComputerDao;
import com.excilys.formation.java.cdb.dto.ComputerDto;
import com.excilys.formation.java.cdb.mapper.ComputerDtoMapper;
import com.excilys.formation.java.cdb.model.Computer;

public class DashBoardService {
	@Autowired
	private ComputerDao computerDao;
	
	public DashBoardService () {
//		this.computerDao = new ComputerDao();
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
