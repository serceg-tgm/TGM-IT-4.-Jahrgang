package org.apache.camel.example.etl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * In dieser Testklasse wird die Klasse 'PersonDocument' getestet. Dabei wird ueberprueft, ob bestimmte Werte mit setter-Methoden gesetzt und mit getter-Methoden 
 * danach korrekt ausgelesen werden.
 * 
 * @author Erceg (serceg@student.tgm.ac.at)
 * @version 20150301
 */
public class PersonDocumentTest {

	public PersonDocument pd;
	
	@Before
	public void initialize() {
		pd = new PersonDocument();
	}
	
	@Test
	public void testSetGetCity() {
		pd.setCity("Vienna");
		assertEquals("Vienna", pd.getCity());
	}
	
	@Test
	public void testSetGetFirstName() {
		pd.setFirstName("Stefan");
		assertEquals("Stefan", pd.getFirstName());
	}
	
	@Test
	public void testSetGetLastName() {
		pd.setLastName("Erceg");
		assertEquals("Erceg", pd.getLastName());
	}
	
	@Test
	public void testSetGetUser() {
		pd.setUser("serceg");
		assertEquals("serceg", pd.getUser());
	}
	
	@Test
	public void testToString() {
		pd.setUser("serceg");
		assertEquals("Person[user: serceg]", pd.toString());
	}

}
