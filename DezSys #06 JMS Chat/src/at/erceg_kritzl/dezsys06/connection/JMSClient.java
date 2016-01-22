package at.erceg_kritzl.dezsys06.connection;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;

/**Sendet die Nachricht
 * 
 * @author Martin Kritzl
 *
 */
public class JMSClient implements Sendable {

	private Connectable connection;
	
	public JMSClient(Connectable connection) {
		this.connection = connection;
	}

	@Override
	public void sendMessage(MessageBehavior toSend) {
		TextMessage message = null;
		MessageProducer producer = null;
		try {
			//Der Producer wird erstellt
			producer = this.connection.getSession().createProducer(
					this.connection.getDestination());
			
			//Die Message soll auch gespeichert bleiben, wenn der Empfaenger nicht erreichbar ist
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			
			//Message wird erstellt
			message = this.connection.getSession().createTextMessage(
					toSend.getContent());
			
			//Der Nachricht wird ein Attribut NameOfSender hinzugefuegt
			message.setStringProperty("NameOfSender", this.connection.getUsername());

			//Message wird gesendet
			producer.send(message);

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Connectable getConnection() {
		return this.connection;
	}
}
