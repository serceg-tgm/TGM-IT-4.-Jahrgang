package at.erceg_kritzl.s06;

import java.util.Iterator;
import java.util.ArrayList;

/**
 * Sammelt die Beobachtenden in einer Liste und benachrichtigt alle bei einer Sichtung einer Ente.
 * 
 * @author http://examples.oreilly.de/german_examples/hfdesignpatger/
 * @author Stefan Erceg(Kommentare)
 *
 */
public class SenderRing implements QuakBeobachtungsSubjekt {
	ArrayList beobachtende = new ArrayList();
	QuakBeobachtungsSubjekt ente;
 
	/**
	 * Konstruktor der Klasse. Ente wird initialisiert.
	 * @param ente Typ der Ente
	 */
	public SenderRing(QuakBeobachtungsSubjekt ente) {
		this.ente = ente;
	}
  
	/**
	 * Der Beobachter, der im Parameter uebergeben wird, wird zur Beobachter-Liste hinzugefuegt.
	 * @param beobachter Beobachter, der sich registrieren moechte
	 */
	public void registriereBeobachter(Beobachter beobachter) {
		beobachtende.add(beobachter);
	}
  
	/**
	 * Mittels eines Iterators werden alle Beobachtenden bei einem Quaken benachrichtigt.
	 */
	public void benachrichtigeBeobachtende() {
		Iterator iterator = beobachtende.iterator();
		while (iterator.hasNext()) {
			Beobachter beobachter = (Beobachter)iterator.next();
			beobachter.aktualisieren(ente);
		}
	}
 
	/**
	 * Eine getter-Methode, mit welcher alle Beobachtenden, die sich in der Liste befinden, zurueckgegeben
	 * werden.
	 * @return alle Beobachtenden, die sich in der Liste befinden
	 */
	public Iterator getBeobachtende() {
		return beobachtende.iterator();
	}
}
