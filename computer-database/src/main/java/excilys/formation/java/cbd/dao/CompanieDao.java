package excilys.formation.java.cbd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import excilys.formation.java.cbd.mapper.CompanieMapper;
import excilys.formation.java.cbd.model.Companie;
import excilys.formation.java.cbd.model.CompaniePage;
import excilys.formation.java.cbd.model.Page;
import excilys.formation.java.cbd.service.ConnectDB;

@Repository
public class CompanieDao extends Dao<Companie>{
	
	private static Logger logger = LoggerFactory.getLogger(CompanieDao.class);
	
	@Autowired
	private ConnectDB connect;
	
	public CompanieDao() {}

	@Override
	public boolean create(Companie obj) {
		try {
			Statement st = this.connect.getInstance().createStatement();
			String sql = "INSERT INTO company values (" + obj.getId() + "," 
														 + obj.getName() + ");";
			st.executeUpdate(sql);
		    }catch (SQLException e) {
		    	logger.error("Error create companie");
				e.printStackTrace();
		    	return false;
		    }
		return true;
	}

	@Override
	public boolean delete(int id) {
		try {
			this.connect.getInstance().setAutoCommit(false);
			Statement st = this.connect.getInstance().createStatement();

			String sqlDeleteComputer = "DELETE FROM computer WHERE company_id=" + id + ";";
			st.executeUpdate(sqlDeleteComputer);

			String sql = "DELETE FROM company WHERE id = " + id;
			st.executeUpdate(sql);
		
			this.connect.getInstance().commit();
		    }catch (SQLException e) {
		    	logger.error("Error delete companie");
				e.printStackTrace();
		    	return false;
		    }
		return false;
	}

	@Override
	public Companie update(Companie obj) {
		try {
			String sql = "UPDATE company SET id = ?, name = ? WHERE id =" + obj.getId();
			PreparedStatement ps = this.connect.getInstance().prepareStatement(sql);
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
			ResultSet result = this.connect.getInstance().createStatement(
		    ResultSet.TYPE_SCROLL_INSENSITIVE,
		    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM company WHERE id = " + id);
			
			companie = CompanieMapper.createEntity(result);
		}catch (SQLException e) {
	    	logger.error("Error find companie");
			e.printStackTrace();
	    }
		return companie;
	}
	
	public String findNameCompany(int id) {
		String nameCompany = "";
		try {
			ResultSet result = this.connect.getInstance().createStatement(
		    ResultSet.TYPE_SCROLL_INSENSITIVE,
		    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT name FROM company WHERE id = " + id);
			
		    if(result.first()) {
		    	nameCompany = result.getString("name");
			}
		}catch (SQLException e) {
	    	logger.error("Error find companie");
			e.printStackTrace();
	    }
		return nameCompany;
	}

	@Override
	public List<Companie> findAll() {
		List<Companie> companyList = new ArrayList<Companie>();
		try {
			ResultSet result = this.connect.getInstance().createStatement(
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
	public List<Companie> findAllLimite(Page<Companie> page, String column, String order) {
		return null;
	}

	@Override
	public int findNbElem() {
		try {
			ResultSet result = this.connect.getInstance().createStatement(
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
			ResultSet result = this.connect.getInstance().createStatement(
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
