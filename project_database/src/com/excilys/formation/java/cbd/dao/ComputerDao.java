package com.excilys.formation.java.cbd.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.java.cbd.model.Computer;

public class ComputerDao extends Dao<Computer>{

	public ComputerDao(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Computer obj) {
		try {
			Statement st = this.connect.createStatement();
			String sql = "INSERT INTO computer values (" + obj.getId() + "," 
														 + obj.getName() + ","
														 + obj.getDateIn() + ","
														 + obj.getDateOut() + ","
														 + obj.getManufacturer() + ");";
			st.executeUpdate(sql);
		    }catch (SQLException e) {
		    	e.printStackTrace();
		    	return false;
		    }
		return true;
	}

	@Override
	public boolean delete(Computer obj) {
		try {
			Statement st = this.connect.createStatement();
			String sql = "DELETE FROM computer WHERE id = " + obj.getId();
			st.executeUpdate(sql);
		    }catch (SQLException e) {
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
	public boolean update(Computer obj) {
		try {
			String sql = "UPDATE computer SET id = ?, name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id =" + obj.getId();
			PreparedStatement ps = this.connect.prepareStatement(sql);
			ps.setInt(1, obj.getId());
		    ps.setString(2, obj.getName());
		    ps.setDate(3, Date.valueOf(obj.getDateIn()));
		    ps.setDate(4, Date.valueOf(obj.getDateOut()));
		    ps.setInt(5, obj.getManufacturer());
		    ps.executeUpdate();
		    }catch (SQLException e) {
		    	e.printStackTrace();
		    	return false;
		    }
		return false;
	}

	@Override
	public Computer find(int id) {
		Computer computer = new Computer();      
		try {
			ResultSet result = this.connect.createStatement(
		    ResultSet.TYPE_SCROLL_INSENSITIVE,
		    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM computer WHERE id = " + id);
		    if(result.first())
		    	computer = new Computer(id, result.getString("name"), result.getInt("company_id"), result.getDate("introduced").toLocalDate(), result.getDate("discontinued").toLocalDate());
		    }catch (SQLException e) {
		    	e.printStackTrace();
		    }
		return computer;
	}

	@Override
	public List<Computer> findAll() {
		List<Computer> computerList = new ArrayList<Computer>();
		Computer computer = new Computer();
		try {
			ResultSet result = this.connect.createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM computer");
			//ResultSetMetaData resultMeta = result.getMetaData();
			
			while(result.next()) {
			    	computer = new Computer(result.getInt("id"), result.getString("name"), result.getInt("company_id"), result.getDate("introduced").toLocalDate(), result.getDate("discontinued").toLocalDate());
			    	computerList.add(computer);
			}
		}catch (SQLException e) {
		    	e.printStackTrace();
		}
		return computerList;
	}
}
