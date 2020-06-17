package com.excilys.formation.java.cbd.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.java.cbd.model.Computer;
import com.excilys.formation.java.cbd.service.ConnectDB;

public class ComputerDao extends Dao<Computer>{

	public ComputerDao(ConnectDB conn) {
		super(conn);
	}

	@Override
	public boolean create(Computer obj) {
		try {
			Statement st = this.connect.getConnection().createStatement();
			String sql = "INSERT INTO computer values (" + obj.getId() + ", '" +
															obj.getName() + "', ";		    
		    if(obj.getDateIn() == null) {
		    	sql += null + ", ";;
		    }
		    else {
		    	sql += obj.getDateIn() + ", ";
		    }
		    if(obj.getDateIn() == null) {
		    	sql += null + ", ";
		    }
		    else {
		    	sql += obj.getDateIn() + ", ";
		    }
		    if(obj.getManufacturer() == null) {
		    	sql += null + ");";
		    }
		    else {
		    	sql += obj.getManufacturer() + ");";
		    }
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
			Statement st = this.connect.getConnection().createStatement();
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
		    System.out.println(sql);
		    st.executeUpdate(sql);
		    }catch (SQLException e) {
		    	System.out.println("Error Update Computer");
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
		    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM computer WHERE id = " + id);
			
		    if(result.first())
		    	computer = new Computer(id, result.getString("name"));
		    	if(result.getDate("introduced") == null) {
		    		computer.setDateIn(null);
		    	}
		    	else {
		    		computer.setDateIn(result.getDate("introduced").toLocalDate());
		    	}
		    	if(result.getDate("discontinued") == null) {
		    		computer.setDateOut(null);
		    	}
		    	else {
		    		computer.setDateOut(result.getDate("discontinued").toLocalDate());
		    	}
		    	computer.setManufacturer(result.getInt("company_id"));
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
			ResultSet result = this.connect.getConnection().createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM computer");
			
			while(result.next()) {
				computer = new Computer(result.getInt("id"), result.getString("name"), result.getInt("company_id"));
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
		    	computerList.add(computer);
			}
		}catch (SQLException e) {
		    	e.printStackTrace();
		}
		return computerList;
	}
}
