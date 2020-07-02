package excilys.formation.java.cbd.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import excilys.formation.java.cbd.mapper.ComputerMapper;
import excilys.formation.java.cbd.model.Computer;
import excilys.formation.java.cbd.service.ConnectDB;

public class ComputerDao extends Dao<Computer>{
	
	private static Logger logger = LoggerFactory.getLogger(ComputerDao.class);
	
	private ConnectDB connect;
	
	public ComputerDao() throws SQLException {
		this.connect = new ConnectDB();
	}

	@Override
	public boolean create(Computer obj) {
		try /*(Connection connect2 = connect.getInstance2())*/{
			Statement st = this.connect.getConnection().createStatement();
			String sql = "INSERT INTO computer values (" + obj.getId() + ", '" +
															obj.getName() + "', ";		    
		    if(obj.getDateIn() == null) {
		    	sql += null + ", ";;
		    }
		    else {
		    	sql += "'" + obj.getDateIn() + "', ";	
		    }
		    if(obj.getDateOut() == null) {
		    	sql += null + ", ";
		    }
		    else {
		    	sql += "'" + obj.getDateOut() + "', ";
		    }
		    if(obj.getManufacturer() == null) {
		    	sql += null + ");";
		    }
		    else {
		    	sql += obj.getManufacturer() + ");";
		    }
 			st.executeUpdate(sql);
		    }catch (SQLException e) {
		    	logger.error("Error create computer");
		    	e.printStackTrace();
		    	return false;
		    }
		return true;
	}

	/*
	@Override
	public boolean delete(Computer obj) {
		try {
			Statement st = this.connect.getConnection().createStatement();
			String sql = "DELETE FROM computer WHERE id = " + obj.getId();
			st.executeUpdate(sql);
		    }catch (SQLException e) {
		    	logger.error("Error delete computer");
		    	e.printStackTrace();
		    	return false;
		    }
		return false;
	}
	*/
	
	@Override
	public boolean delete(int id) {
		try {
			Statement st = this.connect.getConnection().createStatement();
			String sql = "DELETE FROM computer WHERE id = " + id;
			st.executeUpdate(sql);
		    }catch (SQLException e) {
		    	logger.error("Error delete computer");
		    	e.printStackTrace();
		    	return false;
		    }
		return false;
	}

	/**
	 * Delete old computer and add the new computer
	 * @param obj Objet computer mise a jour dans la base de donnée
	 */
	@Override
	public Computer update(Computer obj) {
		try {
			String sql = "UPDATE computer SET id = " + obj.getId() + 
											", name = '" + obj.getName() + "', ";
			Statement st = this.connect.getConnection().createStatement();
		    if(!(obj.getDateIn() == null)) {
		    	sql += "introduced = '" + Date.valueOf(obj.getDateIn()) + "', ";
		    }
		    if(!(obj.getDateOut() == null)) {
		    	sql += "discontinued = '" + Date.valueOf(obj.getDateOut()) + "', ";
		    }
		    if(!(obj.getManufacturer() == null)) {
		    	sql += "company_id = " + obj.getManufacturer();
		    }
		    sql += " WHERE id = " + obj.getId();
		    st.executeUpdate(sql);
		    }catch (SQLException e) {
		    	logger.error("Error update computer");
		    	e.printStackTrace();
		    	return obj;
		    }
		return obj;
	}

