package excilys.formation.java.cbd.cli;

import java.sql.SQLException;
import java.util.Scanner;

import excilys.formation.java.cbd.service.ConnectDB;

public class TestCli {
	public static void main(String[] args) throws SQLException {
		ConnectDB con = new ConnectDB();
		Scanner sc = new Scanner(System.in);
		CommandLine command = new CommandLine(con);
		command.home();

		while(true) {
			System.out.println("Entrez une option : ");
			command.setCommandIn(sc.nextLine());
			command.setOptionList();
			if(command.getCommandIn().equals("q")) {
				break;
			}
			command.executeCommande();
		}
		
		System.out.println("Fin du programme");
		sc.close();
	}
}
