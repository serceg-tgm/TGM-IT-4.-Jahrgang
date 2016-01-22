package at.erceg_kritzl.s06.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import at.erceg_kritzl.s06.LockPfeife;
import at.erceg_kritzl.s06.Quakologe;

/**
 * Die Methoden der Klasse 'LockPfeife' werden getestet.
 * 
 * @author Stefan Erceg
 * @version 20141217
 *
 */
public class TestLockPfeife {

	private LockPfeife lockpfeife;
	private Quakologe quakologe;
	private ByteArrayOutputStream output;

	/**
	 * Dient zum Initialisieren der jeweiligen Attribute (LockPfeife, Quakologe und 
	 * ByteArrayOutputStream).
	 */
	@Before
	public void initialize() {
		lockpfeife = new LockPfeife();
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
		lockpfeife.quaken();
		assertEquals("Kwaak\r\n", output.toString());
	}

	/**
	 * Fuegt den vorher initialisierten Quakologen mit der registriereBeobachter-Methode zu den
	 * Beobachtern hinzu.
	 */
	@Test
	public void testRegistriereBeobachter() {
		lockpfeife.registriereBeobachter(quakologe);
	}

	/**
	 * Prueft, ob die toString-Methode den entsprechenden Text fuer die Lockpfeife zurueckgibt.
	 */
	@Test
	public void testToString() {
		assertEquals(lockpfeife.toString(), "Lockpfeife");	
	}

}