package com.excilys.formation.java.cdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.java.cdb.service.ComputerService;

@RestController	
public class AddComputerRestController {
	@Autowired
	private static ComputerService computerService;
}
