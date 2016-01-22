package at.stefanerceg.s02.konto;

/**
 * 
 * Die Klasse "Konto" zieht bei Überweisungen und Abhebungen die jeweilige Summe vom Saldo ab. <br>
 * 
 * @author Stefan Erceg
 * @since 10.10.2014
 * @version 1.0
 *
 */

public class Konto {
	
	/* Attribute für die Kontonummer und den Saldo werden erstellt */
	
	private long nummer;
	protected double saldo;
	
	/**
	 * der Konstruktor weist dem Attribut "nummer" den im Parameter eingegeben Wert zu und setzt den Saldo zu Beginn 
	 * auf 0
	 * @param nummer Kontonummer wird angegeben
	 */
	
    public Konto(long nummer) {
        this.nummer = nummer;
        this.saldo = 0;
    }
    
    /**
     * getter-Methode, welche den aktuellen Saldo zurückgibt
     * @return Saldobetrag
     */
    
    public double getSaldo() {
        return this.saldo;
    }
    
    /**
     * Überweisen eines bestimmten Geldbetrags
     * @param betrag Geldbetrag wird angegeben
     * @throws IllegalArgumentException wird geworfen, wenn der Geldbetrag nicht größer 0 ist
     */
    
    public void einzahlen(double betrag) throws IllegalArgumentException {
    	/* Gelbetrag wird zum aktuellen Saldo hinzugefügt, wenn der Betrag größer 0 ist */
    	if (betrag > 0) {
    		this.saldo += betrag;
    	} else {
    		throw new IllegalArgumentException("Betrag muss größer 0 sein!");
    	}
    }
    
    /**
     * Abheben eines bestimmten Geldbetrags
     * @param betrag Geldbetrag wird angegeben
     * @throws IllegalArgumentException wird geworfen, wenn der Geldbetrag nicht größer 0 ist
     * @throws NotEnoughMoneyException wird geworfen, wenn nicht genug Geld vorhanden ist (für die Klassen Sparkonto und Girokonto notwendig)
     */
    
    public void abheben(double betrag) throws IllegalArgumentException, NotEnoughMoneyException {
    	/* Geldbetrag wird vom aktuellen Saldo abgezogen, wenn der Betrag größer 0 ist */
    	if (betrag > 0) {
    		this.saldo -= betrag;
    	} else {
    		throw new IllegalArgumentException("Betrag muss größer 0 sein!");
    	}
    }
    
}
