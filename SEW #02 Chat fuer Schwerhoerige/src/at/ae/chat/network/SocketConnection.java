package at.ae.chat.network;

import at.ae.chat.message.Message;
import at.ae.chat.message.MessageBus;
import at.ae.chat.simulation.Main;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Connection class representing a connection to another chat participant.
 *
 * @version 1.0
 * @author Klaus Ableitinger
 */
public class SocketConnection {

	private String name;

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	private MessageReceiverThread thread;

	/**
	 * Creates a new Connection with the given name and Socket-connection.
	 *
	 * @param name      the name of the connection
	 * @param socket    the Socket of the connection
	 * @throws IOException if an I/O error occurs during stream creation
	 */
	public SocketConnection(String name, Socket socket) throws IOException {

		this.name = name;
		this.socket = socket;

		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());

		thread = new MessageReceiverThread();
		thread.start();
	}

	/**
	 * Returns the associated Socket of this connection.
	 *
	 * @return the associated Socket of this connection
	 */
	public Socket getConnection() {

		return socket;
	}

	/**
	 * Sends the given String to the connected Socket.
	 *
	 * @param message the message to sendMessage
	 */
	public void send(Message message) {

		try {

			out.writeObject(message);
			out.flush();
		} catch(IOException ex) {

			throw new RuntimeException(ex);
		}
	}

	/**
	 * Closes this connection.
	 */
	public void close() {

		thread.run = false;
		try { socket.close(); } catch(IOException ignored) { }
	}

	private class MessageReceiverThread extends Thread {

		private boolean run;

		private MessageReceiverThread() {

			run = true;
		}

		@Override
		public void run() {

			try {

				while(run) {
					Object obj = in.readObject();
					if(obj instanceof String) {

						MessageBus.messageReceived(new Message(name, (String) obj, 0));
					} else if(obj instanceof Message) {

						MessageBus.messageReceived((Message) obj);
					} else {

						Main.LOG.error("Received illegal Object: " + obj.getClass());
					}
				}
			} catch(EOFException ex) {

				SocketConnection.this.close();
				Main.LOG.info("Connection closed, EOF");
			} catch (IOException | ClassNotFoundException ex) {

				throw new RuntimeException(ex);
			}
		}
	}
}
