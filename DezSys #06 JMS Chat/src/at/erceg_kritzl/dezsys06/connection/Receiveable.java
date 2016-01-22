package at.erceg_kritzl.dezsys06.connection;

/**Stellt das Verhalten einer empfangenden Klasse dar
 * 
 * @author Martin Kritzl
 *
 */
public interface Receiveable extends Runnable {
	/**Gibt die momentan eingestellte Connection zurueck
	 * 
	 * @return connection
	 */
	public Connectable getConnection(); 
	
	/**Legt fest, ob von der Connection Nachrichten erhalten werden sollen
	 * 
	 * @param receive
	 */
	public void setReceive(boolean receive);
}
