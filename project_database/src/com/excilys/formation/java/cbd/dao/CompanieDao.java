package com.excilys.formation.java.cbd.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.excilys.formation.java.cbd.model.Companie;

public class CompanieDao extends Dao<Companie>{

	public CompanieDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Companie obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Companie obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Companie obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Companie find(int id) {
		Companie companie = new Companie();
		try {
			ResultSet result = this.connect.createStatement(
		    ResultSet.TYPE_SCROLL_INSENSITIVE,
		    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM company WHERE id = " + id);
		    if(result.first())
		    	companie = new Companie(id, result.getString("name"));
		    }catch (SQLException e) {
		    	e.printStackTrace();
		    }
		return companie;
	}

	@Override
	public List<Companie> findAll() {
		
		return null;
	}

}
