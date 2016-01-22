package at.erceg_kritzl.dezsys06.handler;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import at.erceg_kritzl.dezsys06.connection.Message;
import at.erceg_kritzl.dezsys06.display.CLI;
import at.erceg_kritzl.dezsys06.display.Control;

/**
 * Interpretiert den angegebenen String und taetigt etwaige Befehle
 * 
 * @author Martin Kritzl
 *
 */
public class InputHandler implements Handler {

	public static final Logger logger = LogManager.getLogger(CLI.class);

	@Override
	public void interpretLine(Control control) {
		// Die inputLine wird geteilt
		String line = control.getDisplay().getMessage().getContent();
		String[] args = line.split(" ");
		// Benutzername und URL wird gesetzt
		if (args[0].equalsIgnoreCase("LOGIN")) {
			if (args.length == 3) {
				control.getServer().setReceive(false);
				control.getConnection().init(args[1], args[2]);
			}else
				notEnoughArg(control);

			// Alle moeglichen Befehle werden ausgegeben
		} else if (args[0].equalsIgnoreCase("HELP")) {
			logger.info("Verfügbare Commands:");
			logger.info("");
			logger.info("HELP");
			logger.info("\tZeigt die moeglichen Commandos");
			logger.info("LOGIN <Name> <ipAdressJMS>:<PortJMS>");
			logger.info("\tGeben Sie ihren Benuternamen an um sich zu authetifizieren");
			logger.info("JOIN <chatroom>");
			logger.info("\tTreten Sie einem Chatraum bei");
			logger.info("MAIL <Name> <Content>");
			logger.info("\tSchickt eine persoenliche Nachricht an einen anderen Rechner");
			logger.info("MAILBOX");
			logger.info("\tEmpfaengt alle persoenlichen Nachrichten");
			logger.info("EXIT");
			logger.info("\tBeendet das Programm");

			// Das Programm wird beendet.
		} else if (args[0].equalsIgnoreCase("EXIT")) {
			System.exit(0);
			// Schickt die Nachricht ueber die eingestellte Connection.

			// Diese Befehle duerfen erst ausgefuehrt werden wenn der User
			// eingeloggt ist
		} else if (control.getConnection().getUsername() != null) {
			// Ein Chatraum wird betreten
			if (args[0].equalsIgnoreCase("JOIN")) {

				if (args.length == 2) {
					/*
					 * Setzen der Connection auf Topic In der Zwischenzeit darf
					 * der Server nicht receiven, da ansonsten fehlerhafte
					 * Connections genutzt werden koennten.
					 */
					control.getServer().setReceive(false);
					control.getConnection().setTopic(args[1]);
					control.getServer().setReceive(true);
				} else
					notEnoughArg(control);

				// Eine Mail wird geschickt
			} else if (args[0].equalsIgnoreCase("MAIL")) {

				if (args.length >= 3) {
					/*
					 * Setzen der Connection auf Queue In der Zwischenzeit darf
					 * der Server nicht receiven, da ansonsten fehlerhafte
					 * Connections genutzt werden koennten.
					 */
					control.getServer().setReceive(false);
					control.getConnection().setQueue(args[1]);
					control.getServer().setReceive(true);
					// Schicken der Nachricht
					control.getClient().sendMessage(
							new Message(
									line.substring(
											line.indexOf(" ",
													line.indexOf(args[1])) + 1,
											line.length())));
				} else
					notEnoughArg(control);

				// Die Mailbox wird aufgerufen
			} else if (args[0].equalsIgnoreCase("MAILBOX")) {

				if (args.length == 1) {
					/*
					 * Setzen der Connection auf Queue In der Zwischenzeit darf
					 * der Server nicht receiven, da ansonsten fehlerhafte
					 * Connections genutzt werden koennten.
					 */
					control.getServer().setReceive(false);
					// Die Mailbox ist immer der eigene Benutzername
					control.getConnection().setQueue(
							control.getConnection().getUsername());
					control.getServer().setReceive(true);
				} else
					notEnoughArg(control);
			} else {
				// Nur wenn die Connection gesetzt ist

				if (control.getConnection().getConnection() != null)
					control.getClient().sendMessage(new Message(line));
				else
					connectionNotSet(control);

			}

		} else {
			nameNotSet(control);
		}
	}

	/**
	 * Gibt eine Fehlermeldung ueber das Display aus. Falsche Parameter
	 */
	private void notEnoughArg(Control control) {
		logger.error("Falsche Parameter. Hilfe unter HELP");
	}

	/**
	 * Gibt eine Fehlermeldung ueber das Display aus. Benutzername nicht
	 * eingegeben
	 */
	private void nameNotSet(Control control) {
		logger.error("Bitte melden Sie sich zuerst mit LOGIN an. Hilfe unter HELP");
	}

	/**
	 * Gibt eine Fehlermeldung ueber das Display aus. Keine Connection
	 * vorhanden.
	 */
	private void connectionNotSet(Control control) {
		logger.error("Nicht verbunden. Bitte mit den Befehlen JOIN, MAIL oder MAILBOX anmelden. Hilfe unter HELP");
	}
}
