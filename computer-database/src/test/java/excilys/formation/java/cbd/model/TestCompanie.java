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
	

}
