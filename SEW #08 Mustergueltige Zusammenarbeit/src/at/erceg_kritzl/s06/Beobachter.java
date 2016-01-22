package at.erceg_kritzl.s06;

/**
* Stellt einen Beobachter dar
* 
* @author http://examples.oreilly.de/german_examples/hfdesignpatger/
* @author Martin Kritzl(Kommentare)
*
*/
public interface Beobachter {
	/**
	 * Teilt dem Beobachter mit, dass eine Ente gesichtet wurde
	 * 
	 * @param ente
	 */
	public void aktualisieren(QuakBeobachtungsSubjekt ente);
}
