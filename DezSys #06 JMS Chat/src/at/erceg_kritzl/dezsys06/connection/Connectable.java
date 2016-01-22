package at.erceg_kritzl.dezsys06.connection;


import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Connection;
import javax.jms.Destination;

/**Legt das Verhalten fuer eine Verbindung mit dem JMS fest
 * 
 * @author Martin Kritzl
 *
 */
public interface Connectable {

	/**Gibt die Session der Verbindung zurueck
	 * 
	 * @return session
	 */
	public abstract Session getSession();

	/**Gibt die Connection der Verbindung zurueck
	 * 
	 * @return connection
	 */
	public abstract Connection getConnection();
	 
	/**Gibt die Destination der Verbindung zurueck
	 * 
	 * @return destination
	 */
	public abstract Destination getDestination();

	/**Erstellt eine neue Verbindung, die Topic verwendet. Dies bedeutet, dass es sich um einen Chatraum handelt,
	 * 
	 * @param chatroom
	 */
	public abstract void setTopic(String chatroom);
	
	/**Erstellt eine neue Verbindung die Queue verwendet. Dies bedeutet, dass es sich um einen privaten Chat handelt. 
	 * In unserem Programm eine E-Mail.
	 * 
	 * @param destinationIP
	 */
	public abstract void setQueue(String destUser);
	
	/**Gibt den Username zurueck
	 * 
	 * @return username
	 */
	public String getUsername();
	
	/**Wird benoetigt, um im Nachhinein den Username und URL zu setzen
	 * 
	 * @param username
	 * @param url
	 */
	public void init(String username, String url);
	
	/**
	 * Schliesst die Verbindung
	 */
	public void close() throws JMSException;

}
