package at.erceg_kritzl.s06;

/**
 * Stellt eine Moorente dar
 * 
 * @author http://examples.oreilly.de/german_examples/hfdesignpatger/
 * @author Stefan Erceg(Kommentare)
 *
 */
public class MoorEnte implements Quakfaehig {
	SenderRing senderRing;

	/**
	 * Konstruktor der Klasse. SenderRing wird initialisiert.
	 */
	public MoorEnte() {
		senderRing = new SenderRing(this);
	}

	/**
	 * Quaken wird bei der Moorente durchgefuehrt. Die Beobachtenden werden, falls eine Moorente quakt, 
	 * benachrichtigt.
	 */
	public void quaken() {
		System.out.println("Quak");
		benachrichtigeBeobachtende();
	}

	/**
	 * Beobachter koennen sich registrieren, um bei einem Quaken der Moorente benachrichtigt zu werden.
	 * @param beobachter Beobachter, der sich registrieren moechte
	 */
	public void registriereBeobachter(Beobachter beobachter) {
		senderRing.registriereBeobachter(beobachter);
	}

	/**
	 * Beobachter werden beim Quaken der Moorente benachrichtigt.
	 */
	public void benachrichtigeBeobachtende() {
		senderRing.benachrichtigeBeobachtende();
	}

	/**
	 * Liefert als String den Text fuer die Moorente zurueck.
	 * @return String, der den Typ der Ente (hier: Moorente) darstellt
	 */
	public String toString() {
		return "Moorente";
	}
}
