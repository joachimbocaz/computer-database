package com.excilys.formation.java.cbd.test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.excilys.formation.java.cbd.dao.CompanieDao;
import com.excilys.formation.java.cbd.dao.ComputerDao;
import com.excilys.formation.java.cbd.model.Companie;
import com.excilys.formation.java.cbd.model.Computer;
import com.excilys.formation.java.cbd.service.ConnectDB;

public class Main {
	public static void main(String[] args) throws SQLException {
		ConnectDB con = new ConnectDB();
		CompanieDao companieDao = new CompanieDao(con);
		ComputerDao computerDao = new ComputerDao(con);
		List<Companie> companieL =  new LinkedList<Companie>();
		List<Computer> computerL =  new ArrayList<Computer>();
			
		LocalDate newDate = LocalDate.of(2015, Month.JANUARY, 1);
		LocalDate newDate2 = LocalDate.of(2018, Month.JANUARY, 24);
		Computer nComputer = new Computer(581, "nouveau");//, 13, newDate, newDate2);

		computerDao.create(nComputer);
		
		//création de la liste des compagnies et affichage
		companieL = companieDao.findAll();
		for(Companie elem:companieL) {
			System.out.println(elem);
		}
		
		//creation de la liste des ordinateurset affichage
		computerL = computerDao.findAll();
		for(Computer elem:computerL) {
			System.out.println(elem);
		}	
	}
}
