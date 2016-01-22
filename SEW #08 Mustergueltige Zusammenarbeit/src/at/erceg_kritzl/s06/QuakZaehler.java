package at.erceg_kritzl.s06;

/**
 * Zaehlt die Anzahl der Quaks zusammen.
 * 
 * @author http://examples.oreilly.de/german_examples/hfdesignpatger/
 * @author Stefan Erceg(Kommentare)
 *
 */
public class QuakZaehler implements Quakfaehig {
	Quakfaehig ente;
	static int anzahlDerQuaks;
  
	/**
	 * Konstruktor der Klasse.
	 * @param ente Typ der Ente
	 */
	public QuakZaehler(Quakfaehig ente) {
		this.ente = ente;
	}
  
	/**
	 * Wenn eine Ente quakt, erhoeht sich die Anzahl der Quaks.
	 */
	public void quaken() {
		ente.quaken();
		anzahlDerQuaks++;
	}
 
	/**
	 * Eine getter-Methode, mit welcher die Anzahl der Quaks zurueckgegeben wird.
	 * @return Anzahl der Quaks
	 */
	public static int getQuaks() {
		return anzahlDerQuaks;
	}
 
	/**
	 * Beobachter koennen sich registrieren, um bei einem Quaken einer bestimmten Ente benachrichtigt zu
	 * werden.
	 * @param beobachter Beobachter, die sich registrieren moechten
	 */
	public void registriereBeobachter(Beobachter beobachter) {
		ente.registriereBeobachter(beobachter);
	}
 
	/**
	 * Beobachter werden benachrichtigt, falls ein Quaken bei einer bestimmten Ente durchgefuehrt wird.
	 */
	public void benachrichtigeBeobachtende() {
		ente.benachrichtigeBeobachtende();
	}
   
	/**
	 * Liefert als String den Text fuer den bestimmten Typ der Ente zurueck.
	 */
	public String toString() {
		return ente.toString();
	}
}
