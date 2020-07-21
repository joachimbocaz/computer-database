package excilys.formation.java.cbd.cli;

import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import excilys.formation.java.cbd.configuration.SpringConfigurationContext;

@Component
public class TestCli {
	
	public static void main(String[] args) throws SQLException {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurationContext.class);
		CommandLine command = context.getBean(CommandLine.class);
		
		Scanner sc = new Scanner(System.in);
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
