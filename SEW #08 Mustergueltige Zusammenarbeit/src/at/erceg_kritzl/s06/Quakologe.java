package at.erceg_kritzl.s06;

public class Quakologe implements Beobachter {
 
	/**
	 * Eine Meldung vom Quakologen wird ausgegegeben, falls eine bestimmte Ente quakt.
	 * @param ente Ente, die gerade gequakt hat
	 */
	public void aktualisieren(QuakBeobachtungsSubjekt ente) {
		System.out.println("Quakologe: " + ente + " hat gerade gequakt.");
	}
 
	/**
	 * Liefert als String den Text fuer den Quakologen zurueck.
	 */
	public String toString() {
		return "Quakologe";
	}
}