	@Override
	public Computer find(int id) {
		Computer computer = new Computer();
		try {
			ResultSet result = this.connect.getConnection().createStatement(
		    ResultSet.TYPE_SCROLL_INSENSITIVE,
		    ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM computer WHERE id = " + id);
			
			computer = ComputerMapper.createEntity(result);
	   	}catch (SQLException e) {
	   		logger.error("Error find computer");
	    	e.printStackTrace();
	    }
		return computer;
	}

	@Override
	public List<Computer> findAll() {
		List<Computer> computerList = new ArrayList<Computer>();
		try {
			ResultSet result = this.connect.getConnection().createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM computer");
			
			computerList = ComputerMapper.createListEntity(result);
		}catch (SQLException e) {
			logger.error("Error find all computer");

	    	e.printStackTrace();
		}
		return computerList;
	}
 
	@Override
	public List<Computer> findAllLimite(int limite, int offset) {
		List<Computer> computerList = new ArrayList<Computer>();
		try {
			String sql = "SELECT * "
					   + "FROM computer "
					   + "ORDER BY id ASC "
					   + "LIMIT " + offset 
					   + ", " + limite + ";";
			ResultSet result = this.connect.getConnection().createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
			
			computerList = ComputerMapper.createListEntity(result);
		}catch (SQLException e) {
			logger.error("Error find computer from " + limite + " to " + limite + offset);
		    e.printStackTrace();
		}
		return computerList;
	}
	

	public List<Computer> findAllLimite(int limite, int offset, String column, String order) {
		List<Computer> computerList = new ArrayList<Computer>();
		try {
			String sql = "SELECT * "
					   + "FROM computer "
					   + "ORDER BY " + column +" " + order
					   + " LIMIT " + offset 
					   + ", " + limite + ";";
			
			System.out.println(sql);
			ResultSet result = this.connect.getConnection().createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
			computerList = ComputerMapper.createListEntity(result);
		}catch (SQLException e) {
			logger.error("Error find computer from " + limite + " to " + limite + offset);
		    e.printStackTrace();
		}
		return computerList;
	}

	@Override
	public int findNbElem() {
		try {
			ResultSet result = this.connect.getConnection().createStatement(
		    ResultSet.TYPE_SCROLL_INSENSITIVE,
		    ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT COUNT(*) AS total FROM computer");
			
		    if(result.first()) {
		    	return result.getInt("total");
		    }
		}catch (SQLException e) {
			logger.error("Error find number of computer");
	    	e.printStackTrace();
	    }
		return 0;
	}
	
	@Override
	public int maxId() {
		try {
			ResultSet result = this.connect.getConnection().createStatement(
		    ResultSet.TYPE_SCROLL_INSENSITIVE,
		    ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT MAX(id) AS max FROM computer");
			
		    if(result.first()) {
		    	return result.getInt("max");
		    }
		}catch (SQLException e) {
			logger.error("Error find id max of computer");
	    	e.printStackTrace();
	    }
		return 0;
	}
	
	public List<Computer> searchComputer(String search, int limite, int offset){
		List<Computer> computerList = new ArrayList<Computer>();
		try {
			String sql = "SELECT * FROM computer WHERE name LIKE '%" + search + "%' OR company_id in (select id from company where name like '%" + search + "%') order by id asc limit " + offset + ", " + limite + ";";
			ResultSet result = createRequete(sql);
			
			computerList = ComputerMapper.createListEntity(result);
		}catch (SQLException e) {
			logger.error("Error find search of computer");
	    	e.printStackTrace();
	    }
		return computerList;
	}
	
	public List<Computer> searchComputer(String search, int offset, int limite, String column, String order){
		List<Computer> computerList = new ArrayList<Computer>();
		try {
			String sql = "SELECT * FROM computer "
					   + "WHERE name "
					   		+ "LIKE '%" + search + "%' OR company_id in (select id from company where name like '%" + search + "%') "
					   		+ "ORDER BY " + column +" " + order +" limit " + limite + ", " + offset + ";";
			//revoir le limit et offset
			ResultSet result = createRequete(sql);
			
			computerList = ComputerMapper.createListEntity(result);
		}catch (SQLException e) {
			logger.error("Error find search of computer");
	    	e.printStackTrace();
	    }
		return computerList;
	}
	
	public int findNbSearchComputer(String search){
		int nbComputer = 0;
		try {
			String sql3 = "SELECT COUNT(computer.id) as toto FROM computer LEFT JOIN company as cp on computer.company_id = cp.id WHERE computer.name LIKE ? OR cp.name LIKE ?;";
			PreparedStatement result2 = this.connect.getConnection().prepareStatement(sql3);
			result2.setString(1, "%" + search + "%");
			result2.setString(2, "%" + search + "%");
			ResultSet tmp = result2.executeQuery();
		    if(tmp.next()) {
		    	nbComputer = tmp.getInt("toto");
		    	return nbComputer;
		    }
			
		}catch (SQLException e) {
			logger.error("Error find number of search computer");
	    	e.printStackTrace();
	    }
		return nbComputer;
	}
	
	public ArrayList<String> splitOrder(String order) {
		String column = order.substring(0, 2);
		String orderColumn = order.substring(2, 5);
		
		if(column.equals("cn")) {
			column = "name";
		}
		else if(column.equals("di")) {
			column = "introduced";
		}
		else if(column.equals("dd")) {
			column = "discontinued";
		}
		else if(column.equals("ci")) {
			column = "compagny.name";
		}
		if(orderColumn.contentEquals("DSC")) {
			orderColumn = "DESC";
		}

		ArrayList<String> styleOrder = new ArrayList<String>();
		styleOrder.add(column);
		styleOrder.add(orderColumn);
		
		return styleOrder;
	}
	
	private ResultSet createRequete(String requete) {
		ResultSet result;
		try {
			result = this.connect.getConnection().createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_UPDATABLE).executeQuery(requete);
			return result;
		} catch (SQLException e) {
			logger.error("Error create requete");
			e.printStackTrace();
		}
		return null;
	}
}
