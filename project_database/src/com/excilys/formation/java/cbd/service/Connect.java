package com.excilys.formation.java.cbd.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	  public static void main(String[] args) {      
		    try {
		      Class.forName("org.postgresql.Driver");
		      System.out.println("Driver O.K.");
		      
/*
		  	private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
		  	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/storetest";
		  	private static final String USERNAME = "root";
*/
		      String url = "jdbc:postgresql://localhost:5432/computer-database-db";
		      String user = "admincdb";
		      String passwd = "Iledesloups1&";

		      Connection conn = DriverManager.getConnection(url, user, passwd);
		      System.out.println("Connexion effective !");         
		         
		    } catch (Exception e) {
		      e.printStackTrace();
		    }      
		  }
}
