package at.stefanerceg.s02.konto;

/**
* 
* Die Klasse "Sparkonto" erbt von der Klasse "Konto". Das Guthaben des Sparkontos darf 0,- Euro nicht unterschreiten. 
* Die Zinsberechnung des Sparkontos soll erheblich vereinfacht werden und die Zinsen für ein ganzes Jahr anhand des 
* aktuellen Saldos berechnet werden. <br>
* 
* @author Stefan Erceg
* @since 10.10.2014
* @version 1.0
*
*/

public class Sparkonto extends Konto {
   
	/* Attribut für den Zinssatz wird erstellt */
	
	private float zinssatz;
   
	/**
	 * der Konstruktor weist den Attributen "nummer" und "zinssatz" die im Parameter eingegebenen Werte für die 
	 * Kontonummer und den Zinssatz zu
	 * @param nummer Kontonummer wird angegeben
	 * @param zinssatz Zinssatz wird angegeben
	 * @throws IllegalArgumentException wird geworfen, wenn der Zinssatz nicht größer 0 ist
	 */
	
	public Sparkonto(long nummer, float zinssatz) throws IllegalArgumentException {
       super(nummer);
       if(zinssatz > 0) {
    	   this.zinssatz = zinssatz;
       } else {
    	   throw new IllegalArgumentException("Zinssatz muss größer 0 sein!");
       }
   }
   
	/**
	 * @see at.stefanerceg.s02.konto.Konto#abheben(double)
	 */
	/* vom Konto werden solange Beträge abgehoben, bis das Konto nicht überzogen wurde */
	
   public void abheben(double betrag) throws IllegalArgumentException, NotEnoughMoneyException {
   	/* wenn der aktuelle Saldo >= dem Geldbetrag ist, kann abgehoben werden, ansonsten wird eine Exception geworfen */
       if (this.getSaldo() >= betrag) {
           super.abheben(betrag);
       } else {
    	   throw new NotEnoughMoneyException();
       }
   }
   
   /**
    * die Zinsen für ein ganzes Jahr werden anhand des aktuellen Saldos berechnet
    */
   
   public void addiereJahreszinsen() {
   	/* man rechnet den aktuellen Saldo mal einem ganzen Jahr (12 Monate) durch 100 und mal den auf double gecasteten 
   	 * Zinssatz */
   	super.einzahlen(this.getSaldo() * 12 / 100 * (double) zinssatz);
   }
   
}