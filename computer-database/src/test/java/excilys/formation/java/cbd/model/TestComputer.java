package excilys.formation.java.cbd.model;

import java.time.LocalDate;

import org.junit.Test;

import junit.framework.TestCase;

public class TestComputer extends TestCase {
	
	@Test
	public void testGetId() {
		Computer computer = new Computer(1, "test");
		assertEquals(1, computer.getId());
	}
	
	@Test
	public void testGetName() {
		Computer computer = new Computer(1, "test");
		assertEquals("test", computer.getName());
	}
	
	@Test
	public void testGetManufacturer() {
		Computer computer = new Computer(1, "test", 2);
		assertEquals(new Integer(2), computer.getManufacturer());
	}
	
	@Test
	public void testGetDateIn() {
		LocalDate dateIn = LocalDate.of(2020, 1, 8);
		LocalDate dateOut = LocalDate.of(2021, 1, 8);
		Computer computer = new Computer(1, "test", 2, dateIn, dateOut);
		assertEquals(dateIn, computer.getDateIn());
	}
	
	@Test
	public void testSetDateIn() {
		LocalDate dateIn = LocalDate.of(2020, 1, 8);
		LocalDate dateOut = LocalDate.of(2021, 1, 8);
		LocalDate newDateIn = LocalDate.of(2020, 5, 8);
		Computer computer = new Computer(1, "test", 2, dateIn, dateOut);
		computer.setDateIn(newDateIn);
		assertEquals(newDateIn, computer.getDateIn());
	}
	
	@Test
	public void testGetDateOut() {
		LocalDate dateIn = LocalDate.of(2020, 1, 8);
		LocalDate dateOut = LocalDate.of(2021, 1, 8);
		Computer computer = new Computer(1, "test", 2, dateIn, dateOut);
		assertEquals(dateOut, computer.getDateOut());
	}
	
	@Test
	public void testSetDateOut() {
		LocalDate dateIn = LocalDate.of(2020, 1, 8);
		LocalDate dateOut = LocalDate.of(2021, 1, 8);
		LocalDate newDateOut = LocalDate.of(2022, 5, 8);
		Computer computer = new Computer(1, "test", 2, dateIn, dateOut);
		computer.setDateOut(newDateOut);
		assertEquals(newDateOut, computer.getDateOut());
	}
	
	@Test
	public void testComputer() {
		LocalDate dateIn = LocalDate.of(2020, 1, 8);
		LocalDate dateOut = LocalDate.of(2021, 1, 8);
		Computer computer = new Computer(1, "test", 2, dateIn, dateOut);
		assertEquals(dateIn.isBefore(dateOut), computer.getDateIn().isBefore(computer.getDateOut()));
	}
}
