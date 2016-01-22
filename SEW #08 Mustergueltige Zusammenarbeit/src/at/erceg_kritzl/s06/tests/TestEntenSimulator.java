package at.erceg_kritzl.s06.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import at.erceg_kritzl.s06.AbstrakteEntenFabrik;
import at.erceg_kritzl.s06.EntenSimulator;
import at.erceg_kritzl.s06.ZaehlendeEntenFabrik;

/**
 * Die Methoden der Klasse 'EntenSimulator' werden getestet.
 * 
 * @author Martin Kritzl
 * @version 20141217
 *
 */
public class TestEntenSimulator {

	private EntenSimulator sim;

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	/**
	 * Initialisiert den OutputStream
	 */
	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	/**
	 * Fuehrt die gesamte Entensimulation aus und ueberprueft die Ausgabe in der Konsole.
	 */
	@Test
	public void testSimulation() {
		AbstrakteEntenFabrik entenFabrik = new ZaehlendeEntenFabrik();
		this.sim = new EntenSimulator();
		this.sim.simulieren(entenFabrik);
		assertEquals(
				"\nEntensimulator: mit Observer\r\n"
				+ "Quak\r\n"
				+ "Quakologe: Moorente hat gerade gequakt.\r\n"
				+ "Kwaak\r\n"
				+ "Quakologe: Lockpfeife hat gerade gequakt.\r\n"
				+ "Quietsch\r\n"
				+ "Quakologe: Gummiente hat gerade gequakt.\r\n"
				+ "Schnatter\r\n"
				+ "Quakologe: sich als Ente ausgebende Gans hat gerade gequakt.\r\n"
				+ "Quak\r\n"
				+ "Quakologe: Stockente hat gerade gequakt.\r\n"
				+ "Quak\r\n"
				+ "Quakologe: Stockente hat gerade gequakt.\r\n\n"
				+ "Die Enten haben 5-mal gequakt.\r\n",
				outContent.toString());

	}
}
