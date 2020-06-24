package excilys.formation.java.cbd.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import excilys.formation.java.cbd.model.Companie;
import junit.framework.TestCase;

public class TestCompanieMapper extends TestCase {
	private final static String ATTRIBUT_ID_COMPANIE = "id"; 
	private final static String ATTRIBUT_NAME = "name";
	
	private final static Integer ID_COMPANIE = 1;
	private final static String NAME_COMPANIE = "apple";
	
	private ResultSet resultSet = Mockito.mock(ResultSet.class);
	
	@Before
	public void setUp() throws SQLException {
		Mockito.when(resultSet.getInt(ATTRIBUT_ID_COMPANIE)).thenReturn(ID_COMPANIE);
		Mockito.when(resultSet.getString(ATTRIBUT_NAME)).thenReturn(NAME_COMPANIE);
	}
	
	@Test
	public void testEntity() throws SQLException{
		Companie companie = CompanieMapper.entity(resultSet);
		Companie companieExpected = new Companie(ID_COMPANIE, NAME_COMPANIE);
		assertEquals(companie, companieExpected);
	}
}
