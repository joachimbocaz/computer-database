package excilys.formation.java.cbd.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import excilys.formation.java.cbd.mapper.ComputerMapper;
import excilys.formation.java.cbd.model.Computer;
import excilys.formation.java.cbd.service.ConnectDB;

@Repository
public class ComputerDao extends Dao<Computer>{
	
	
	private static final String INSERT_SQL = "INSERT INTO computer (name,introduced,discontinued,company_id) values (?, ?, ?, ?);";
	private static final String DELETE_SQL = "DELETE FROM computer WHERE id = ?";
	private static final String SELECT_SQL = "SELECT * FROM computer LEFT join company as cp on computer.company_id = cp.id WHERE computer.id = ?";
	private static final String SELECT_ALL_SQL = "SELECT * FROM computer LEFT join company as cp on computer.company_id = cp.id";
	private static final String COUNT_SQL = "SELECT COUNT(id) AS total FROM computer";
	private static final String SELECT_SEARCH = "SELECT * "
											  + "FROM computer "
											  + "LEFT JOIN company as cp on computer.company_id = cp.id "
											  + "WHERE computer.name like ? OR cp.name like ? Order By ";
	
	private String limitSearchOrderSql(String column, String order) {
		return SELECT_SEARCH + column + " IS NULL, " + column + " "  + order + " LIMIT ?,?";
	}
	
	private static Logger logger = LoggerFactory.getLogger(ComputerDao.class);
	 
	private ConnectDB connect;
	
	public ComputerDao() throws SQLException {
		this.connect = new ConnectDB();
	}

	@Override
	public boolean create(Computer computer) {
		try(Connection connect = this.connect.getInstance();
			PreparedStatement sql = connect.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
			
			sql.setString(1, computer.getName());
				
		    if(computer.getDateIn() == null) {
		    	sql.setNull(2, Types.DATE);
		    }
		    else {
		    	sql.setDate(2, Date.valueOf(computer.getDateIn()));	
		    }
		    if(computer.getDateOut() == null) {
		    	sql.setNull(3, Types.DATE);
		    }
		    else {
		    	sql.setDate(3, Date.valueOf(computer.getDateOut()));
		    }
		    if(computer.getCompanie() == null) {
		    	sql.setNull(4, Types.BIGINT);
		    }
		    else {
		    	sql.setInt(4, computer.getCompanie().getId());
		    }
		    
			sql.executeUpdate();	
		}catch(SQLException eSQL) {
			logger.error("Erreur insertion computer dans la BDD");
			return false;
		}
		return true;
	}
	
