package excilys.formation.java.cbd.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import excilys.formation.java.cbd.model.Computer;

public class ComputerMapper{

	private static Computer entity(ResultSet result) throws SQLException {
		Computer computer = new Computer();
		computer = new Computer(result.getInt("id"), result.getString("name"));
		
		if(result.getString("introduced") == null) {
	    	computer.setDateIn(null);
		}
		else {
			computer.setDateIn(result.getDate("introduced").toLocalDate());
		}
		if(result.getDate("discontinued") == null){
			computer.setDateOut(null);
		}
		else {
			computer.setDateIn(result.getDate("discontinued").toLocalDate());
		}
		computer.setManufacturer(result.getInt("company_id"));
		
		return computer;
	}
	
	public static Computer createEntity(ResultSet result) throws SQLException {
		Computer computer = new Computer();
		
		if(result.first()) {
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