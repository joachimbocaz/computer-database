package com.excilys.formation.java.cbd.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.excilys.formation.java.cbd.model.Computer;

public class ComputerDao extends Dao<Computer>{

	public ComputerDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Computer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Computer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Computer obj) {
		// TODO Auto-generated method stub
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
		    	computer = new Computer(id, result.getString("name"), result.getInt("company_id"), result.getDate("introduced"), result.getDate("discontinued"));
		    }catch (SQLException e) {
		    	e.printStackTrace();
		    }
		return computer;
	}

	@Override
	public List<Computer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
