package at.erceg_kritzl.s06;

/**
 * Stellt eine Stockente dar
 * 
 * @author http://examples.oreilly.de/german_examples/hfdesignpatger/
 * @author Stefan Erceg(Kommentare)
 *
 */
public class StockEnte implements Quakfaehig {
	SenderRing senderRing;
 
	/**
	 * Konstruktor der Klasse. SenderRing wird initialisiert.
	 */
	public StockEnte() {
		senderRing = new SenderRing(this);
	}
 
	/**
	 * Quaken wird bei der Stockente durchgefuehrt. Die Beobachtenden werden, falls eine Moorente quakt, 
	 * benachrichtigt.
	 */
	public void quaken() {
		System.out.println("Quak");
		benachrichtigeBeobachtende();
	}

	/**
	 * Beobachter koennen sich registrieren, um bei einem Quaken der Stockente benachrichtigt zu werden.
	 * @param beobachter Beobachter, der sich registrieren moechte
	 */
	public void registriereBeobachter(Beobachter beobachter) {
		senderRing.registriereBeobachter(beobachter);
	}
 
	/**
	 * Beobachter werden beim Quaken der Stockente benachrichtigt.
	 */
	public void benachrichtigeBeobachtende() {
		senderRing.benachrichtigeBeobachtende();
	}
 
	/**
	 * Liefert als String den Text fuer die Stockente zurueck.
	 * @return String, der den Typ der Ente (hier: Stockente) darstellt
	 */
	public String toString() {
		return "Stockente";
	}
}
