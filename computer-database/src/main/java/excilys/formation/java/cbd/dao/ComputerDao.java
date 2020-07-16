package excilys.formation.java.cbd.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import excilys.formation.java.cbd.mapper.ComputerRowMapper;
import excilys.formation.java.cbd.model.Computer;
import excilys.formation.java.cbd.model.ComputerPage;
import excilys.formation.java.cbd.model.Page;

@Repository
public class ComputerDao extends Dao<Computer>{
	
	
	private static final String INSERT_SQL = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES(:name,:introduced,:discontinued,:company_id);";
	private static final String DELETE_SQL = "DELETE FROM computer WHERE id = :id";
	private static final String UPDATE_SQL = "UPDATE computer SET name = :name, introduced = :introduced, discontinued = :discontinued, company_id = :company_id WHERE id = :id;";
	private static final String SELECT_SQL = "SELECT computer.id as id, computer.name as name, computer.introduced as introduced, computer.discontinued as discontinued, computer.company_id as company_id, cp.name as companyName "
										   + "FROM computer "
										   + "LEFT join company as cp on computer.company_id = cp.id "
										   + "WHERE computer.id = :computer.id";
	private static final String SELECT_ALL_SQL = "SELECT computer.id as id, computer.name as name, computer.introduced as introduced, computer.discontinued as discontinued, computer.company_id as company_id "
											   + "FROM computer "
											   + "LEFT join company as cp on computer.company_id = cp.id";
	private static final String SELECT_ALL_LIMITE_SQL = "SELECT computer.id as id, computer.name as name, computer.introduced as introduced, computer.discontinued as discontinued, computer.company_id as company_id, cp.name as companyName "
													  + "FROM computer LEFT join company as cp on computer.company_id = cp.id "
													  + "ORDER BY ";
	private static final String COUNT_SQL = "SELECT COUNT(id) AS total FROM computer";
	private static final String COUNT_SEARCH_SQL = "SELECT COUNT(computer.id) FROM computer LEFT JOIN company as cp on computer.company_id = cp.id WHERE computer.name like :search OR cp.name like :search";
	private static final String SELECT_SEARCH = "SELECT computer.id as id, computer.name as name, computer.introduced as introduced, computer.discontinued as discontinued, computer.company_id as company_id, cp.name as companyName "
											  + "FROM computer "
											  + "LEFT JOIN company as cp on computer.company_id = cp.id "
											  + "WHERE computer.name like :search OR cp.name like :search Order By ";

	private NamedParameterJdbcTemplate vJdbcTemplate;

	private String limitSearchOrderSql(String column, String order) {
		String requete = SELECT_SEARCH + column + " IS NULL, " + column + " "  + order + " LIMIT :offset, :nb";
		return requete;
	}

	private String limitOrderSql(String column, String order) {
		String requete = SELECT_ALL_LIMITE_SQL + column + " IS NULL, " + column +  " " + order + " LIMIT :offset, :nb";
		return requete;
	}

//	private static Logger logger = LoggerFactory.getLogger(ComputerDao.class);

//	@Autowired
//	private ConnectDB connect;
	
	@Autowired
	public ComputerDao(NamedParameterJdbcTemplate vJdbcTemplate) {
		this.vJdbcTemplate = vJdbcTemplate;
	}
	
	@Override
	public boolean create(Computer computer) {
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("name", computer.getName())
				.addValue("introduced", computer.getDateIn() == null ? null:Date.valueOf(computer.getDateIn()))
				.addValue("discontinued", computer.getDateOut() == null ? null:Date.valueOf(computer.getDateOut()))
				.addValue("company_id", computer.getCompanie() == null ? null:computer.getCompanie().getId());
		vJdbcTemplate.update(INSERT_SQL, parameters);
		return true;
	}
	
	@Override
	public boolean delete(int id) {
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("id", id);
		vJdbcTemplate.update(DELETE_SQL, parameters);
		return true;
	}
	
	@Override
	public Computer update(Computer computer) {
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("name", computer.getName())
				.addValue("introduced", computer.getDateIn() == null ? null:Date.valueOf(computer.getDateIn()))
				.addValue("discontinued", computer.getDateOut() == null ? null:Date.valueOf(computer.getDateOut()))
				.addValue("company_id", computer.getCompanie() == null ? null:computer.getCompanie().getId());
		vJdbcTemplate.update(UPDATE_SQL, parameters);
		return computer;
	}
	
	@Override
	public Computer find(int id) {
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("computer.id", id);
		Computer computer = vJdbcTemplate.queryForObject(SELECT_SQL, parameters, new ComputerRowMapper());
		return computer;
	}
	
	@Override
	public List<Computer> findAll() {
		List<Computer> allComputer = new ArrayList<>();
		allComputer = vJdbcTemplate.query(SELECT_ALL_SQL, new ComputerRowMapper());
		return allComputer;
	}

	@Override
	public List<Computer> findAllLimite(Page<Computer> page, String column, String order) {
		page = new ComputerPage();
		List<Computer> allComputer = new ArrayList<Computer>();
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("offset", page.getOffSet())
				.addValue("nb", page.getNbElementByPage());
		allComputer = vJdbcTemplate.query(limitOrderSql(column, order), parameters, new ComputerRowMapper());
		return allComputer;
	}
	
	@Override
	public int findNbElem() {
		SqlParameterSource parameters = new MapSqlParameterSource();
		return vJdbcTemplate.queryForObject(COUNT_SQL,parameters, Integer.class);
	}
	
	@Override
	public int maxId() {
		SqlParameterSource parameters = new MapSqlParameterSource();
		return vJdbcTemplate.queryForObject("SELECT MAX(id) AS max FROM computer", parameters, Integer.class);
	}
	
	public List<Computer> searchComputer(String search, ComputerPage page, String column, String order){
		List<Computer> allComputer = new ArrayList<>();
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("search", "%" + search + "%")
				.addValue("offset", page.getOffSet())
				.addValue("nb", page.getNbElementByPage());
		allComputer = vJdbcTemplate.query(limitSearchOrderSql(column, order), parameters, new ComputerRowMapper());
		return allComputer;
	}
	
	public int findNbSearchComputer(ComputerPage page, String column, String order, String search){
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("search", "%" + search + "%");
		return vJdbcTemplate.queryForObject(COUNT_SEARCH_SQL, parameters, Integer.class);
	}
		
	public ArrayList<String> splitOrder(String order) {
		String column = order.substring(0, 2);
		String orderColumn = order.substring(2, 5);
		
		if(column.equals("cn")) {
			column = "computer.name";
		}
		else if(column.equals("di")) {
			column = "computer.introduced";
		}
		else if(column.equals("dd")) {
			column = "computer.discontinued";
		}
		else if(column.equals("ci")) {
			column = "cp.name";
		}
		if(orderColumn.contentEquals("DSC")) {
			orderColumn = "DESC";
		}

		ArrayList<String> styleOrder = new ArrayList<String>();
		styleOrder.add(column);
		styleOrder.add(orderColumn);
		
		return styleOrder;
	}
}
