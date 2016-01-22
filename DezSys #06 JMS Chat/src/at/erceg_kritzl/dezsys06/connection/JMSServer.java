package at.erceg_kritzl.dezsys06.connection;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Empfaengt die Nachricht
 * 
 * @author Martin Kritzl
 *
 */
public class JMSServer implements Receiveable {

	private Connectable connection;
	private boolean receive;
	private boolean exit;
	private static final Logger logger = LogManager.getLogger(JMSServer.class);

	public JMSServer(Connectable connection) {
		this.receive = false;
		this.connection = connection;
	}

	private void receiveMessage() {
		TextMessage message = null;
		MessageConsumer consumer = null;
		try {
			//System.out.println("reveive");
			//Je nachdem, ob ein Topic oder eine Queue eingestellt ist, wird diese weiter bearbeitet
			if (this.connection.getDestination() instanceof Topic) {
				//Es wird ein Subscriber erstellt, der den Topic mit dem Usernamen authentifiziert
				TopicSubscriber subscriber = this.connection.getSession().createDurableSubscriber((Topic) this.connection.getDestination(), this.connection.getUsername());
				
				//Es wird auf einkommende Nachrichten gewartet
				message = (TextMessage) subscriber.receive();
				
				//Der Subscriber wird wieder geschlossen, um das Aendern der Connection zu ermoeglichen
				subscriber.close();
			} else if (this.connection.getDestination() instanceof Queue) {
				//Es wird ein Consumer erstellt
				consumer = this.connection.getSession().createConsumer(this.connection.getDestination());
				
				/*Wenn der Client selber die Nachricht geschickt hat, darf dieser die Nachricht nicht wieder auslesen, da die
					Nachricht sonst nicht mehr vorhanden ist */
				if (this.connection.getDestination().toString().substring(8).equals(this.connection.getUsername()))
					message = (TextMessage) consumer.receive();
				//Der Cosumer wird wieder geschlossen, um das Aendern der Connection zu ermoeglichen
				consumer.close();
			}
			//System.out.println("bekommen");
			//Ausgeben der Nachricht
			if (message != null) {
				logger.info(message.getStringProperty("NameOfSender") + ": " + message.getText());
				message.acknowledge();
			}
		} catch (javax.jms.IllegalStateException e) {
			setReceive(false);
			logger.info("Bitte erneut anmelden. Server nicht erreichbar. Hilfe erhalten Sie unter HELP");
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (!this.exit) {
			if (this.receive)
				this.receiveMessage();
		}
	}

	@Override
	public Connectable getConnection() {
		return this.connection;
	}

	/**
	 * @return the receive
	 */
	public boolean isReceive() {
		return receive;
	}

	/**
	 * @param receive
	 *            the receive to set
	 */
	public void setReceive(boolean receive) {
		this.receive = receive;
	}

}
