package at.stefanerceg.s02.konto;

/**
* 
* Die Klasse "Losungswortsparkonto" erbt von der Klasse "Sparkonto". Das Guthaben des Losungswortsparkontos darf 
* 0,- Euro nicht unterschreiten und 15 000,- Euro nicht überschreiten. <br>
* 
* @author Stefan Erceg
* @since 10.10.2014
* @version 1.0
*
*/


public class Losungswortsparkonto extends Sparkonto {

	/* Attribut für das Losungswort wird erstellt */
	
	private String losungswort;

	/**
	 * der Konstruktor weist den Attributen "nummer", "zinssatz" und "losungswort" die im Parameter eingegebenen Werte 
	 * für die Kontonummer, den Zinssatz und das Losungswort zu
	 * @param nummer Kontonummer wird angegeben
	 * @param zinssatz Zinssatz wird angegeben
	 * @param losungswort Losungswort wird angegeben
	 * @throws IllegalArgumentException wird geworfen, wenn der Zinssatz nicht größer 0 ist
	 */
	
    public Losungswortsparkonto(long nummer, float zinssatz, String losungswort) throws IllegalArgumentException {
    	super(nummer,zinssatz);
    	this.losungswort = losungswort;
    }
    
    /**
     * Einzahlung eines bestimmten Geldbetrags. Der Saldo darf den Wert 15 000 nicht überschreiten.
     * @param betrag Geldbetrag wird angegeben
     * @throws IllegalArgumentException wird geworfen, wenn der Geldbetrag nicht größer 0 ist
     */
    
    @Override
    public void einzahlen(double betrag) throws IllegalArgumentException {
    	/* Gelbetrag wird zum aktuellen Saldo hinzugefügt, wenn der Betrag größer 0 und kleiner 15 000 ist */
    	if (betrag > 0 && betrag <= 15000) {
        	/* wenn der aktuelle Saldo mit dem einzuzahlendem Geldbetrag <= 15 000 ist, kann eingezahlt werden, 
        	 * ansonsten wird eine Exception geworfen */
    		if (this.getSaldo() + betrag <= 15000) {
    			this.saldo += betrag;
    		} else {
    			throw new IllegalArgumentException("Der Saldobetrag hat den Wert 15 000 überschritten!");
    		}
    	} else {
    		throw new IllegalArgumentException("Betrag muss größer 0 und kleiner 15 000 sein!");
    	}
    }
}
