package at.erceg_kritzl.s06;

/**
 * Stellt eine Gans dar
 * 
 * @author http://examples.oreilly.de/german_examples/hfdesignpatger/
 * @author Stefan Erceg(Kommentare)
 *
 */
public class Gans {

	/**
	 * Methode zum Schnattern der Gans.
	 */
	public void schnattern() {
		System.out.println("Schnatter");
	}

	/**
	 * In String-Format wird der Typ der Ente (hier: Gans) zurueckgegeben.
	 * @return String, der den Typ der Ente (hier: Gans) darstellt
	 */
	public String toString() {
		return "Gans";
	}
}
