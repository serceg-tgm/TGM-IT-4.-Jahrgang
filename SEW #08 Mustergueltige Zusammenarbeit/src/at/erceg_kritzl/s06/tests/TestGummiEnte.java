package at.erceg_kritzl.s06.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import at.erceg_kritzl.s06.GummiEnte;
import at.erceg_kritzl.s06.Quakologe;

/**
 * Die Methoden der Klasse 'GummiEnte' werden getestet.
 * 
 * @author Stefan Erceg
 * @version 20141217
 *
 */
public class TestGummiEnte {

	private GummiEnte gummiente;
	private Quakologe quakologe;
	private ByteArrayOutputStream output;

	/**
	 * Dient zum Initialisieren der jeweiligen Attribute (GummiEnte, Quakologe und ByteArrayOutputStream).
	 */
	@Before
	public void initialize() {
		gummiente = new GummiEnte();
		quakologe = new Quakologe();
		output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
	}

	/**
	 * Prueft, ob beim Ausfuehren der Quak-Methode der entsprechende Text in die Konsole geschrieben
	 * wird.
	 */
	@Test
	public void testQuaken() {
		gummiente.quaken();
		assertEquals("Quietsch\r\n", output.toString());
	}

	/**
	 * Fuegt den vorher initialisierten Quakologen mit der registriereBeobachter-Methode zu den
	 * Beobachtern hinzu.
	 */
	@Test
	public void testRegistriereBeobachter() {
		gummiente.registriereBeobachter(quakologe);
	}
	
	/**
	 * Prueft, ob die toString-Methode den entsprechenden Text fuer die Gummiente zurueckgibt.
	 */	
	@Test
	public void testToString() {
		assertEquals(gummiente.toString(), "Gummiente");	
	}

}