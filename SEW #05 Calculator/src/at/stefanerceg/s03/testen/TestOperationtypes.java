package at.stefanerceg.s03.testen;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import at.stefanerceg.s03.AdditionCalculator;
import at.stefanerceg.s03.Calculator;
import at.stefanerceg.s03.DivisionCalculator;
import at.stefanerceg.s03.MultiplicationCalculator;
import at.stefanerceg.s03.SubtractionCalculator;

/**
* 
* Diese Testklasse testet alle Klassen, die das Interface Calculateable implementieren (AdditionCalculator,
* SubstractionCalculator, MultiplicationCalculator und DivisionCalculator) und deren execute-Methode. <br>
* 
* @author Stefan Erceg
* @since 06.11.2014
* @version 20141112
*
*/


public class TestOperationtypes {

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
	 * Testet, ob jeder Wert den Modifier hinzuaddiert.
	 */
	
	@Test
	public void testAdditionCalculator() {

		c.setModifier(10.0);
		c.addValue(100.0);
		c.addValue(200.0);
		c.addValue(300.0);
		c.setCalculateable(new AdditionCalculator());
		c.executeCalculation();
		
		assertEquals("Modifier: 10.0\nValues: 110.0 | 210.0 | 310.0 | ", c.toString());
		
	}
	
	/**
	 * Testet, ob jeder Wert den Modifier subtrahiert.
	 */
	
	@Test
	public void testSubstractionCalculator() {

		c.setModifier(10.0);
		c.addValue(100.0);
		c.addValue(200.0);
		c.addValue(300.0);
		c.setCalculateable(new SubtractionCalculator());
		c.executeCalculation();
		
		assertEquals("Modifier: 10.0\nValues: 90.0 | 190.0 | 290.0 | ", c.toString());
		
	}
	
	/**
	 * Testet, ob jeder Wert mal dem Modifier multipliziert.
	 */
	
	@Test
	public void testMultiplicationCalculator() {

		c.setModifier(10.0);
		c.addValue(100.0);
		c.addValue(200.0);
		c.addValue(300.0);
		c.setCalculateable(new MultiplicationCalculator());
		c.executeCalculation();
		
		assertEquals("Modifier: 10.0\nValues: 1000.0 | 2000.0 | 3000.0 | ", c.toString());
		
	}
	
	/**
	 * Testet, ob jeder Wert durch den Modifier dividiert.
	 */
	
	@Test
	public void testDivisionCalculator() {

		c.setModifier(10.0);
		c.addValue(100.0);
		c.addValue(200.0);
		c.addValue(300.0);
		c.setCalculateable(new DivisionCalculator());
		c.executeCalculation();
		
		assertEquals("Modifier: 10.0\nValues: 10.0 | 20.0 | 30.0 | ", c.toString());
		
	}

	/**
	 * Bei diesem Testfall wird der größte Wert von Double als Modifier eingesetzt und auch in die Liste gespeichert.
	 * Bei einer Addition kommt eine unendliche Zahl heraus.
	 */
	
	@Test
	public void testExecuteAdd1() {
		c.setModifier(Double.MAX_VALUE);
		c.addValue(Double.MAX_VALUE);
		c.setCalculateable(new AdditionCalculator());
		c.executeCalculation();
		
		assertEquals("Modifier: " + Double.MAX_VALUE + "\nValues: " + Double.POSITIVE_INFINITY + " | ", c.toString());
	}
	
	/**
	 * Bei diesem Testfall wird der größte Wert von Double * -1 als Modifier eingesetzt und der größte Wert in die Liste 
	 * gespeichert.
	 * Bei einer Addition ergibt der neue Wert 0.0.
	 */
	
	@Test
	public void testExecuteAdd2() {
		c.setModifier(Double.MAX_VALUE * -1);
		c.addValue(Double.MAX_VALUE);
		c.setCalculateable(new AdditionCalculator());
		c.executeCalculation();
		
		assertEquals("Modifier: " + Double.MAX_VALUE * -1 + "\nValues: 0.0 | ", c.toString());
	}
	
	/**
	 * Bei diesem Testfall wird der größte Wert von Double * -1 als Modifier eingesetzt und auch in die Liste 
	 * gespeichert.
	 * Bei einer Addition kommt eine unendliche (negative) Zahl heraus.
	 */
	
	@Test
	public void testExecuteAdd3() {
		c.setModifier(Double.MAX_VALUE * -1);
		c.addValue(Double.MAX_VALUE * -1);
		c.setCalculateable(new AdditionCalculator());
		c.executeCalculation();
		
		assertEquals("Modifier: " + Double.MAX_VALUE * -1 + "\nValues: " + Double.NEGATIVE_INFINITY + " | ", c.toString());
	}
	
	/**
	 * Bei diesem Testfall wird der größte Wert von Double als Modifier eingesetzt und auch in die Liste gespeichert.
	 * Bei einer Subtraktion ergibt der neue Wert 0.0.
	 */
	
