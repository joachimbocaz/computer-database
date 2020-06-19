package com.excilys.formation.java.cbd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.java.cbd.mapper.CompanieMapper;
import com.excilys.formation.java.cbd.model.Companie;
import com.excilys.formation.java.cbd.service.ConnectDB;

public class CompanieDao extends Dao<Companie>{

	public CompanieDao(ConnectDB conn) {
		super(conn);
	}

	@Override
	public boolean create(Companie obj) {
		try {
			Statement st = this.connect.getConnection().createStatement();
			String sql = "INSERT INTO company values (" + obj.getId() + "," 
														 + obj.getName() + ");";
			st.executeUpdate(sql);
		    }catch (SQLException e) {
		    	e.printStackTrace();
		    	return false;
		    }
		return true;
	}

	@Override
	public boolean delete(Companie obj) {
		try {
			Statement st = this.connect.getConnection().createStatement();
			String sql = "DELETE FROM company WHERE id = " + obj.getId();
			st.executeUpdate(sql);
		    }catch (SQLException e) {
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
	    	e.printStackTrace();
	    }
		return 0;
	}
}
