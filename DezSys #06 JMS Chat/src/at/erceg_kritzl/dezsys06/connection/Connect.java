package at.erceg_kritzl.dezsys06.connection;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Connection;
import javax.jms.Destination;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Stellt die Connection des Hostsystems und des JMS-Systems dar
 * 
 * @author Martin Kritzl
 *
 */
public class Connect implements Connectable {
	
	public static final Logger logger = LogManager.getLogger("CLI");

	private static String password = ActiveMQConnection.DEFAULT_PASSWORD;
	private ConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;
	private Destination destination;
	private String username;

	@Override
	public void init(String username, String url) {
		//Zuvor erstellte Verbindungen sollen getrennt werden
		try {
			this.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		this.connectionFactory = new ActiveMQConnectionFactory(username,
				password, "failover://tcp://" + url);
		this.username = username;
	}

	public Connection getConnection() {
		return connection;
	}

	public Session getSession() {
		return session;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setTopic(String chatroom) {
		try {
			// Allgemeine Verbindung wird erstellt
			buildConnection(chatroom);
			// Topic wird als Ziel gewaehlt
			this.destination = session.createTopic(chatroom);
		} catch (Exception e) {

			System.out.println("[MessageConsumer] Caught: " + e);
			e.printStackTrace();

		}

	}

	public void setQueue(String destinationIP) {
		try {
			// Allgemeine Verbindung wird erstellt
			buildConnection(destinationIP);
			// Queue wird als Ziel gewaehlt
			this.destination = session.createQueue(destinationIP);
		} catch (Exception e) {

			System.out.println("[MessageConsumer] Caught: " + e);
			e.printStackTrace();

		}
	}

	/**
	 * Erstellt die Connection und die Session anhand der Parameter
	 * 
	 * @param username
	 * @param url
	 * @param chatroom
	 * @throws JMSException
	 */
	private void buildConnection(String dest) {
		try {
			// Die alten Verbindungen werden geschlossen
			close();
			// Connection wird erstellt
			this.connection = this.connectionFactory.createConnection();
			
			this.connection.setClientID(getUsername());
			//this.connection.sendTie
			this.connection.start();

			// Session wird erstellt
			this.session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public String getUsername() {
		return this.username;
	}

	@Override
	public void close() throws JMSException {
		if ((this.connection != null) && (this.session != null)) {
			this.session.close();
			this.connection.close();
		}
	}
}
