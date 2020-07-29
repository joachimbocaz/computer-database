package com.excilys.formation.java.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.formation.java.cdb.model.Companie;
import com.excilys.formation.java.cdb.model.Computer;

public class ComputerRowMapper implements RowMapper<Computer>{

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Computer computer = new Computer(rs.getInt("id"), rs.getString("name"));
		if(rs.getDate("introduced") != null) {
			computer.setDateIn(rs.getDate("introduced").toLocalDate());
		}
		
		if(rs.getDate("discontinued") != null) {
			computer.setDateOut(rs.getDate("discontinued").toLocalDate());
		}
		
		if(rs.getInt("company_id") != 0) {
			Companie company = new Companie(rs.getInt("company_id"),rs.getString("companyName"));
			computer.setCompanie(company);
		}
		return computer;
	}
}
