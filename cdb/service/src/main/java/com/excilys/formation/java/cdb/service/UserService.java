package com.excilys.formation.java.cdb.service;

import com.excilys.formation.java.cdb.model.User;

public interface UserService {

	User findByUsername(String username);

}
