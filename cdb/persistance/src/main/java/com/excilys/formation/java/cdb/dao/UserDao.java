package com.excilys.formation.java.cdb.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.formation.java.cdb.model.User;

public interface UserDao extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
