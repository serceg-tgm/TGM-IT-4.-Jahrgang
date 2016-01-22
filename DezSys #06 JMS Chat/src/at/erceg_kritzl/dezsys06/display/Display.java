package at.erceg_kritzl.dezsys06.display;

import at.erceg_kritzl.dezsys06.connection.MessageBehavior;

/**Stellt das Verhalten einer Klasse dar, die auf dem Bildschirm Texte dartsellt
 * 
 * @author Martin Kritzl
 *
 */
public interface Display extends Runnable{
	
	
	/**Gibt die jetzige Message zurueck
	 * 
	 * @return Message
	 */
	public MessageBehavior getMessage();
	
	/**Setzt die Message
	 * 
	 * @param message
	 */
	public void setMessage(MessageBehavior message);

}