	@Test
	public void testExecuteSub1() {
		
		c.setModifier(Double.MAX_VALUE);
		c.addValue(Double.MAX_VALUE);
		c.setCalculateable(new SubtractionCalculator());
		c.executeCalculation();
		
		assertEquals("Modifier: " + Double.MAX_VALUE + "\nValues: 0.0 | ", c.toString());
	}
	
	/**
	 * Bei diesem Testfall wird der größte Wert von Double * -1 als Modifier eingesetzt und der größte Wert in die Liste 
	 * gespeichert.
	 * Bei einer Subtraktion kommt eine unendliche (negative) Zahl heraus.
	 */
	
	@Test
	public void testExecuteSub2() {
		
		c.setModifier(Double.MAX_VALUE*-1);
		c.addValue(Double.MAX_VALUE);
		c.setCalculateable(new SubtractionCalculator());
		c.executeCalculation();
		
		assertEquals("Modifier: " + Double.MAX_VALUE*-1 + "\nValues: " + Double.POSITIVE_INFINITY + " | ", c.toString());
	}
	
	/**
	 * Bei diesem Testfall wird der größte Wert von Double * -1 als Modifier eingesetzt und auch in die Liste 
	 * gespeichert.
	 * Bei einer Subtraktion ergibt der neue Wert 0.0.
	 */
	
	@Test
	public void testExecuteSub3() {
		
		c.setModifier(Double.MAX_VALUE*-1);
		c.addValue(Double.MAX_VALUE*-1);
		c.setCalculateable(new SubtractionCalculator());
		c.executeCalculation();
		
		assertEquals("Modifier: " + Double.MAX_VALUE*-1 + "\nValues: 0.0 | ", c.toString());
	}
	
	/**
	 * Bei diesem Testfall wird der größte Wert von Double als Modifier eingesetzt und auch in die Liste gespeichert.
	 * Bei einer Multiplikation kommt eine unendliche Zahl heraus.
	 */
	
	@Test
	public void testExecuteMpl1() {
		
		c.setModifier(Double.MAX_VALUE);
		c.addValue(Double.MAX_VALUE);
		c.setCalculateable(new MultiplicationCalculator());
		c.executeCalculation();
		
		assertEquals("Modifier: " + Double.MAX_VALUE + "\nValues: " + Double.POSITIVE_INFINITY + " | ", c.toString());
	}
	
	/**
	 * Bei diesem Testfall wird der größte Wert von Double * -1 als Modifier eingesetzt und der größte Wert in die Liste 
	 * gespeichert.
	 * Bei einer Multiplikation kommt eine unendliche (negative) Zahl heraus.
	 */
	
	@Test
	public void testExecuteMpl2() {
		
		c.setModifier(Double.MAX_VALUE*-1);
		c.addValue(Double.MAX_VALUE);
		c.setCalculateable(new MultiplicationCalculator());
		c.executeCalculation();
		
		assertEquals("Modifier: " + Double.MAX_VALUE*-1+ "\nValues: " + Double.NEGATIVE_INFINITY + " | ", c.toString());
	}
	
	/**
	 * Bei diesem Testfall wird der größte Wert von Double als Modifier eingesetzt und auch in die Liste gespeichert.
	 * Bei einer Division ergibt der neue Wert 1.0.
	 */
	
	@Test
	public void testExecuteDiv1() {
		
		c.setModifier(Double.MAX_VALUE);
		c.addValue(Double.MAX_VALUE);
		c.setCalculateable(new DivisionCalculator());
		c.executeCalculation();
		
		assertEquals("Modifier: " + Double.MAX_VALUE + "\nValues: 1.0 | ", c.toString());
	}
	
	/**
	 * Bei diesem Testfall wird der größte Wert von Double * -1 als Modifier eingesetzt und der größte Wert in die Liste 
	 * gespeichert.
	 * Bei einer Division ergibt der neue Wert -1.0.
	 */
	
	@Test
	public void testExecuteDiv2() {

		c.setModifier(Double.MAX_VALUE*-1);
		c.addValue(Double.MAX_VALUE);
		c.setCalculateable(new DivisionCalculator());
		c.executeCalculation();
		
		assertEquals("Modifier: " + Double.MAX_VALUE*-1 + "\nValues: -1.0 | ", c.toString());
		
	}
	
	/**
	 * Testet, ob bei einer Division durch 0 "NaN" (Not a Number) zurückgegeben wird.
	 */
	
	@Test
	public void testExecuteDiv_divide0() {
		
		c.setModifier(0.0);
		c.addValue(0.0);
		c.setCalculateable(new DivisionCalculator());
		c.executeCalculation();
		
		assertEquals("Modifier: 0.0\nValues: " + Double.NaN + " | ", c.toString());
	}

}
