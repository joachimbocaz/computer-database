package excilys.formation.java.cbd.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import excilys.formation.java.cbd.model.Companie;

@Repository
public interface CompanieDao extends JpaRepository<Companie, Long>{}
