package com.excilys.formation.java.cbd.cli;

import java.util.Scanner;

public class CommandLine {
	private Scanner sc = new Scanner(System.in);
	private int commandIn;
	private boolean update = false;
	
	public CommandLine() {
		
	}
	public int getCommandIn() {
		return this.commandIn;
	}
	
	public void executeCommande() {
		if(update) {
			switch(this.commandIn) {
				case 104:
					this.help();
					break;
			}
		}
	}
	
	public void home() {
		System.out.println("Bienvenue dans l'interface de commandepour la gestion de la bdd d'ordinateur\n"
				+ "Taper 'h' pour avoir l'aide sur les différentes commande disponible");
		commandIn = sc.nextInt();
	}
	
	public void help() {
		System.out.println("Liste des différent commande disponible :\n"
				+ "'h' : Aide sur les instructions possible\n"
				+ "'q' : Quitter l'interface de commande\n"
				+ "'C' : Liste des différents ordi de la BDD\n"
				+ "'Q' : Liste des différentes compagnie de la BDD\n"
				+ "'c [nom_ordi]' : Détaille sur un ordinateur en particulier\n"
				+ "'cr [id, nom, (compagnie), (dateIn), (dateOut)]' : Création d'un nouveau pc, parametre entre '()' optionnel\n"
				+ "'cu [id, nom, (compagnie), (dateIn), (dateOut)]' : Maj d'un pc, parametre entre '()' optionnel\n"
				+ "'cd [id]' : Supression d'un ordinateur grace a son id");
	}
	
	public void listComputer() {
		
	}
	
	public void listCompanies() {
		
	}
	
	public void detailComputer() {
		
	}
	
	public void createComputer() {
		
	}
	
	public void updateComputer() {
		
	}
	
	public void deleteComputer() {
		
	}
}
