package at.ae.chat.network;

import at.ae.chat.message.Message;
import at.ae.chat.message.MessageBus;
import at.ae.chat.message.MessageSender;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Server for the Chat program, listens for chat connections.
 *
 * @version 1.0
 * @author Klaus Ableitinger
 */
public class Server implements MessageSender {


	private ServerSocket server;
	private ListenThread thread;

	private Map<String, SocketConnection> connections;

	/**
	 * Creates a new Server Object.
	 */
	public Server() {

		connections = new HashMap<>();
		MessageBus.registerCommunicator(this);
	}

	/**
	 * Starts hosting on the given port.
	 *
	 * @param port the port to host on.
	 */
	public void startHosting(int port) {

		try {

			server = new ServerSocket(port);
			thread = new ListenThread();
			thread.start();
		} catch(IOException ex) {

			throw new RuntimeException(ex);
		}
	}

	/**
	 * Stops hosting.
	 */
	public void stopHosting() {

		thread.host = false;
	}

	@Override
	public void sendMessage(Message message) {

		for(SocketConnection connection : connections.values())
			connection.send(message);
	}

	private class ListenThread extends Thread {

		private boolean host = true;

		@Override
		@SuppressWarnings({"SocketOpenedButNotSafelyClosed", "IOResourceOpenedButNotSafelyClosed"})
		public void run() {

			try {

				while(host) {

					Socket client = server.accept();
					String name = "standard";
//					String name = new BufferedReader(new InputStreamReader(client.getInputStream())).readLine();

					if(connections.containsKey(name))
						client.close();
					else
						connections.put(name, new SocketConnection(name, client));
				}
			} catch(IOException ex) {

				throw new RuntimeException(ex);
			}
		}
	}
}
