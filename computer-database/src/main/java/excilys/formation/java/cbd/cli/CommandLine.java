package excilys.formation.java.cbd.cli;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import excilys.formation.java.cbd.dao.CompanieDao;
import excilys.formation.java.cbd.dao.ComputerDao;
import excilys.formation.java.cbd.model.Companie;
import excilys.formation.java.cbd.model.CompaniePage;
import excilys.formation.java.cbd.model.Computer;
import excilys.formation.java.cbd.model.ComputerPage;
import excilys.formation.java.cbd.model.Page;
import excilys.formation.java.cbd.service.ConnectDB;

public class CommandLine {
	private String commandIn;
	private String[] optionList;
	private enum Command{
		Aide,
		Quitter,
		Liste_Ordi,
		Liste_Comp,
		Detail,
		Create,
		Update,
		Delete,
		Default;	
	}
	private Command command;
	ConnectDB con;
	CompanieDao companieDao;
	ComputerDao computerDao;
	Page<Computer> computerPage;
	Page<Companie> compagniePage;
	List<Companie> companieL =  new LinkedList<Companie>();
	List<Computer> computerL =  new ArrayList<Computer>();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	
	public CommandLine(ConnectDB con) {
		this.con = con;
		this.companieDao = new CompanieDao(this.con);
		this.computerDao = new ComputerDao(this.con);
		this.computerPage = new ComputerPage();
		this.compagniePage = new CompaniePage();
	}
	
	public void setCompanieL() {
		this.companieL = this.companieDao.findAll();
	}
	
	public void setComputerL() {
		this.computerL = this.computerDao.findAll();
	}
	
	public String getCommandIn() {
		return this.commandIn;
	}
	
	public void setCommandIn(String pCommandIn) {
		this.commandIn = pCommandIn;
	}
	
	public String[] getOptionList() {
		return this.optionList;
	}
	
	public void setOptionList() {
		this.optionList = this.commandIn.split(" ");
	}
	
	public void setCommand() {
		if(this.optionList[0].equals("h")) {
			this.command = Command.Aide;
		}
		else if(this.optionList[0].equals("C")) {
			this.command = Command.Liste_Ordi;
		}
		else if(this.optionList[0].equals("Q")) {
			this.command = Command.Liste_Comp;
		}
		else if(this.optionList[0].equals("c")) {
			this.command = Command.Detail;
		}
		else if(this.optionList[0].equals("cr")) {
			this.command = Command.Create;
		}
		else if(this.optionList[0].equals("cu")) {
			this.command = Command.Update;
		}
		else if(this.optionList[0].equals("cd")) {
			this.command = Command.Delete;
		}
		else {
			this.command = Command.Default;
		}
	}
	
	public void executeCommande() {
		this.setCommand();
		switch(this.command) {
			case Aide:
				this.help();
				break;
			case Liste_Comp:
				//this.listCompanies();
				this.listPageCompagnie();
				break;
			case Liste_Ordi:
				//this.listComputer();
				this.listPageComputer();
				break;
			case Detail:
				this.detailComputer();
				break;
			case Create:
				this.createComputer();
				break;
			case Update:
				this.updateComputer();
				break;
			case Delete:
				this.deleteComputer();
				break;
			default:
				break;
		}
	}
	
	public void home() {
		System.out.println("Bienvenue dans l'interface de commande pour la gestion de la bdd d'ordinateur\n"
				+ "Taper 'h' pour avoir l'aide sur les différentes commande disponible");
	}
	
