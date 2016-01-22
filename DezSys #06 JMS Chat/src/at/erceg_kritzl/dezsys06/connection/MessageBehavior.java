package at.erceg_kritzl.dezsys06.connection;

/**Beschreibt das Verhalten einer Nachricht
 * 
 * @author Martin Kritzl
 *
 */
public interface MessageBehavior {

	/**Gibt den Inhalt der Nachricht zurueck
	 * 
	 * @return content
	 */
	public abstract String getContent();

}
