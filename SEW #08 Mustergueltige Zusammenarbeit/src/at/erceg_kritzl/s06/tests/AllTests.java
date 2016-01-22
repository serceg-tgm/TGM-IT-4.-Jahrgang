package at.erceg_kritzl.s06.tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestGans.class, TestGansAdapter.class, TestGummiEnte.class,
		TestLockPfeife.class, TestMoorEnte.class, TestQuakologe.class,
		TestQuakzaehler.class, TestSchar.class, TestSenderRing.class,
		TestStockEnte.class, TestZaehlendeEntenFabrik.class, TestEntenfabrik.class,
		TestEntenSimulator.class })

/**
 * Fuehrt alle Testklassen des Programms aus.
 * 
 * @author Stefan Erceg
 * @author Martin Kritzl
 * @version 20141217
 *
 */
public class AllTests {
	/**
	 * In der Main-Methode werden alle Testklassen, die in der Suite definiert wurden, ausgefuehrt.
	 * @param args
	 */
	public static void main(String[] args) {
		JUnitCore.main("at.erceg_kritzl.s06.tests.AllTests");
	}
}
