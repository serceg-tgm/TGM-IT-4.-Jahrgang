package at.erceg_kritzl.dezsys06.display;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.erceg_kritzl.dezsys06.connection.Message;
import at.erceg_kritzl.dezsys06.connection.MessageBehavior;

/**
 * Liest die Eingaben aus der CLI und zeigt erhaltene Nachrichten an
 * 
 * @author Martin Kritzl
 *
 */
public class CLI implements Display {
	
	private Control control;
	private MessageBehavior message;
	public static final Logger logger = LogManager.getLogger("CLI");

	public CLI(Control control) {
		this.control = control;
		logger.info("Willkommen in unserem Chatclient. Hilfe erhalten Sie unter HELP");
	}

	@Override
	public void run() {
		while (true) {
			try {
				//Liest aus der Console aus
				this.setMessage(new Message(new BufferedReader(
						new InputStreamReader(System.in)).readLine()));
				//Wenn diese nicht "" ist, wird sie interpretiert
				if (!this.getMessage().getContent().equals(""))
					this.control.getHandler().interpretLine(this.control);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return the message
	 */
	public MessageBehavior getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(MessageBehavior message) {
		this.message = message;
	}
	
	public static Logger getLogger() {
		return logger;
		
	}
}