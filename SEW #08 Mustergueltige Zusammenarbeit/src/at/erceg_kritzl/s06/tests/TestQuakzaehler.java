package at.erceg_kritzl.s06.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import at.erceg_kritzl.s06.Beobachter;
import at.erceg_kritzl.s06.MoorEnte;
import at.erceg_kritzl.s06.QuakZaehler;
import at.erceg_kritzl.s06.Quakfaehig;
import at.erceg_kritzl.s06.Quakologe;

/**
 * Die Methoden der Klasse 'Quakzaehler' werden getestet.
 * 
 * @author Martin Kritzl
 * @version 20141217
 *
 */
public class TestQuakzaehler {
	private Quakfaehig ente;
	private Beobachter beobachter;
	private QuakZaehler zaehler;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	/**
	 * Initialisieren der Attribute
	 */
	@Before
	public void prepare() {
		this.ente = new MoorEnte();
		this.zaehler = new QuakZaehler(this.ente);
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
	 * Laesst eine Ente in einem Quakzaehler quacken und ueberprueft, ob der Zaehler um 
	 * eins erhoeht und ob der quak in der Konsole ausgegeben wurde 
	 */
	@Test
	public void testQuacken() {
		this.zaehler.quaken();
		assertEquals(1, QuakZaehler.getQuaks());
		assertEquals("Quak\r\n", outContent.toString());
	}
	
	/**
	 * Ueberprueft die beinhalteten Enten des Quackzaehlers
	 */
	@Test
	public void testToString() {
		assertEquals("Moorente", this.zaehler.toString());
	}
	
	/**
	 * Registriert einen Beobachter der benachrichtigt wird, dass neue Enten gesichtet wurden.
	 * Es wird dann ueberprueft, ob in der Konsole ausgegeben wurde, ob der Beobachter diese
	 * Information erhalten hat
	 */
	@Test
	public void testRegistriereBeobachter() {
		this.zaehler.registriereBeobachter(beobachter);
		this.zaehler.benachrichtigeBeobachtende();
		assertEquals("Quakologe: Moorente hat gerade gequakt.\r\n", outContent.toString());
	}

}
