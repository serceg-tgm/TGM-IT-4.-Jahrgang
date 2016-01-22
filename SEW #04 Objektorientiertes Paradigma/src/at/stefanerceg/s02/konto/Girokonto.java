package at.stefanerceg.s02.konto;

/**
 * 
 * Die Klasse "Girokonto" erbt von der Klasse "Konto". Das Guthaben (Saldo) des Girokontos darf im �berziehungsrahmen 
 * negativ werden. Bei �berweisungen wird die jeweilige Summe zus�tzlich am Zielkonto eingezahlt. <br>
 * 
 * @author Stefan Erceg
 * @since 10.10.2014
 * @version 1.0
 *
 */

public class Girokonto extends Konto {
	
	/* Attribut f�r den �berziehungsrahmen wird erstellt */
	
    private int ueberziehungsrahmen;
    
    /**
     * der Konstruktor weist den Attributen "nummer" und "ueberziehungsrahmen" die im Parameter eingegebenen Werte f�r 
     * die Kontonummer und den �berziehungsrahmen zu
     * @param nummer Kontonummer wird angegeben
     * @param uezr �berziehungsrahmen wird angegeben
     * @throws IllegalArgumentException wird geworfen, wenn der �berziehungsrahmen nicht als positiver Wert angegeben worden ist
     */
    
    public Girokonto(long nummer, int uezr) throws IllegalArgumentException {
        super(nummer);
        if(uezr >= 0) {
        	this.ueberziehungsrahmen = uezr;
        } else {
        	throw new IllegalArgumentException("Der �berziehungsrahmen muss als positiver Wert angegeben werden!");
        }
    }
    
    /**
	 * @see at.stefanerceg.s02.konto.Konto#abheben(double)
	 */
    /* bis zum �berziehungsrahmen darf vom Konto abgehoben werden */
    
    public void abheben(double betrag) throws IllegalArgumentException, NotEnoughMoneyException {
    	/* wenn der aktuelle Saldo mit dem �berziehungsrahmen >= dem Geldbetrag ist, kann abgehoben werden, ansonsten 
    	 * wird eine Exception geworfen */
    	if(this.getSaldo() + this.ueberziehungsrahmen >= betrag) {
            super.abheben(betrag);      
        } else {
        	throw new NotEnoughMoneyException();
        }
    }
    
    /**
     * Abheben eines bestimmten Geldbetrags von einem Konto und diesen auf ein anderes Konto �berweisen
     * @param betrag Geldbetrag wird angegeben
     * @param zielkonto das Konto, auf welches der Betrag �berwiesen wird, wird angegeben
     * @throws NotEnoughMoneyException wird geworfen, wenn nicht genug Geld vorhanden is
     */
    
    public void ueberweisen(double betrag, Girokonto zielkonto) throws IllegalArgumentException, NotEnoughMoneyException {
    	/* wenn der aktuelle Saldo mit dem �berziehungsrahmen >= dem Geldbetrag ist, kann abgehoben werden, ansonsten 
    	 * wird eine Exception geworfen */
    	if(this.getSaldo() + this.ueberziehungsrahmen >= betrag) {
    		/* der Betrag wird vom Konto abgehoben und am Zielkonto eingezahlt */
    		this.abheben(betrag);
    		zielkonto.einzahlen(betrag);
    	} else {
    		throw new NotEnoughMoneyException();
    	}
    }
    
}