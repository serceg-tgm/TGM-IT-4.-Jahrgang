package at.stefanerceg.s02.konto;

/**
 * 
 * Es wird eine eigene "NotEnoughMoneyException" erstellt, welche von der Klasse "Exception" erbt. <br>
 * 
 * @author Stefan Erceg
 * @since 10.10.2014
 * @version 1.0
 *
 */

public class NotEnoughMoneyException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * die Methode "getMessage()" wird überschrieben
	 * @return Text "nicht genug Geld vorhanden"
	 */
	
	@Override
	public String getMessage() {
		return "nicht genug Geld vorhanden";
	}
	
}
