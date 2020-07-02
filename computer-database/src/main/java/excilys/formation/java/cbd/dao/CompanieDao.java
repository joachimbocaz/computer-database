package excilys.formation.java.cbd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import excilys.formation.java.cbd.mapper.CompanieMapper;
import excilys.formation.java.cbd.model.Companie;
import excilys.formation.java.cbd.service.ConnectDB;

public class CompanieDao extends Dao<Companie>{
	
	private static Logger logger = LoggerFactory.getLogger(CompanieDao.class);
	
	private ConnectDB connect;
	
	public CompanieDao() throws SQLException {
		this.connect = new ConnectDB();
	}

	@Override
	public boolean create(Companie obj) {
		try {
			Statement st = this.connect.getConnection().createStatement();
			String sql = "INSERT INTO company values (" + obj.getId() + "," 
														 + obj.getName() + ");";
			st.executeUpdate(sql);
		    }catch (SQLException e) {
//		    	logger.error("Error create companie");
				e.printStackTrace();
		    	return false;
		    }
		return true;
	}

	@Override
	public boolean delete(int id) {
		try {
			Statement st = this.connect.getConnection().createStatement();
			String sql = "DELETE FROM company WHERE id = " + id;
			st.executeUpdate(sql);
		    }catch (SQLException e) {
//		    	logger.error("Error delete companie");
				e.printStackTrace();
		    	return false;
		    }
		return false;
	}

	@Override
	public Companie update(Companie obj) {
		try {
			String sql = "UPDATE company SET id = ?, name = ? WHERE id =" + obj.getId();
			PreparedStatement ps = this.connect.getConnection().prepareStatement(sql);
			ps.setInt(1, obj.getId());
		    ps.setString(2, obj.getName());
		    ps.executeUpdate();
		    }catch (SQLException e) {
		    	logger.error("Error update companie");
				e.printStackTrace();
		    	return obj;
		    }
		return obj;
	}

	@Override
	public Companie find(int id) {
		Companie companie = new Companie();
		try {
			ResultSet result = this.connect.getConnection().createStatement(
		    ResultSet.TYPE_SCROLL_INSENSITIVE,
		    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM company WHERE id = " + id);
			
			companie = CompanieMapper.createEntity(result);
		}catch (SQLException e) {
	    	logger.error("Error find companie");
			e.printStackTrace();
	    }
		return companie;
	}

	@Override
	public List<Companie> findAll() {
		List<Companie> companyList = new ArrayList<Companie>();
		try {
			ResultSet result = this.connect.getConnection().createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM company");
			
			companyList = CompanieMapper.createListEntity(result);
		}catch (SQLException e) {
	    	logger.error("Error find all companie");
			e.printStackTrace();
		}
		return companyList;
	}

	@Override
	public List<Companie> findAllLimite(int limite, int offset) {
		List<Companie> companyList = new ArrayList<Companie>();
		Companie company = new Companie();
		try {
			ResultSet result = this.connect.getConnection().createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * "
												   + "FROM company "
												   + "ORDER BY id ASC "
												   + "LIMIT " + offset 
												   + ", " + limite + ";");;
			while(result.next()) {
				company = new Companie(result.getInt("id"), result.getString("name"));
				companyList.add(company);
			}
		}catch (SQLException e) {
	    	logger.error("Error find all "+ limite +" companie");
	    	e.printStackTrace();
		}
		return companyList;
	}

	@Override
	public int findNbElem() {
		try {
			ResultSet result = this.connect.getConnection().createStatement(
		    ResultSet.TYPE_SCROLL_INSENSITIVE,
		    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT COUNT(*) AS total FROM computer");
			
		    if(result.first()) {
		    	return result.getInt("total");
		    }
		}catch (SQLException e) {
	    	logger.error("Error find number of companie");
	    	e.printStackTrace();
	    }
		return 0;
	}

	@Override
	public int maxId() {
		try {
			ResultSet result = this.connect.getConnection().createStatement(
		    ResultSet.TYPE_SCROLL_INSENSITIVE,
		    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT MAX(id) AS max FROM computer");
			
		    if(result.first()) {
		    	return result.getInt("max");
		    }
		}catch (SQLException e) {
	    	logger.error("Error find number of companie");
	    	e.printStackTrace();
	    }
		return 0;
	}
}
