package at.erceg_kritzl.s06;
import java.util.ArrayList;
import java.util.List;

/**
 * Sammelt die Enten in einer Liste.
 * 
 * @author http://examples.oreilly.de/german_examples/hfdesignpatger/
 * @author Stefan Erceg(Kommentare)
 *
 */
public class Schar implements Quakfaehig {

	private List<Quakfaehig> enten;
	
	/**
	 * Konstruktor der Klasse. Eine ArrayList fuer die Enten wird initialisiert.
	 */
	public Schar() {
		this.enten = new ArrayList<Quakfaehig>();
	}

	/**
	 * Eine bestimmte Ente, die im Parameter uebergeben wird, wird zur ArrayList hinzugefuegt.
	 * @param ente Ente, die zur ArrayList hinzugefuegt wird
	 */
	public void hinzufuegen(Quakfaehig ente) {
		this.enten.add(ente);
	}


	/**
	 * In String-Format wird der Typ der Ente zurueckgegeben.
	 * @return String, der den Typ der Ente darstellt
	 */
	public String toString() {
		return this.enten.toString();
	}


	/**
	 * @see Quakfaehig#quaken()
	 */
	public void quaken() {
		for(Quakfaehig ente: enten) {
			ente.quaken();
		}
	}

	@Override
	public void registriereBeobachter(Beobachter beobachter) {
		for(Quakfaehig ente: enten) {
			ente.registriereBeobachter(beobachter);
		}
	}

	@Override
	public void benachrichtigeBeobachtende() {
		for(Quakfaehig ente: enten) {
			ente.benachrichtigeBeobachtende();
		}
	}

}
