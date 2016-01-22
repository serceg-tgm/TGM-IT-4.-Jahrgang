package at.erceg_kritzl.s06.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import at.erceg_kritzl.s06.AbstrakteEntenFabrik;
import at.erceg_kritzl.s06.GummiEnte;
import at.erceg_kritzl.s06.LockPfeife;
import at.erceg_kritzl.s06.MoorEnte;
import at.erceg_kritzl.s06.StockEnte;
import at.erceg_kritzl.s06.ZaehlendeEntenFabrik;

/**
 * Die Methoden der Klasse 'ZaehlendeEntenFabrik' werden getestet.
 * 
 * @author Martin Kritzl
 * @version 20141217
 *
 */
public class TestZaehlendeEntenFabrik {
	
	private AbstrakteEntenFabrik fabrik;
	
	@Before
	public void prepare() {
		this.fabrik = new ZaehlendeEntenFabrik();
	}

	/**
	 * Erzeugt eine "MoorEnte" ueber die ZaehlendeEntenFabrik und ueberprueft, ob
	 * eine explizit erstellte "MoorEnte" den gleichen Wert bei "toString()" hat.
	 */
	@Test
	public void testErzeugeMoorEnte() {
		assertEquals(new MoorEnte().toString(), fabrik.erzeugeMoorEnte().toString());
	}
	
	/**
	 * Erzeugt eine "StockEnte" ueber die ZaehlendeEntenFabrik und ueberprueft, ob
	 * eine explizit erstellte "StockEnte" den gleichen Wert bei "toString()" hat.
	 */
	@Test
	public void testErzeugeStockEnte() {
		assertEquals(new StockEnte().toString(), fabrik.erzeugeStockEnte().toString());
	}
	
	/**
	 * Erzeugt eine "LockPfeife" ueber die ZaehlendeEntenFabrik und ueberprueft, ob
	 * eine explizit erstellte "LockPfeife" den gleichen Wert bei "toString()" hat.
	 */
	@Test
	public void testErzeugeLockPfeife() {
		assertEquals(new LockPfeife().toString(), fabrik.erzeugeLockPfeife().toString());
	}
	
	/**
	 * Erzeugt eine "GummiEnte" ueber die ZaehlendeEntenFabrik und ueberprueft, ob
	 * eine explizit erstellte "GummiEnte" den gleichen Wert bei "toString()" hat.
	 */
	@Test
	public void testErzeugeGummiEnte() {
		assertEquals(new GummiEnte().toString(), fabrik.erzeugeGummiEnte().toString());
	}

}
