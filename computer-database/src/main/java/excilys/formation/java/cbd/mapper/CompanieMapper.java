package excilys.formation.java.cbd.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import excilys.formation.java.cbd.model.Companie;


public class CompanieMapper{

	public static Companie entity(ResultSet result) throws SQLException{
		return new Companie(result.getInt("id"), result.getString("name"));
	}
	
	public static Companie createEntity(ResultSet result) throws SQLException {
		Companie companie = new Companie();
	    if(result.first()) {
	    	companie = entity(result);
		}
		return companie;
	}

	public static List<Companie> createListEntity(ResultSet result) throws SQLException{
		List<Companie> companyList = new ArrayList<Companie>();
		while(result.next()) {
			companyList.add(entity(result));
		}
		return companyList;
	}
	

}
