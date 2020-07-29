package com.excilys.formation.java.cdb.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectDBH2 {

	private Connection connect;
	
	public static final String FICHIER_PROPERTIES="/home/joachim/computer-database/computer-database/src/test/resources/datasource.properties"; 
	
	
			
//	private static Logger logger = LoggerFactory.getLogger(ConnectDBH2.class);
	
	private static HikariDataSource ds = new HikariDataSource(new HikariConfig(FICHIER_PROPERTIES));

	public ConnectDBH2(){
		try {
			connect = ds.getConnection();
		} catch (SQLException e) {
//			logger.info("connection impossible");
			e.printStackTrace();
		}
	}

	public Connection getInstance() throws SQLException{
		if(connect == null || connect.isClosed()){
			new ConnectDBH2();
		}
		return connect;
	}   
}
