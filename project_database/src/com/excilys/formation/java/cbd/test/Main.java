package com.excilys.formation.java.cbd.test;

import java.sql.SQLException;

import com.excilys.formation.java.cbd.service.ConnectDB;

public class Main {
	public static void main(String[] args) {
		try {
			ConnectDB con = new ConnectDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
