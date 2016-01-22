package at.ae.chat.simulation;

import at.ae.chat.gui.MainFrame;
import at.ae.chat.network.Client;
import at.ae.chat.network.Server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main class of the Chat application.
 *
 * @version 1.0
 * @author Stefan Erceg
 */
public class Main {

	/* Chat logger */
	
	public static final Logger LOG = LogManager.getLogger("tgm.sew.chat");

	/* Command line arguments */
	
	private static final String USAGE = "java -jar <Main.jar> <server|client> <ip|port> [port] <username>";

	/**
	 * Main method of the chat application.
	 *
	 * @param args program arguments
	 */
	public static void main(String[] args) {

		String username;

		try {

			/* if no arguments were entered, a notification will be shown */
			
			if(args.length == 0) {

				System.out.println(USAGE);
				return;
			}

			/* if the first argument is "server", a new server-object will be created and started */
			
			if(args[0].equalsIgnoreCase("server")) {

				/* if there are not 3 arguments, a notification will be shown */
				
				if(args.length != 3) {

					System.out.println(USAGE);
					return;
				}

				Server server = new Server();
				server.startHosting(Integer.parseInt(args[1]));
				username = args[2];
			
			/* if the first argument is "server", a new server-object will be created and started */
				
			} else if(args[0].equalsIgnoreCase("client")) {

				/* if there are not 4 arguments, a notification will be shown */
				
				if(args.length != 4) {

					System.out.println(USAGE);
					return;
				}

				Client client = new Client();

				while(true) {

					try {

						client.connect(args[1], Integer.parseInt(args[2]));
						username = args[3];
						break;
					
					/* if the entered port number is not a number, a notification will be sent */
						
					} catch(NumberFormatException ex) {

						System.out.println("Port not a number");
						System.out.println(USAGE);
						return;
					
					/* if the client couln't connect to the server, a connection will be retried in 2 seconds */
						
					} catch(Exception ex) {

						LOG.warn("Couldn't connect to server, retrying in 2 seconds");
						try { Thread.sleep(2000); } catch(InterruptedException ignore) { }
					
					}
				
				}
			
			/* if the arguments are not correct set, a notification will be shown */
				
			} else {

				System.out.println(USAGE);
				return;
			}
			
			/* a new MainFrame-object is decleared and the size of the window is set by 700 * 500 pixel */

			MainFrame mainFrame = new MainFrame(username);
			mainFrame.setSize(700, 500);
			mainFrame.setVisible(true);

		/* if the entered port number is not a number, a notification will be sent */
			
		} catch(NumberFormatException e) {

			System.out.println("Port not a number");
			System.out.println(USAGE);
		}
		
	}
	
}
