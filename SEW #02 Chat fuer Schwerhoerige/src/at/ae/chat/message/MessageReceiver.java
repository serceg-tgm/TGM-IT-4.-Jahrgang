package at.ae.chat.message;

import at.ae.chat.message.Message;

/**
 * Message Listener Interface, used in the connector to notify for new messages.
 *
 * @version 1.0
 * @author Stefan Erceg
 */
public interface MessageReceiver {

	/**
	 * Called whenever a new Message is received.
	 *
	 * @param message   the message received
	 */
	void receiveMessage(Message message);
}
