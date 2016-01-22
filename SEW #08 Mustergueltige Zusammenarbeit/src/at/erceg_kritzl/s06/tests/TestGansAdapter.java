package at.erceg_kritzl.s06.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import at.erceg_kritzl.s06.Gans;
import at.erceg_kritzl.s06.GansAdapter;
import at.erceg_kritzl.s06.Quakologe;

/**
 * Die Methoden der Klasse 'GansAdapter' werden getestet.
 * 
 * @author Stefan Erceg
 * @version 20141217
 *
 */
public class TestGansAdapter {

	private Gans gans;
	private GansAdapter gansadapter;
	private Quakologe quakologe;
	private ByteArrayOutputStream output;

	/**
	 * Dient zum Initialisieren der jeweiligen Attribute (Gans, GansAdapter, Quakologe und 
	 * ByteArrayOutputStream).
	 */
	@Before
	public void initialize() {
		gans = new Gans();
		gansadapter = new GansAdapter(gans);
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
		gansadapter.quaken();
		assertEquals("Schnatter\r\n", output.toString());
	}

	/**
	 * Fuegt den vorher initialisierten Quakologen mit der registriereBeobachter-Methode zu den
	 * Beobachtern hinzu.
	 */
	@Test
	public void testRegistriereBeobachter() {
		gansadapter.registriereBeobachter(quakologe);
	}

	/**
	 * Prueft, ob die toString-Methode den entsprechenden Text fuer den GansAdapter zurueckgibt.
	 */
	@Test
	public void testToString() {
		assertEquals(gansadapter.toString(), "sich als Ente ausgebende Gans");	
	}

}
