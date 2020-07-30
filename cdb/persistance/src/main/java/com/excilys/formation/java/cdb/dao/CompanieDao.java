package com.excilys.formation.java.cdb.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.formation.java.cdb.model.Companie;

@Repository
public interface CompanieDao extends JpaRepository<Companie, Integer>{

	Integer countByNameContaining(String search);

	List<Companie> findByNameContaining(String search, Pageable pageable);

}
