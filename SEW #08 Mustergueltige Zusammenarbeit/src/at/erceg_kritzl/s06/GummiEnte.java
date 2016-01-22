package at.erceg_kritzl.s06;

/**
 * Stellt eine Gummiente dar
 * 
 * @author http://examples.oreilly.de/german_examples/hfdesignpatger/
 * @author Stefan Erceg(Kommentare)
 *
 */
public class GummiEnte implements Quakfaehig {
	SenderRing senderRing;

	/**
	 * Konstruktor der Klasse. SenderRing wird initialisiert.
	 */
	public GummiEnte() {
		senderRing = new SenderRing(this);
	}
 
	/**
	 * Quietschen wird bei der Gummiente durchgefuehrt. Die Beobachtenden werden, falls eine Gummiente 
	 * quietscht, benachrichtigt.
	 */
	public void quaken() {
		System.out.println("Quietsch");
		benachrichtigeBeobachtende();
	}

	/**
	 * Beobachter koennen sich registrieren, um bei einem Quietschen der Gummiente benachrichtigt zu werden.
	 * @param beobachter Beobachter, der sich registrieren moechte
	 */
	public void registriereBeobachter(Beobachter beobachter) {
		senderRing.registriereBeobachter(beobachter);
	}

	/**
	 * Beobachter werden beim Quietschen der Gummiente benachrichtigt.
	 */
	public void benachrichtigeBeobachtende() {
		senderRing.benachrichtigeBeobachtende();
	}
 
	/**
	 * Liefert als String den Text fuer die Gummiente zurueck.
	 * @return String, der den Typ der Ente (hier: Gummiente) darstellt
	 */
	public String toString() {
		return "Gummiente";
	}
}
