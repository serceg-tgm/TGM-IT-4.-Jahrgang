package at.stefanerceg.s03.testen;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import at.stefanerceg.s03.AdditionCalculator;
import at.stefanerceg.s03.Calculateable;
import at.stefanerceg.s03.Calculator;

/**
* 
* Diese Testklasse testet die Klasse "Calculator". <br>
* 
* @author Stefan Erceg
* @since 06.11.2014
* @version 20141112
*
*/

public class TestCalculator {

	/* ein Calculator-Attribut wird erstellt */
	
	private Calculator c;
	
	/**
	 * Das Calculator-Attribut wird initialisiert.
	 */
	
	@Before
	public void initializeCalculator() {
		c = new Calculator();
	}
	
	/**
	 * Der Konstruktor der Klasse Calculator wird überprüft.
	 */
	
	@Test
	public void testConstructor() {
		assertNotNull(c);
	}
	
	/**
	 * Testet, ob Werte zur Liste hinzugefügt werden.
	 */
	
	@Test
	public void testAddValue() {
		c.addValue(100.0);
		assertEquals("Modifier: 0.0\nValues: 100.0 | ", c.toString());
	}
	
	/**
	 * Testet, ob einzigartige Werte, d.h. Werte, die nur einmal in der Liste vorkommen, von der Liste entfernt werden.
	 */
	
	@Test
	public void testRemoveValue_einzigartigerWert() {
		c.addValue(10.0);
		c.addValue(10.5);
		c.addValue(11.0);
		c.removeValue(10.0);
		assertEquals("Modifier: 0.0\nValues: 10.5 | 11.0 | ", c.toString());
	}
	
	/**
	 * Testet, ob bei doppelt vorkommenden Werten, d.h. Werten, die mehrmals in der Liste vorhanden sind, das
	 * erstvorkommende Element gelöscht wird.
	 */
	
	@Test
	public void testRemoveValue_doppeltVorkommenderWert() {
		c.addValue(10.0);
		c.addValue(10.5);
		c.addValue(10.0);
		c.removeValue(10.0);
		assertEquals("Modifier: 0.0\nValues: 10.5 | 10.0 | ", c.toString());
	}
	
	/**
	 * Testet die setter- und getter-Methoden für das Setzen des Operationstyps bzw. das Herausfinden des aktuell
	 * verwendeten Operationstyps.
	 */
	
	@Test
	public void testSetGetCalculateable() {
		Calculateable add = new AdditionCalculator();
		c.setCalculateable(add);
		assertEquals(add, c.getCalculateable());
	}
	
	/**
	 * Testet, ob setModifier() das Attribut "modifier" korrekt setzt.
	 */
	
	@Test
	public void testSetModifier() {
		c.setModifier(1.0);
		assertEquals("Modifier: 1.0\nValues: ", c.toString());
	}
	
	/**
	 * Testet, ob der Modifier und die Werte aus der Liste korrekt als String ausgegeben werden.
	 */
	
	@Test
	public void testToString() {
		c.addValue(100.0);
		assertEquals("Modifier: 0.0\nValues: 100.0 | ", c.toString());
	}
	
	/**
	 * Testet, ob beim Setzen des entsprechenden Operationstyps und Ausführen der execute-Methode der Wert richtig
	 * verändert wird.
	 */
	
	@Test
	public void testExecuteCalculation() {

		c.setModifier(10.0);
		c.addValue(100.0);
		c.addValue(200.0);
		c.addValue(300.0);
		c.setCalculateable(new AdditionCalculator());
		c.executeCalculation();
		
		assertEquals("Modifier: 10.0\nValues: 110.0 | 210.0 | 310.0 | ", c.toString());
		
	}
	
	

}
