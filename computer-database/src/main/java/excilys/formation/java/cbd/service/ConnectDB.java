package excilys.formation.java.cbd.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConnectDB {

		private Connection connect;
		
		public static final String FICHIER_PROPERTIES="/datasource.properties"; 
				
		private static Logger logger = LoggerFactory.getLogger(ConnectDB.class);
		
//		private static HikariConfig hK =  new HikariConfig(FICHIER_PROPERTIES);
//		private static HikariDataSource ds = new HikariDataSource(hK);

		private DataSource hikariDataSource;
		
		@Autowired
		public ConnectDB(DataSource hikariDataSource){
			this.hikariDataSource = hikariDataSource;
		}

		public Connection getInstance() throws SQLException{
			if(connect == null || connect.isClosed()){
				try {
					connect = hikariDataSource.getConnection();
				} catch (SQLException e) {
					logger.info("connection impossible");
					e.printStackTrace();
				}
			}
			return connect;
		}

		public DataSource getHikariDataSource() {
			return this.hikariDataSource;
		}

		public void setHikariDataSource(DataSource hikariDataSource) {
			this.hikariDataSource = hikariDataSource;
		} 
}
