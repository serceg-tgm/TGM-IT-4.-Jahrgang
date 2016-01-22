package at.erceggeyer.insy05.cli;

import java.io.Console;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * 
 * Der CommandLineParser implementiert das Interface Input und weist den erstellten String-Attributen die jeweiligen Eingaben des Benutzers
 * zu. Diese Attribute koennen mit getter-Methoden ausgelesen werden.
 * 
 * @author Stefan Erceg
 * @version 20150116
 * 
 */

public class CommandLineParser implements Input {

	// zusammenfassendes Options-Attribut, das die einzelnen Options zu seiner Sammlung hinzufuegt
	private Options options;
	
	// Attribute, denen die Eingaben des Benutzers zugewiesen werden, werden erstellt
	// manche Attribute werden mit ihren Standardwerten initialisiert
	
	private String  dbms = "mysql",
					hostname = "localhost",
					username = System.getProperty("user.name"), 
					userpwd = "", 
					dbname = null,
					filename = "out";
	
	private int port;

	private boolean cracked = false;

	/**
	 * 
	 * Hier fuegt das zusammenfassende Options-Attribut die einzelnen Option-Attribute fuer die jeweiligen moeglichen Eingaben des Benutzers 
	 * hinzu.
	 * 
	 * @return zusammenfassendes Options-Attribut, das alle Options hinzugefuegt hat
	 */
	
	@SuppressWarnings("static-access")
	private Options addOptions() {

		this.options = new Options();

		// einzelne Options fuer die moeglichen Eingaben des Benutzers werden erstellt und initialisiert

		Option dbms = OptionBuilder.withArgName("dbms").hasArg().
				withValueSeparator().
				withDescription("name of the dbms <default: mysql>").
				create("D");

		Option hostname = OptionBuilder.withArgName("hostname").hasArg()
				.withValueSeparator()
				.withDescription("hostname of the DBMS <default: localhost>")
				.create("h");
		
		Option username = OptionBuilder.withArgName("user-name").hasArg()
				.withValueSeparator()
				.withDescription("user-name <default: system-username>")
				.create("u");
		
		Option userpwd = OptionBuilder.withArgName("user-password").hasArg()
				.withValueSeparator()
				.withDescription("user-password").create("p");
		
		Option dbname = OptionBuilder.withArgName("database name").hasArg()
				.isRequired().withValueSeparator()
				.withDescription("database name <required>").create("d");
		
		Option port = OptionBuilder.withArgName("port number").hasArg()
				.isRequired().withValueSeparator()
				.withDescription("port number <required>").create("n");

		Option filename = OptionBuilder.withArgName("output file prefix")
				.hasArg().withValueSeparator()
				.withDescription("output file name prefix <default: out>").create("f");

		Option cracked = OptionBuilder.withArgName("crack astah licence")
				.withDescription("run astah api in cracked mode").create("c");

		// dem zusammenfassenden Options-Attribut werden alle einzelnen Option-Attribute hinzugefuegt

		this.options.addOption(dbms);
		this.options.addOption(hostname);
		this.options.addOption(username);
		this.options.addOption(userpwd);
		this.options.addOption(dbname);
		this.options.addOption(port);
		this.options.addOption(filename);
		this.options.addOption(cracked);

		return this.options;

	}

	/**
	 * @see Input#parseArgs(java.lang.String[])
	 */
	
	public void parseArgs(String[] args) {

		GnuParser parser = new GnuParser();

		try {

			CommandLine cmd = parser.parse(this.addOptions(), args);

			if (cmd.hasOption("D")) {
				this.dbms = cmd.getOptionValue('D');
			}

			if (cmd.hasOption("h")) {
				this.hostname = cmd.getOptionValue('h');
			}

			if (cmd.hasOption("u")) {
				this.username = cmd.getOptionValue('u');
			}

			if (cmd.hasOption("p")) {
				this.userpwd = cmd.getOptionValue('p');
			}

			if (cmd.hasOption("d")) {
				this.dbname = cmd.getOptionValue('d');
			}

			if (cmd.hasOption("n")) {
				this.port = Integer.parseInt(cmd.getOptionValue('n'));
			}

			if (cmd.hasOption("f")) {
				this.filename = cmd.getOptionValue('f');
			}
			
			if(!cmd.hasOption("p")) {
				System.out.print("Please enter the password: ");
				Console c = System.console();
				// das Passwort wird unsichtbar eingelesen und die Verbindung aufgebaut
				if(c != null){
					char [] pwd = c.readPassword();
					this.userpwd = new String(pwd);
				} else {
					@SuppressWarnings("resource")
					Scanner keyboard = new Scanner(System.in);
					this.userpwd = keyboard.next();
				}
			}

			if (cmd.hasOption("c")) {
				this.cracked = true;
			}
		
		// falls nicht alle notwendigen Argumente eingegeben worden sind, werden die Fehlermeldung und die Hilfe ausgegeben
			
		} catch (ParseException e) {
			System.err.println("You did not enter all the required arguments");
			this.notEnoughArgsError();
		}

	}

	/**
	 * Falls eine der benoetigten Argumente (Datenbankname, Tabellenname und gewuenschte Felder der Tabelle) nicht eingegeben worden sind,
	 * wird eine Fehlermeldung ausgegeben und durch den HelpFormatter Hilfe angeboten, indem die angebotenen Argumente und deren Beschreibung
	 * angezeigt werden.
	 */
	
	private void notEnoughArgsError() {

		HelpFormatter hf = new HelpFormatter();
		hf.printHelp("java -jar <Filename.jar> [options...] arguments...", this.options);

	}

	/**
	 * @see Input#getDBMS()
	 */

	public String getDBMS() {
		return this.dbms;
	}

	/**
	 * @see Input#getHostname()
	 */
	
	public String getHostname() {
		return this.hostname;
	}

	/**
	 * @see Input#getUsername()
	 */
	
	public String getUsername() {
		return this.username;
	}

	/**
	 * @see Input#getUserpwd()
	 */
	
	public String getUserpwd() {
		return this.userpwd;
	}
	
	/**
	 * @see Input#getDBName()
	 */
	
	public String getDBName() {
		return this.dbname;
	}

	/**
	 * @see Input#getPort()
	 */
	
	public int getPort() {
		return port;
	}

	/**
	 * @see at.erceggeyer.insy05.cli.Input#getPort()
	 */

	public String getFilename() {
		return filename;
	}

	public boolean isCracked() {
		return cracked;
	}
}
