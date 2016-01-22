package at.erceg_kritzl.s06;

/**
 * Stellt eine Lockpfeife dar
 * 
 * @author http://examples.oreilly.de/german_examples/hfdesignpatger/
 * @author Stefan Erceg(Kommentare)
 *
 */
public class LockPfeife implements Quakfaehig {
	SenderRing senderRing;

	/**
	 * Konstruktor der Klasse. SenderRing wird initialisiert.
	 */
	public LockPfeife() {
		senderRing = new SenderRing(this);
	}
 
	/**
	 * Enten werden mittels der Lockpfeife gelockt. Die Beobachtenden werden, falls eine Lockpfeife verwendet
	 * wurde, benachrichtigt.
	 */
	public void quaken() {
		System.out.println("Kwaak");
		benachrichtigeBeobachtende();
	}
 
	/**
	 * Beobachter koennen sich registrieren, um beim Verwenden einer Lockpfeife benachrichtigt zu werden.
	 * @param beobachter Beobachter, der sich registrieren moechte
	 */
	public void registriereBeobachter(Beobachter beobachter) {
		senderRing.registriereBeobachter(beobachter);
	}

	/**
	 * Beobachter werden beim Verwenden einer Lockpfeife benachrichtigt.
	 */
	public void benachrichtigeBeobachtende() {
		senderRing.benachrichtigeBeobachtende();
	}
	/**
	 * Liefert als String den Text fuer die Lockpfeife zurueck.
	 * @return String, der den Typ der Ente (hier: Lockpfeife) darstellt
	 */ 
	public String toString() {
		return "Lockpfeife";
	}
}
