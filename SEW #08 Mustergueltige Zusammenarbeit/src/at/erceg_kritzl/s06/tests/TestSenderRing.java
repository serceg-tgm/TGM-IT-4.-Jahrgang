package at.erceg_kritzl.s06.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import at.erceg_kritzl.s06.Beobachter;
import at.erceg_kritzl.s06.MoorEnte;
import at.erceg_kritzl.s06.QuakBeobachtungsSubjekt;
import at.erceg_kritzl.s06.Quakologe;
import at.erceg_kritzl.s06.SenderRing;

/**
 * Die Methoden der Klasse 'SenderRing' werden getestet.
 * 
 * @author Martin Kritzl
 * @version 20141217
 *
 */
public class TestSenderRing {

	private SenderRing ring;
	private QuakBeobachtungsSubjekt ente;
	private Beobachter beobachter;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	/**
	 * Initialisiert den OutputStream
	 */
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}
	
	/**
	 * Initialisieren der Attribute
	 */
	@Before
	public void prepare() {
		this.ente = new MoorEnte();
		this.ring = new SenderRing(ente);
		this.beobachter = new Quakologe();
	}
	
	/**
	 * Registriert einen Benutzer und prueft ob dieser wieder ausgelesen werden kann
	 */
	@Test
	public void registerGetBeobachter() {
		this.ring.registriereBeobachter(this.beobachter);
		assertEquals(this.beobachter, this.ring.getBeobachtende().next());
	}
	
	/**
	 * Registriert einen Beobachter der benachrichtigt wird, dass neue Enten gesichtet wurden.
	 * Es wird dann ueberprueft, ob in der Konsole ausgegeben wurde, ob der Beobachter diese
	 * Information erhalten hat
	 */
	@Test
	public void benachrichtigeBeobachtende() {
		this.ring.registriereBeobachter(this.beobachter);
		this.ring.benachrichtigeBeobachtende();
		assertEquals("Quakologe: Moorente hat gerade gequakt.\r\n", this.outContent.toString());
	}
	

}
