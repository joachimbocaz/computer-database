package com.excilys.formation.java.cbd.cli;

public class TestCli {
	public static void main(String[] args) {
		CommandLine command = new CommandLine();
		command.home();
		
		while(true) {
			if(command.getCommandIn() == "q".codePointAt(0)) {
				break;
			}
			command.executeCommande();
		}
		
		System.out.println("Fin du programme");
	}
}