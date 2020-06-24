package excilys.formation.java.cbd.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import excilys.formation.java.cbd.dao.ComputerDao;

public class ConnectDB {
		private static ConnectDB instance;
		private Connection connect;
		
		private String url = "jdbc:mysql://localhost/computer-database-db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	    private String username = "admincdb";
	    private String password = "qwerty1234";
	    
		private static Logger logger = LoggerFactory.getLogger(ConnectDB.class);


		public ConnectDB() throws SQLException {
			try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            this.connect = DriverManager.getConnection(url, username, password);
	            System.out.println("Connect");
	           
	        } catch (ClassNotFoundException ex) {
	        	logger.error("Driver not found");
	        	ex.printStackTrace();
	        } catch(SQLException s) {
	        	logger.error("Error connection to DB");
	        	s.printStackTrace();
			}
		}
		
		public Connection getConnection() {
			
			return connect;
		}
		
		public final static ConnectDB getInstance() throws SQLException {
			if(instance == null) {
				instance = new ConnectDB();
			}else if( instance.getConnection().isClosed()) {
				instance = new ConnectDB();
			}
			return instance;
		}
		
		public static void doRequest(ConnectDB con, String requete) {
			ResultSet resultats = null;
			try {
				Statement stmt = con.getConnection().createStatement();
				resultats = stmt.executeQuery(requete);
				ResultSetMetaData rsmd;
				rsmd = resultats.getMetaData();
				int nbCols = rsmd.getColumnCount();
				while (resultats.next()) {
					for (int i = 1; i <= nbCols; i++)
						System.out.print(resultats.getString(i) + " ");
						System.out.println();
					}
				resultats.close();
			} catch (SQLException e) {
				logger.error("Error request");
				e.printStackTrace();
			}
		}
}
