package at.erceg_kritzl.s06;

/**
 * Mittels des GansAdapters wird das Problem des Quakens bei der Gans geregelt. Da diese ja nicht quaken
 * kann, wird hier statt der Methode "quaken" die Methode "schnattern" durchgefuehrt. Diese wird aber
 * in der Methode "quaken" verpackt und laesst sie somit wie die Methode bei den anderen Ententypen
 * auussehen.
 * 
 * @author http://examples.oreilly.de/german_examples/hfdesignpatger/
 * @author Stefan Erceg(Kommentare)
 *
 */
public class GansAdapter implements Quakfaehig {
	Gans gans;
	SenderRing senderRing;

	/**
	 * Konstruktor der Klasse. Gans und SenderRing werden initialisiert.
	 * @param gans Gans
	 */
	public GansAdapter(Gans gans) {
		this.gans = gans;
		senderRing = new SenderRing(this);
	}
 
	/**
	 * Statt dem Quaken wird bei der Gans ein Schnattern durchgefuehrt. Die Beobachtenden werden, falls
	 * eine Gans schnattert, benachrichtigt.
	 */
	public void quaken() {
		gans.schnattern();
		benachrichtigeBeobachtende();
	}

	/**
	 * Beobachter koennen sich registrieren, um bei einem Schnattern der Gans benachrichtigt zu werden.
	 * @param beobachter Beobachter, der sich registrieren moechte
	 */
	public void registriereBeobachter(Beobachter beobachter) {
		senderRing.registriereBeobachter(beobachter);
	}

	/**
	 * Beobachter werden beim Schnattern der Gans benachrichtigt.
	 */
	public void benachrichtigeBeobachtende() {
		senderRing.benachrichtigeBeobachtende();
	}

	/**
	 * Liefert als String den Text fuer die Gans zurueck.
	 * @return String, der den Typ der Ente (hier: sich als Ente ausgebende Gans) darstellt
	 */
	public String toString() {
		return "sich als Ente ausgebende Gans";
	}
}