	public boolean create2(Computer obj) {
		try /*(Connection connect2 = connect.getInstance2())*/{
			Statement st = this.connect.getInstance().createStatement();
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

	@Override
	public boolean delete(int id) {
		try(Connection connect = this.connect.getInstance();
			PreparedStatement sql = connect.prepareStatement(DELETE_SQL)){
			
			sql.setInt(1, id);
			sql.executeUpdate();
			}catch(SQLException eSQL) {
				logger.error("Error Delete Computer",eSQL);
				return false;
			}
		return true;
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
			Statement st = this.connect.getInstance().createStatement();
		    if(!(obj.getDateIn() == null)) {
		    	sql += "introduced = '" + Date.valueOf(obj.getDateIn()) + "', ";
		    }
		    if(!(obj.getDateOut() == null)) {
		    	sql += "discontinued = '" + Date.valueOf(obj.getDateOut()) + "' , ";
		    }
		    if(!(obj.getManufacturer() == null)) {
		    	sql += "company_id = " + obj.getManufacturer();
		    }
		    else {
		    	sql = sql.substring(0, sql.length() - 2);
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

		try(Connection connect = this.connect.getInstance();
			PreparedStatement sql = connect.prepareStatement(SELECT_SQL)) {
			
			sql.setInt(1, id);
			ResultSet result = sql.executeQuery();
			
			computer = ComputerMapper.createEntity(result);
				
			}catch(SQLException eSQL) {
				logger.error("Error Getting computer",eSQL);
			}
		return computer;
	}
	
	@Override
	public List<Computer> findAll() {
		List<Computer> computerList = new ArrayList<Computer>();
		try(Connection connect = this.connect.getInstance();
			PreparedStatement sql = connect.prepareStatement(SELECT_ALL_SQL)) {
			
			ResultSet result = sql.executeQuery();
			computerList = ComputerMapper.createListEntity(result);
		}catch (SQLException e) {
			logger.error("Error find all computer");
	    	e.printStackTrace();
	    	return null;
		}
		return computerList;
	}
 
	@Override
	public List<Computer> findAllLimite(int limite, int offset) {
		List<Computer> computerList = new ArrayList<Computer>();
		String sqlTmp = "SELECT * "
				   + "FROM computer "
				   + "LEFT join company as cp on computer.company_id = cp.id"
				   + "ORDER BY id ASC "
				   + "LIMIT " + offset 
				   + ", " + limite + ";";
		
		try(Connection connect = this.connect.getInstance();
			PreparedStatement sql = connect.prepareStatement(sqlTmp)) {
			
			ResultSet result = sql.executeQuery();
			computerList = ComputerMapper.createListEntity(result);
		}catch (SQLException e) {
			logger.error("Error find computer from " + limite + " to " + limite + offset);
		    e.printStackTrace();
		}
		return computerList;
	}
	

	public List<Computer> findAllLimite(int limite, int offset, String column, String order) {
		List<Computer> computerList = new ArrayList<Computer>();
		String sqlTmp = "SELECT * "
				   + "FROM computer LEFT join company as cp on computer.company_id = cp.id "
				   + "ORDER BY " + column +" " + order
				   + " LIMIT " + offset 
				   + ", " + limite;
		
		try(Connection connect = this.connect.getInstance();
			PreparedStatement sql = connect.prepareStatement(sqlTmp)) {
			
			ResultSet result = sql.executeQuery();
			computerList = ComputerMapper.createListEntity(result);
			
		}catch (SQLException e) {
			logger.error("Error find computer from " + limite + " to " + limite + offset);
		    e.printStackTrace();
		}
		return computerList;
	}

	@Override
	public int findNbElem() {
		int nbElem = 0;
		try(Connection connect = this.connect.getInstance();
			PreparedStatement sql = connect.prepareStatement(COUNT_SQL)) {
			
			ResultSet result = sql.executeQuery();
			
		    if(result.next()) {
		    	nbElem = result.getInt("total");
		    }
		    return nbElem;
		    
		}catch (SQLException e) {
			logger.error("Error find number of computer");
	    	e.printStackTrace();
	    	return nbElem;
	    }
	}
	
	@Override
	public int maxId() {
		System.out.println("sqipfsdiofhj");
		try {
			ResultSet result = this.connect.getInstance().createStatement(
		    ResultSet.TYPE_SCROLL_INSENSITIVE,
		    ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT MAX(id) AS max FROM computer");
			
			System.out.println("titi");
		    if(result.next()) {
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
			String sql = "SELECT * FROM computer LEFT join company as cp on computer.company_id = cp.id WHERE name LIKE '%" + search + "%' OR company_id in (select id from company where name like '%" + search + "%') order by id asc limit " + offset + ", " + limite + ";";
			ResultSet result = createRequete(sql);
			computerList = ComputerMapper.createListEntity(result);
		}catch (SQLException e) {
			logger.error("Error find search of computer");
	    	e.printStackTrace();
	    }
		return computerList;
	}
	
//	public List<Computer> searchComputer(String search, int offset, int limite, String column, String order){
//		List<Computer> computerList = new ArrayList<Computer>();
//		try {
//			String sql = "SELECT * FROM computer "
//					   + "WHERE name LEFT JOIN company as cp on computer.company_id = cp.id "
//					   + "LIKE '%" + search + "%' OR company_id in (select id from company where name like '%" + search + "%') "
//					   + "ORDER BY " + column +" " + order +" limit " + limite + ", " + offset + ";";
//			System.out.println(sql);
//			//revoir le limit et offset
//			ResultSet result = createRequete(sql);
//			computerList = ComputerMapper.createListEntity(result);
//		}catch (SQLException e) {
//			logger.error("Error find search of computer");
//	    	e.printStackTrace();
//	    }
//		return computerList;
//	}
	
	public List<Computer> searchComputer(String search, int offset, int limite, String column, String order){
		List<Computer> computerList = new ArrayList<>();
		try(Connection connect = this.connect.getInstance();
			PreparedStatement prepare = connect.prepareStatement(limitSearchOrderSql(column, order))) {
			
			prepare.setString(1, "%"+search+"%");
			prepare.setString(2, "%"+search+"%");
			prepare.setInt(4, offset);
			prepare.setInt(3, limite);

			ResultSet result = prepare.executeQuery();
			computerList = ComputerMapper.createListEntity(result);
			
		}catch(SQLException eSQL) {
			logger.error("Error Getting Computers between Search",eSQL);
		}
		return computerList;
}
	
	public int findNbSearchComputer(String search){
		int nbComputer = 0;
		try {
			String sql3 = "SELECT COUNT(computer.id) as toto FROM computer LEFT JOIN company as cp on computer.company_id = cp.id WHERE computer.name LIKE ? OR cp.name LIKE ?;";
			PreparedStatement result2 = this.connect.getInstance().prepareStatement(sql3);
			result2.setString(1, "%" + search + "%");
			result2.setString(2, "%" + search + "%");
			System.out.println(result2);
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
			column = "computer.name";
		}
		else if(column.equals("di")) {
			column = "computer.introduced";
		}
		else if(column.equals("dd")) {
			column = "computer.discontinued";
		}
		else if(column.equals("ci")) {
			column = "cp.name";
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
			result = this.connect.getInstance().createStatement(
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
