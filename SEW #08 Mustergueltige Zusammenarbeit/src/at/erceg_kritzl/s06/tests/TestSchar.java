package at.erceg_kritzl.s06.tests;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import at.erceg_kritzl.s06.Beobachter;
import at.erceg_kritzl.s06.GummiEnte;
import at.erceg_kritzl.s06.MoorEnte;
import at.erceg_kritzl.s06.Quakfaehig;
import at.erceg_kritzl.s06.Quakologe;
import at.erceg_kritzl.s06.Schar;

/**
 * Die Methoden der Klasse 'Schar' werden getestet.
 * 
 * @author Martin Kritzl
 * @version 20141217
 *
 */
public class TestSchar {
	
	private Schar schar;
	private Quakfaehig ente;
	private Beobachter beobachter;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	/**
	 * Initialisieren der Attribute
	 */
	@Before
	public void prepare() {
		this.schar = new Schar();
		this.ente = new MoorEnte();
		this.beobachter = new Quakologe();
	}
	
	/**
	 * Initialisiert den OutputStream
	 */
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}
	
	/**
	 * Fuegt eine Ente zu der Schar hinzu und prueft dies ueber 'toString'
	 */
	@Test
	public void testHinzufuegenAndToString() {
		this.schar.hinzufuegen(ente);
		assertEquals("[Moorente]", this.schar.toString());
	}
	
	/**
	 * Fuegt zur Schar eine Ente hinzu und laesst alle quaken. Nun wird ueberprueft,
	 * ob dies in der Konsole ausgegeben wurde. 
	 */
	@Test
	public void testQuaken() {
		this.schar.hinzufuegen(ente);
		this.schar.hinzufuegen(new GummiEnte());
		this.schar.quaken();
		assertEquals("Quak\r\nQuietsch\r\n", outContent.toString());
	}
	
	/**
	 * Registriert einen Beobachter der benachrichtigt wird, dass neue Enten gesichtet wurden.
	 * Es wird dann ueberprueft, ob in der Konsole ausgegeben wurde, ob der Beobachter diese
	 * Information erhalten hat
	 */
	@Test
	public void testRegistriereBeobachter() {
		this.schar.hinzufuegen(ente);
		this.schar.registriereBeobachter(beobachter);
		this.schar.benachrichtigeBeobachtende();
		assertEquals("Quakologe: Moorente hat gerade gequakt.\r\n", outContent.toString());
	}
}
