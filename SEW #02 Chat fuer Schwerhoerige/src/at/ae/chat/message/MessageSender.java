package at.ae.chat.message;

import at.ae.chat.message.Message;

/**
 * Communicator interface implemented by Client and Server.
 *
 * @version 1.0
 * @author Stefan Erceg
 */
public interface MessageSender {

	/**
	 * Sends the given message.
	 *
	 * @param message the message to send
	 */
	void sendMessage(Message message);
}
