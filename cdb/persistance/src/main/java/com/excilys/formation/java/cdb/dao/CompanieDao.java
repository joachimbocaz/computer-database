package com.excilys.formation.java.cdb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.formation.java.cdb.model.Companie;

@Repository
public interface CompanieDao extends JpaRepository<Companie, Integer>{

}
