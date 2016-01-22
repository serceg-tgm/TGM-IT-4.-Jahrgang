package at.erceg_kritzl.dezsys06.handler;

import at.erceg_kritzl.dezsys06.display.Control;

/**Stellt das Verhalten einer Klasse dar, die anhand einer Eingabe verschiedene Aufgaben taetigt
 * 
 * @author Martin Kritzl
 *
 */
public interface Handler {

	/**Interpretiert den String und taetigt etwaige Befehle
	 * 
	 * @param control
	 */
	public abstract void interpretLine(Control control);

}
