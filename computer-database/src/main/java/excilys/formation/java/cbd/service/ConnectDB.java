package excilys.formation.java.cbd.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectDB {

		private Connection connect;
		
		public static final String FICHIER_PROPERTIES="/datasource.properties"; 
				
		private static Logger logger = LoggerFactory.getLogger(ConnectDB.class);
		
		private static HikariConfig hK =  new HikariConfig(FICHIER_PROPERTIES);
		private static HikariDataSource ds = new HikariDataSource(hK);

		public ConnectDB(){
			try {
				connect = ds.getConnection();
			} catch (SQLException e) {
				logger.info("connection impossible");
				e.printStackTrace();
			}
		}

		public Connection getInstance() throws SQLException{
			if(connect == null || connect.isClosed()){
				new ConnectDB();
			}
			return connect;
		}   
}
