package at.erceg_kritzl.s06;

/**
 * Ein Interface, welches die Methoden fuer die Registrierung und Benachrichtigung des Beobachters zur
 * Verfuegung stellt.
 * 
 * @author http://examples.oreilly.de/german_examples/hfdesignpatger/
 * @author Stefan Erceg(Kommentare)
 *
 */
public interface QuakBeobachtungsSubjekt {
	
	/**
	 * Beobachter koennen sich registrieren, um bei einem Quaken einer bestimmten Ente benachrichtigt zu
	 * werden.
	 * @param beobachter Beobachter, die sich registrieren moechten
	 */
	public void registriereBeobachter(Beobachter beobachter);
	
	/**
	 * Beobachter werden benachrichtigt, falls ein Quaken bei einer bestimmten Ente durchgefuehrt wird.
	 */
	public void benachrichtigeBeobachtende();
}
