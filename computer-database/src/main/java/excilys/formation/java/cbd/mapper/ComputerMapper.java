package excilys.formation.java.cbd.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import excilys.formation.java.cbd.model.Companie;
import excilys.formation.java.cbd.model.Computer;

public class ComputerMapper{
	
	private static final String COMPUTER_TABLE = "computer.";
	
	public static Computer entity(ResultSet result) throws SQLException {
		Computer computer = new Computer();
		Companie companie = new Companie();
		computer = new Computer(result.getInt(COMPUTER_TABLE + "id"), result.getString(COMPUTER_TABLE + "name"));
		
		if(result.getDate(COMPUTER_TABLE + "introduced") == null) {
	    	computer.setDateIn(null);
		}
		else {
			computer.setDateIn(result.getDate(COMPUTER_TABLE + "introduced").toLocalDate());
		}
		if(result.getDate(COMPUTER_TABLE + "discontinued") == null){
			computer.setDateOut(null);
		}
		else {
			computer.setDateOut(result.getDate(COMPUTER_TABLE + "discontinued").toLocalDate());
		}
		if(result.getInt(COMPUTER_TABLE + "company_id") != 0){
			computer.setManufacturer(result.getInt(COMPUTER_TABLE + "company_id"));
			computer.setCompanie(new Companie(result.getInt(COMPUTER_TABLE + "company_id"), result.getString(7)));
		}
		else {
			computer.setManufacturer(result.getInt(COMPUTER_TABLE + "company_id"));
			computer.setCompanie(new Companie());
		}

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
