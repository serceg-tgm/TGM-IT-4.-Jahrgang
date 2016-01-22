package at.stefanerceg.s03.testen;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
* 
* Diese Klasse dient zum Ausführen aller erstellten Testklassen.
* 
* @author Stefan Erceg
* @since 06.11.2014
* @version 20141112
*
*/

@RunWith(Suite.class)
@SuiteClasses({
	TestCalculator.class, 
	TestOperationtypes.class, 
})

public class TestRunner {

	/**
	 * Alle Tests werden mittels JUnitCore ausgeführt.
	 * @param args Kommandozeilenargumente
	 */
	
	public static void main(String[] args) {
		
		JUnitCore.main("at.stefanerceg.s03.testen.TestRunner");
		
	}
	
}
