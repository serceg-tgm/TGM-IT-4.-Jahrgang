package at.erceg_kritzl.dezsys06.connection;

/**Stellt das Verhalten einer sendenden Klasse dar
 * 
 * @author Martin Kritzl
 *
 */
public interface Sendable {
	/**Gibt die momentan eingestellte Connection zurueck
	 * 
	 * @return connection
	 */
	public Connectable getConnection();
	
	/**Schickt eine Nachricht an die eingestellte Connection
	 * 
	 * @param message
	 */
	public void sendMessage(MessageBehavior message);
}
