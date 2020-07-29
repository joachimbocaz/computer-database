package com.excilys.formation.java.cdb.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.formation.java.cdb.model.Computer;

@Repository
public interface ComputerDao extends JpaRepository<Computer, Integer>{

	List<Computer> findByNameContaining(String search, Pageable pageable);
	
	Integer countByNameContaining(String search);
	
	public default List<String> splitOrder(String order) {
		String column = order.substring(0, 2);
		String orderColumn = order.substring(2, 5);
		
		if(column.equals("cn")) {
			column = "name";
		}
		else if(column.equals("di")) {
			column = "introduced";
		}
		else if(column.equals("dd")) {
			column = "discontinued";
		}
		else if(column.equals("ci")) {
			column = "companie.name";
		}
		if(orderColumn.contentEquals("DSC")) {
			orderColumn = "DESC";
		}

		List<String> styleOrder = new ArrayList<String>();
		styleOrder.add(column);
		styleOrder.add(orderColumn);
		
		return styleOrder;
	}

	
	
}
