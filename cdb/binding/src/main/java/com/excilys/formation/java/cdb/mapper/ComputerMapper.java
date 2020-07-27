package com.excilys.formation.java.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.java.cdb.model.Companie;
import com.excilys.formation.java.cdb.model.Computer;

public class ComputerMapper{
	
	private static final String COMPUTER_TABLE = "computer.";
	
	public static Computer entity(ResultSet result) throws SQLException {
		Computer computer = new Computer(result.getInt("id"), result.getString("name"));
		if(result.getDate("introduced") == null) {
	    	computer.setDateIn(null);
		}
		else {
			computer.setDateIn(result.getDate("introduced").toLocalDate());
		}
		if(result.getDate("discontinued") == null){
			computer.setDateOut(null);
		}
		else {
			computer.setDateOut(result.getDate("discontinued").toLocalDate());
		}
		if(result.getString("Cname") == null){
			computer.setCompanie(new Companie());
		}
		else {
			computer.setCompanie(new Companie(result.getInt("company_id"), result.getString("Cname")));
		}

		return computer;
	}
	
	public static Computer createEntity(ResultSet result) throws SQLException {
		Computer computer = new Computer();

		if(result.next()) {
			computer = entity(result);
		}
		return computer;
	}

	public static List<Computer> createListEntity(ResultSet result) throws SQLException {
		List<Computer> computerList = new ArrayList<Computer>();
		while(result.next()) {
			computerList.add(entity(result));
		}
		return computerList;
	}
}
