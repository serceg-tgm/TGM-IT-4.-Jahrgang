package at.ae.chat.message;

import java.util.ArrayList;
import java.util.List;

/**
 * Message notification bus, used to notify for new received messages.
 *
 * @version 1.0
 * @author Klaus Ableitinger
 */
public class MessageBus {

	private static List<MessageReceiver> messageListener = new ArrayList<>();
	private static List<MessageSender> messageSender = new ArrayList<>();

	private MessageBus() {}

	/**
	 * Registers the given listener to the message bus.
	 *
	 * @param listener the listener to register
	 */
	public static synchronized void registerMessageListener(MessageReceiver listener) {

		messageListener.add(listener);
	}

	/**
	 * Removes the given listener from the message bus.
	 *
	 * @param listener the listener to remove
	 */
	public static synchronized void removeMessageListener(MessageReceiver listener) {

		messageListener.remove(listener);
	}

	/**
	 * Registers the given communicator to the message bus.
	 *
	 * @param messageSender the communicator to register
	 */
	public static synchronized void registerCommunicator(MessageSender messageSender) {

		MessageBus.messageSender.add(messageSender);
	}

	/**
	 * Removes the given Communicator from the message bus.
	 *
	 * @param messageSender the communicator to remove
	 */
	public static synchronized void removeCommunicator(MessageSender messageSender) {

		MessageBus.messageSender.remove(messageSender);
	}

	/**
	 * Notifies all registered listeners of the received message.
	 *
	 * @param message   the message text
	 */
	public static synchronized void messageReceived(Message message) {

		for(MessageReceiver listener : messageListener)
			listener.receiveMessage(message);
	}

	/**
	 * Notifies all registered communicator, that a new message should be sent.
	 *
	 * @param message the Message to sendMessage
	 */
	public static synchronized void sendMessage(Message message) {

		for(MessageSender messageSender : MessageBus.messageSender)
			messageSender.sendMessage(message);
	}
}
