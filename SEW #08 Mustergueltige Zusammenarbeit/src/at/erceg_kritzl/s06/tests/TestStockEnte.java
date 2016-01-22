package at.erceg_kritzl.s06.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import at.erceg_kritzl.s06.Quakologe;
import at.erceg_kritzl.s06.StockEnte;

/**
 * Die Methoden der Klasse 'Stockente' werden getestet.
 * 
 * @author Stefan Erceg
 * @version 20141217
 *
 */
public class TestStockEnte {

	private StockEnte stockente;
	private Quakologe quakologe;
	private ByteArrayOutputStream output;

	/**
	 * Dient zum Initialisieren der jeweiligen Attribute (StockEnte, Quakologe und ByteArrayOutputStream).
	 */
	@Before
	public void initialize() {
		stockente = new StockEnte();
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
		stockente.quaken();
		assertEquals("Quak\r\n", output.toString());
	}

	/**
	 * Fuegt den vorher initialisierten Quakologen mit der registriereBeobachter-Methode zu den
	 * Beobachtern hinzu.
	 */
	@Test
	public void testRegistriereBeobachter() {
		stockente.registriereBeobachter(quakologe);
	}

	/**
	 * Prueft, ob die toString-Methode den entsprechenden Text fuer die Stockente zurueckgibt.
	 */
	@Test
	public void testToString() {
		assertEquals(stockente.toString(), "Stockente");	
	}

}