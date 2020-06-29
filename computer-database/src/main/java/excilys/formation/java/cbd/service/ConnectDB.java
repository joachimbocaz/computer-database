package excilys.formation.java.cbd.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectDB {

	private static ConnectDB instance;
		private Connection connect;
		
		public static final String FICHIER_PROPERTIES="/home/joachim/computer-database/computer-database/src/main/resources/jdbc.properties"; 
		
		private static String url, user, passwd, driver;
		
				
//		private static Logger logger = LoggerFactory.getLogger(ConnectDB.class);


		public ConnectDB() throws SQLException {
//			Properties properties = new Properties();
//			String driver;
//			String url;
//			String nomUtilisateur; 
//			String motDePasse;
//			ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
//			InputStream fichierProperties=classLoader.getResourceAsStream(FICHIER_PROPERTIES);
			
			try {
				getProperties();
	            System.out.println(url);
	            System.out.println(user);
	            System.out.println(passwd);
	            System.out.println(driver);
				Class.forName(driver);
	            this.connect = DriverManager.getConnection(url, user, passwd);
	            System.out.println("Connect");
	           
	        } catch (ClassNotFoundException ex) {
	        	System.out.println("driver not found");
//	        	logger.error("Driver not found");
	        	ex.printStackTrace();
	        } catch(SQLException s) {
	        	System.out.println("error connection a la bdd");
//	        	logger.error("Error connection to DB");
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
//				logger.error("Error request");
				System.out.println("error request");
				e.printStackTrace();
			}
		}
		
		private static void getProperties() {
			Properties prop = new Properties();
			try(InputStream input = new FileInputStream(FICHIER_PROPERTIES)){
				prop.load(input);
				url = prop.getProperty("db.url");
				user = prop.getProperty("db.username");
				passwd = prop.getProperty("db.password");
				driver = prop.getProperty("db.driver");
			} catch (FileNotFoundException e) {
				System.out.println("impossible de trouver le fichier");
//				logger.error("File not Found");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("impossible de lire le fichier");
//				logger.error("IO Exceptions");
				e.printStackTrace();
			}
		}
}
