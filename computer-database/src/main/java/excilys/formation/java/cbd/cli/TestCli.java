package excilys.formation.java.cbd.cli;

import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import excilys.formation.java.cbd.service.ConnectDB;

public class TestCli {
	@Autowired
	private static ConnectDB connect;
	
	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		CommandLine command = new CommandLine(connect);
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
