package excilys.formation.java.cbd.model;

import org.junit.Test;

import junit.framework.TestCase;

public class TestCompanie extends TestCase {

	@Test
	public void testGetId() {
		Companie companie = new Companie(1, "test");
		assertEquals(1, companie.getId());
	}
	
	@Test
	public void testGetName() {
		Companie companie = new Companie(1, "test");
		assertEquals("test", companie.getName());
	}
	
	@Test
	public void testCompanieToString() {
		Companie companie = new Companie(1, "test");
		assertEquals("Companie : test", companie.toString());
	}
	
	@Test
	public void testCompabue() {
		Companie companie = new Companie();
		assertNotNull(companie);
	}
}
