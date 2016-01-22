package org.apache.camel.example.etl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * In dieser Testklasse wird die Klasse 'CustomerEntity' getestet. Dabei wird ueberprueft, ob bestimmte Werte mit setter-Methoden gesetzt und mit getter-Methoden 
 * danach korrekt ausgelesen werden.
 * 
 * @author Erceg (serceg@student.tgm.ac.at)
 * @version 20150301
 */

public class CustomerEntityTest {

	public CustomerEntity ce;
	
	@Before
	public void initialize() {
		ce = new CustomerEntity();
	}
	
	@Test
	public void testSetGetId() {
		ce.setId(new Long(1));
		assertEquals(new Long(1), ce.getId());
	}
	
	@Test
	public void testSetGetCity() {
		ce.setCity("Vienna");
		assertEquals("Vienna", ce.getCity());
	}
	
	@Test
	public void testSetGetFirstName() {
		ce.setFirstName("Stefan");
		assertEquals("Stefan", ce.getFirstName());
	}
	
	@Test
	public void testSetGetPhone() {
		ce.setPhone("06811562389");
		assertEquals("06811562389", ce.getPhone());
	}
	
	@Test
	public void testSetGetStreet() {
		ce.setStreet("Wexstrasse");
		assertEquals("Wexstrasse", ce.getStreet());
	}
	
	@Test
	public void testSetGetSurname() {
		ce.setSurname("Erceg");
		assertEquals("Erceg", ce.getSurname());
	}
	
	@Test
	public void testSetGetUserName() {
		ce.setUserName("serceg");
		assertEquals("serceg", ce.getUserName());
	}
	
	@Test
	public void testSetGetZip() {
		ce.setZip("serceg");
		assertEquals("serceg", ce.getZip());
	}
	
	@Test
	public void testToString() {
		ce.setUserName("serceg");
		ce.setFirstName("Stefan");
		ce.setSurname("Erceg");
		assertEquals("Customer[userName: serceg firstName: Stefan surname: Erceg]", ce.toString());
	}

}