	private void help() {
		System.out.println("Liste des différent commande disponible :\n"
				+ "'h' : Aide sur les instructions possible\n"
				+ "'q' : Quitter l'interface de commande\n"
				+ "'C' 'numPage' : Liste des différents ordi de la BDD, indiquer le numéro de la page\n"
				+ "'Q' 'numPage': Liste des différentes compagnie de la BDD, indiquer le numéro de la page\n"
				+ "'c' ['id_ordi'] : Détaille sur un ordinateur en particulier\n"
				+ "'cr' ['id' 'nom' -c 'compagnie' -i 'dateIn' -o 'dateOut']' : Création d'un nouveau pc, les parametres avec -i -o -c sont optionnels\n"
				+ "'cu' ['id' 'nom' -c 'compagnie' -i 'dateIn' -o 'dateOut']' : Maj d'un pc, les parametres avec -i -o -c sont optionnels\n"
				+ "'cd' ['id'] : Supression d'un ordinateur grace a son id\n"
				+ "WARNING : les dates sont au formats 'yyy-MM-dd'");
	}
	private Computer createComputerCli() {
		Computer compute = new Computer(Integer.parseInt(optionList[1]), optionList[2]);
		List<Boolean> optionChoose = new ArrayList<Boolean>();
		optionChoose.add(false);
		optionChoose.add(false);
		optionChoose.add(false);
		
		for(int i = 0; i < this.optionList.length; i++) {
			if(this.optionList[i].equals("-c")) {
				optionChoose.set(0, true);
				if(this.optionList[i].equals("null")) {
					System.out.println("set id entreprise a 0");
					compute.setManufacturer(0);
					i++;
				}
				else {
					System.out.println("set id entreprise a " + this.optionList[i + 1]);
					compute.setManufacturer(Integer.parseInt(this.optionList[i + 1]));
					i++;
				}
			}
			else if(this.optionList[i].equals("-i")) {
				optionChoose.set(1, true);
				if(this.optionList[i].equals("null")) {
					compute.setDateIn(null);
					i++;
				}
				else {
					compute.setDateIn(LocalDate.parse(this.optionList[i + 1], formatter));
					i++;
				}
				
			}
			else if(this.optionList[i].equals("-o")) {
				optionChoose.set(2, true);
				if(this.optionList[i].equals("null")) {
					compute.setDateOut(null);
					i++;
				}
				else {
					compute.setDateOut(LocalDate.parse(this.optionList[i + 1], formatter));
					i++;
				}
			}
		}
		
		if(!optionChoose.get(0)) {
			compute.setManufacturer(0);
		}
		if(!optionChoose.get(1)) {
			compute.setDateIn(null);
		}
		if(!optionChoose.get(2)) {
			compute.setDateOut(null);
		}
		
		return compute;
	}

	public int getNumPage() {
		return Integer.valueOf(this.optionList[1]);
	}
	
	private void printListComputer(List<Computer> list) {
		for(Computer elem:list) {
			System.out.println(elem);
		}
	}
	
	private void printListCompanie(List<Companie> list) {
		for(Companie elem:list) {
			System.out.println(elem);
		}
	}
	
	public void listComputer() {
		this.computerL = this.computerDao.findAll();
		printListComputer(this.computerL);
	}
	
	public void listPageComputer() {
		this.computerPage = new ComputerPage(getNumPage());
		this.computerPage.setOffset();
		computerPage.setEntity(this.computerDao);
		this.computerL = computerPage.getEntity();
		printListComputer(this.computerL);
	}
	
	public void listPageCompagnie() {
		this.compagniePage = new CompaniePage(getNumPage());
		this.compagniePage.setOffset();
		this.compagniePage.setEntity(this.companieDao);
		this.companieL= this.compagniePage.getEntity();
		printListCompanie(this.companieL);
	}
	
	public void listCompanies() {
		this.companieL = this.companieDao.findAll();
		printListCompanie(this.companieL);
	}
	
	public void detailComputer() {
		int idComputer = Integer.parseInt(this.optionList[1]);
		System.out.println(this.computerDao.find(idComputer));
	}
	
	public void createComputer() {
		System.out.println("creation de l'ordinateur");
		this.computerDao.create(createComputerCli());
	}
	
	public void updateComputer() {
		Computer computerUpdate = createComputerCli();
		this.computerDao.update(computerUpdate);
	}
	
	public void deleteComputer() {
		int idComputer = Integer.parseInt(this.optionList[1]);
		for(Computer elem:computerL) {
			if(elem.getId() == idComputer) {
				System.out.println("supression de l'oridnateur");
				this.computerDao.delete(elem);
				return;
			}
		}
		System.out.println("Ordinateur absent de la BDD");
	}
}