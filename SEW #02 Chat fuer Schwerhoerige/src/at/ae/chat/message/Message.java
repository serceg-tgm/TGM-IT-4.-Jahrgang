package at.ae.chat.message;

import java.io.Serializable;

/**
 * Message class representing a Message sent over the network.
 *
 * @version 1.0
 * @author Klaus Ableitinger
 */
@SuppressWarnings("serial")
public class Message implements Serializable {

	private String sender;

	private String text;
	private Long timestamp;

	/**
	 * Creates a new Message Object with the given message
	 * and the current Timestamp.
	 *
	 * @param sender the sender of this Message
	 * @param text the text
	 */
	public Message(String sender, String text) {

		this(sender, text, System.currentTimeMillis());
	}

	/**
	 * Creates a new Message Object with the given text
	 * and the given Timestamp.
	 *
	 * @param sender the sender of this Message
	 * @param text   the text
	 * @param timestamp the timestamp to create the Message Object with
	 */
	public Message(String sender, String text, long timestamp) {

		this.sender = sender;
		this.text = text == null ? "" : text;
		this.timestamp = timestamp;
	}

	/**
	 * Returns this message's raw text.
	 *
	 * @return the text of this Message
	 */
	public final String getText() {

		return text;
	}

	/**
	 * Returns this Message's timestamp.
	 *
	 * @return the timestamp of this Message
	 */
	public final long getTimestamp() {

		return timestamp;
	}

	/**
	 * Returns this Message's sender.
	 *
	 * @return this Message's sender
	 */
	public String getSender() {

		return sender;
	}

	/**
	 * Returns this text's text.
	 *
	 * @return the text text of this text
	 */
	public String toString() {

		return text;
	}
}
