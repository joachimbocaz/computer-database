package excilys.formation.java.cbd.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import excilys.formation.java.cbd.model.Computer;
import junit.framework.TestCase;

public class TestComputerMapper extends TestCase {
	private final static String ATTRIBUT_COMPUTER = "id"; 
	private final static String ATTRIBUT_NAME = "name";
	private final static String ATTRIBUT_INTRODUCED = "introduced";
	private final static String ATTRIBUT_DISCONTINUED = "discontinued";
	private final static String ATTRIBUT_COMPANY_ID = "company_id";
	
	private final static Integer ID_COMPUTER = 1;
	private final static String NAME_COMPANIE = "apple";
	private final static LocalDate DATE_INTRODUCED = LocalDate.of(2020, 1, 1);
	private final static LocalDate DATE_DISCONTINUED = LocalDate.of(2021, 1, 2);
	private final static Integer COMPANY_ID = 1;


	
	private ResultSet resultSet = Mockito.mock(ResultSet.class);
	
	@Before
	public void setUp() throws SQLException {
		System.out.println(DATE_INTRODUCED);
		System.out.println(DATE_DISCONTINUED);
		Mockito.when(resultSet.getInt(ATTRIBUT_COMPUTER)).thenReturn(ID_COMPUTER);
		Mockito.when(resultSet.getString(ATTRIBUT_NAME)).thenReturn(NAME_COMPANIE);
		Mockito.when(resultSet.getDate(ATTRIBUT_INTRODUCED)).thenReturn(Date.valueOf(DATE_INTRODUCED));
		Mockito.when(resultSet.getDate(ATTRIBUT_DISCONTINUED)).thenReturn(Date.valueOf(DATE_DISCONTINUED));
		Mockito.when(resultSet.getInt(ATTRIBUT_COMPANY_ID)).thenReturn(COMPANY_ID);
	}
	
//	@Test
//	public void testEntity() throws SQLException{
//		Computer computer = ComputerMapper.entity(resultSet);
//		Companie companie = CompanieMapper.entity(resultSet);
//		Computer computerExpected = new Computer(ID_COMPUTER, NAME_COMPANIE, COMPANY_ID, DATE_INTRODUCED, DATE_DISCONTINUED);
//		assertEquals(computer, computerExpected);
//	}
//	
//	@Test
//	public void testEntityDateInNull() throws SQLException{
//		Mockito.when(resultSet.getDate(ATTRIBUT_INTRODUCED)).thenReturn(null);
//		Computer computer = ComputerMapper.entity(resultSet);
//		Computer computerExpected = new Computer(ID_COMPUTER, NAME_COMPANIE, COMPANY_ID, null, DATE_DISCONTINUED);
//		assertEquals(computer, computerExpected);
//	}
//	
//	@Test
//	public void testEntityDateOutNull() throws SQLException{
//		Mockito.when(resultSet.getDate(ATTRIBUT_DISCONTINUED)).thenReturn(null);
//		Computer computer = ComputerMapper.entity(resultSet);
//		Computer computerExpected = new Computer(ID_COMPUTER, NAME_COMPANIE, COMPANY_ID, DATE_INTRODUCED, null);
//		assertEquals(computer, computerExpected);
//	}
}
