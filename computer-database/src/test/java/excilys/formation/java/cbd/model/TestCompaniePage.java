package excilys.formation.java.cbd.model;

import org.junit.Test;

import junit.framework.TestCase;

public class TestCompaniePage extends TestCase {
	@Test
	public void testCompaniePage() {
		CompaniePage companiePage = new CompaniePage();
		assertNotNull(companiePage);
	}
	
	@Test
	public void testCompaniePageNumPage() {
		CompaniePage companiePage = new CompaniePage(4);
		assertEquals(4, companiePage.getNumPage());
	}
	
//	@Test
//	public void testGetEntity() {
//		List<Companie> companieL = new ArrayList<Companie>();
//		Dao<Companie> companieDao = new CompanieDao();
//		Companie companie = new Companie();
//		companieL.add(companie);
//		CompaniePage companiePage = new CompaniePage(4);
//		assertEquals(4, companiePage.getNumPage());
//	}
}
