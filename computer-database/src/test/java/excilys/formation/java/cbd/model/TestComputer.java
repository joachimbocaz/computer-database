package excilys.formation.java.cbd.model;

import java.time.LocalDate;

import org.junit.Test;

import junit.framework.TestCase;

public class TestComputer extends TestCase {
	
//	@Test
//	public void testGetId() {
//		Computer computer = new Computer(1, "test");
//		assertEquals(1, computer.getId());
//	}
//	
//	@Test
//	public void testGetName() {
//		Computer computer = new Computer(1, "test");
//		assertEquals("test", computer.getName());
//	}
//	
//	@Test
//	public void testGetManufacturer() {
//		Computer computer = new Computer(1, "test", 2);
//		assertEquals(new Integer(2), computer.getManufacturer());
//	}
//	
//	@Test
//	public void testSetManufacturer() {
//		LocalDate dateIn = LocalDate.of(2020, 1, 8);
//		LocalDate dateOut = LocalDate.of(2021, 1, 8);
//		Computer computer = new Computer(1, "test", 2, dateIn, dateOut);
//		computer.setManufacturer(3);
//		assertEquals(new Integer(3), computer.getManufacturer());
//	}
//	
//	@Test
//	public void testGetDateIn() {
//		LocalDate dateIn = LocalDate.of(2020, 1, 8);
//		LocalDate dateOut = LocalDate.of(2021, 1, 8);
//		Computer computer = new Computer(1, "test", 2, dateIn, dateOut);
//		assertEquals(dateIn, computer.getDateIn());
//	}
//	
//	@Test
//	public void testSetDateIn() {
//		LocalDate dateIn = LocalDate.of(2020, 1, 8);
//		LocalDate dateOut = LocalDate.of(2021, 1, 8);
//		LocalDate newDateIn = LocalDate.of(2020, 5, 8);
//		Computer computer = new Computer(1, "test", 2, dateIn, dateOut);
//		computer.setDateIn(newDateIn);
//		assertEquals(newDateIn, computer.getDateIn());
//	}
//	
//	@Test
//	public void testSetDateInNull() {
//		LocalDate dateIn = LocalDate.of(2020, 1, 8);
//		LocalDate dateOut = LocalDate.of(2021, 1, 8);
//		LocalDate newDateIn = null;
//		Computer computer = new Computer(1, "test", 2, dateIn, dateOut);
//		computer.setDateIn(newDateIn);
//		assertNull(computer.getDateIn());
//	}
//	
//	@Test
//	public void testSetDateInOutNull() {
//		LocalDate dateIn = LocalDate.of(2020, 1, 8);
//		LocalDate dateOut = null;
//		LocalDate newDateIn = LocalDate.of(2021, 1, 8);
//		Computer computer = new Computer(1, "test", 2, dateIn, dateOut);
//		computer.setDateIn(newDateIn);
//		assertEquals(newDateIn, computer.getDateIn());
//	}
//	
//	@Test
//	public void testGetDateOut() {
//		LocalDate dateIn = LocalDate.of(2020, 1, 8);
//		LocalDate dateOut = LocalDate.of(2021, 1, 8);
//		Computer computer = new Computer(1, "test", 2, dateIn, dateOut);
//		assertEquals(dateOut, computer.getDateOut());
//	}
//	
//	@Test
//	public void testSetDateOut() {
//		LocalDate dateIn = LocalDate.of(2020, 1, 8);
//		LocalDate dateOut = LocalDate.of(2021, 1, 8);
//		LocalDate newDateOut = LocalDate.of(2022, 5, 8);
//		Computer computer = new Computer(1, "test", 2, dateIn, dateOut);
//		computer.setDateOut(newDateOut);
//		assertEquals(newDateOut, computer.getDateOut());
//	}
//	
//	@Test
//	public void testSetDateOutNull() {
//		LocalDate dateIn = LocalDate.of(2020, 1, 8);
//		LocalDate dateOut = LocalDate.of(2021, 1, 8);
//		LocalDate newDateOut = null;
//		Computer computer = new Computer(1, "test", 2, dateIn, dateOut);
//		computer.setDateOut(newDateOut);
//		assertNull(computer.getDateOut());
//	}
//	
//	@Test
//	public void testSetDateOutInNull() {
//		LocalDate dateIn = null;
//		LocalDate dateOut = LocalDate.of(2021, 1, 8);
//		LocalDate newDateOut = LocalDate.of(2020, 1, 8);
//		Computer computer = new Computer(1, "test", 2, dateIn, dateOut);
//		computer.setDateOut(newDateOut);
//		assertEquals(newDateOut, computer.getDateOut());
//	}
//	
//	@Test
//	public void testComputer() {
//		Computer computer = new Computer();
//		assertNotNull(computer);
//	}
//	
//	@Test
//	public void testComputerWithDateIn() {
//		LocalDate dateIn = LocalDate.of(2020, 1, 8);
//		Computer computer = new Computer(1, "test", 2, dateIn);
//		assertEquals(dateIn, computer.getDateIn());
//	}
//	
//	@Test
//	public void testComputerAllParam() {
//		LocalDate dateIn = LocalDate.of(2020, 1, 8);
//		LocalDate dateOut = LocalDate.of(2021, 1, 8);
//		Computer computer = new Computer(1, "test", 2, dateIn, dateOut);
//		assertEquals(dateIn.isBefore(dateOut), computer.getDateIn().isBefore(computer.getDateOut()));
//	}
//	
//	@Test
//	public void testComputerAllParamDateInFail() {
//		LocalDate dateIn = null;
//		LocalDate dateOut = LocalDate.of(2021, 1, 8);
//		Computer computer = new Computer(1, "test", 2, dateIn, dateOut);
//		assertNull(computer.getDateIn());
//	}
//	
//	@Test
//	public void testComputerAllParamDateOutFail() {
//		LocalDate dateIn = LocalDate.of(2020, 1, 8);
//		LocalDate dateOut = null;
//		Computer computer = new Computer(1, "test", 2, dateIn, dateOut);
//		assertNull(computer.getDateOut());
//	}
//	
//	@Test
//	public void testComputerAllParamDateInAfterOut() {
//		LocalDate dateIn = LocalDate.of(2020, 1, 8);
//		LocalDate dateOut = LocalDate.of(2020, 1, 8);
//		Computer computer = new Computer(1, "test", 2, dateIn, dateOut);
//		assertNull(computer.getDateIn());
//	}
//	
//	@Test
//	public void testComputerWithCompanie() {
//		LocalDate dateIn = LocalDate.of(2020, 1, 8);
//		LocalDate dateOut = LocalDate.of(2020, 1, 8);
//		Companie companie = new Companie(1, "toto");
//		Computer computer = new Computer(1, "test", 2, dateIn, dateOut, companie);
//		assertEquals(companie, computer.getCompanie());
//	}
//	
//	@Test
//	public void testComputerWithCompanieInNull() {
//		LocalDate dateOut = LocalDate.of(2020, 1, 8);
//		Companie companie = new Companie(1, "toto");
//		Computer computer = new Computer(1, "test", 2, null, dateOut, companie);
//		assertNull(computer.getDateIn());
//	}
//	
//	@Test
//	public void testComputerWithCompanieOutNull() {
//		LocalDate dateIn = LocalDate.of(2020, 1, 8);
//		Companie companie = new Companie(1, "toto");
//		Computer computer = new Computer(1, "test", 2, dateIn, null, companie);
//		assertNull(computer.getDateOut());
//	}
//	
//	@Test
//	public void testComputerWithCompanieInBeforeOut() {
//		LocalDate dateIn = LocalDate.of(2013, 1, 8);
//		LocalDate dateOut = LocalDate.of(2020, 1, 8);
//		Companie companie = new Companie(1, "toto");
//		Computer computer = new Computer(1, "test", 2, dateIn, dateOut, companie);
//		assertTrue(computer.getDateIn().isBefore(computer.getDateOut()));
//	}
//	
}
