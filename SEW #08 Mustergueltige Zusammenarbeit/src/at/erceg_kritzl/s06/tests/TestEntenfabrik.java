package at.erceg_kritzl.s06.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import at.erceg_kritzl.s06.AbstrakteEntenFabrik;
import at.erceg_kritzl.s06.Entenfabrik;
import at.erceg_kritzl.s06.GummiEnte;
import at.erceg_kritzl.s06.LockPfeife;
import at.erceg_kritzl.s06.MoorEnte;
import at.erceg_kritzl.s06.StockEnte;

/**
 * Die Methoden der Klasse 'Entenfabrik' werden getestet.
 * 
 * @author Stefan Erceg
 * @version 20141217
 *
 */
public class TestEntenfabrik {

	private AbstrakteEntenFabrik entenFabrik;
	
	/**
	 * Dient zum Initialisieren des jeweiligen Attributs (AbstrakteEntenFabrik).
	 */
	@Before
	public void initialize() {
		entenFabrik = new Entenfabrik();
	}
	
	/**
	 * Erzeugt eine "StockEnte" ueber die EntenFabrik und ueberprueft, ob eine explizit erstellte 
	 * "StockEnte" den gleichen Wert bei "toString()" hat.
	 */
	@Test
	public void testErzeugeStockEnte() {
		assertEquals(new StockEnte().toString(), entenFabrik.erzeugeStockEnte().toString());
	}

	/**
	 * Erzeugt eine "MoorEnte" ueber die EntenFabrik und ueberprueft, ob eine explizit erstellte 
	 * "MoorEnte" den gleichen Wert bei "toString()" hat.
	 */
	@Test
	public void testErzeugeMoorEnte() {
		assertEquals(new MoorEnte().toString(), entenFabrik.erzeugeMoorEnte().toString());
	}

	/**
	 * Erzeugt eine "LockPfeife" ueber die EntenFabrik und ueberprueft, ob eine explizit erstellte 
	 * "LockPfeife" den gleichen Wert bei "toString()" hat.
	 */
	@Test
	public void testErzeugeLockPfeife() {
		assertEquals(new LockPfeife().toString(), entenFabrik.erzeugeLockPfeife().toString());
	}
	
	/**
	 * Erzeugt eine "GummiEnte" ueber die EntenFabrik und ueberprueft, ob eine explizit erstellte 
	 * "GummiEnte" den gleichen Wert bei "toString()" hat.
	 */
	@Test
	public void testErzeugeGummiEnte() {
		assertEquals(new GummiEnte().toString(), entenFabrik.erzeugeGummiEnte().toString());
	}

}
