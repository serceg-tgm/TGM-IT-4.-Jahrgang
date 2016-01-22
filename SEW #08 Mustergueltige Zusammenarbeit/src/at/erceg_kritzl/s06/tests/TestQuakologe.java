package at.erceg_kritzl.s06.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import at.erceg_kritzl.s06.AbstrakteEntenFabrik;
import at.erceg_kritzl.s06.Entenfabrik;
import at.erceg_kritzl.s06.Quakfaehig;
import at.erceg_kritzl.s06.Quakologe;
/**
 * Die Methoden der Klasse 'Quakologe' werden getestet.
 * 
 * @author Stefan Erceg
 * @version 20141217
 *
 */
public class TestQuakologe {

	private Quakologe quakologe;
	private AbstrakteEntenFabrik entenFabrik;
	private Quakfaehig moorEnte;
	private ByteArrayOutputStream output;
	
	/**
	 * Dient zum Initialisieren der jeweiligen Attribute (Quakologe, Entenfabrik, Quakfaehig und 
	 * ByteArrayOutputStream).
	 */
	@Before
	public void initialize() {
		quakologe = new Quakologe();
		entenFabrik = new Entenfabrik();
		moorEnte = entenFabrik.erzeugeMoorEnte();
		output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
	}
	
	/**
	 * Prueft, ob beim Ausfuehren der aktualisieren-Methode der entsprechende Text in die Konsole 
	 * geschrieben wird.
	 */
	@Test
	public void testAktualisieren() {
		quakologe.aktualisieren(moorEnte);
		assertEquals("Quakologe: Moorente hat gerade gequakt.\r\n", output.toString());
	}

	/**
	 * Prueft, ob die toString-Methode den entsprechenden Text fuer den Quakologen zurueckgibt.
	 */
	@Test
	public void testToString() {
		assertEquals(quakologe.toString(), "Quakologe");	
	}

}
