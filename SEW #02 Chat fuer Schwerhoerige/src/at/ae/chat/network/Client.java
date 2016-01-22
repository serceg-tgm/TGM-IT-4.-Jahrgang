package at.ae.chat.network;

import at.ae.chat.message.Message;
import at.ae.chat.message.MessageBus;
import at.ae.chat.message.MessageSender;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Client class, representing a chat client.
 *
 * @version 1.0
 * @author Stefan Erceg
 */
public class Client implements MessageSender {

	private SocketConnection connection;

	/**
	 * Creates a new Client object.
	 */
	public Client() {

		connection = null;
		MessageBus.registerCommunicator(this);
	}

	/**
	 * Connects the client to the given host ip-address on the given port.
	 *
	 * @param ip    the ip to connect to
	 * @param port  the port to connect to
	 * @throws UnknownHostException if the connection couldn't be established
	 * @throws IOException          if a I/O error occurs during connection
	 */
	public void connect(String ip, int port) throws UnknownHostException, IOException {

		this.connection = new SocketConnection("partner", new Socket(ip, port));
	}

	/**
	 * Returns the connection, or null when not connected.
	 *
	 * @return the client connection
	 */
	public SocketConnection getConnection() {

		return connection;
	}

	/**
	 * Sends the given message to the connected server.
	 *
	 * @param message the message to sendMessage
	 * @throws NullPointerException if the client isnÄt connected
	 */
	@Override
	public void sendMessage(Message message) {

		connection.send(message);
	}
}
