package at.erceg_kritzl.s06.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import at.erceg_kritzl.s06.Gans;

/**
 * Die Methoden der Klasse 'Gans' werden getestet.
 * 
 * @author Stefan Erceg
 * @version 20141217
 *
 */
public class TestGans {

	private Gans gans;
	private ByteArrayOutputStream output;
	
	/**
	 * Dient zum Initialisieren der jeweiligen Attribute (Gans und ByteArrayOutputStream).
	 */
	@Before
	public void initialize() {
		gans = new Gans();
		output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
	}
	
	/**
	 * Prueft, ob beim Ausfuehren der Schnatter-Methode der entsprechende Text in die Konsole geschrieben
	 * wird.
	 */
	@Test
	public void testSchnattern() {
		gans.schnattern();
		assertEquals("Schnatter\r\n", output.toString());
	}
	
	/**
	 * Prueft, ob die toString-Methode den entsprechenden Text fuer die Gans zurueckgibt.
	 */
	@Test
	public void testToString() {
		assertEquals(gans.toString(), "Gans");
		
	}

}
