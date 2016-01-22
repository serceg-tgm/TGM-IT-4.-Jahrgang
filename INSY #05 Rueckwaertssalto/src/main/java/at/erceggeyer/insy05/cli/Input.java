package at.erceggeyer.insy05.cli;

/**
 * 
 * Das Interface bietet eine Methode zum Parsen der Argumente und mehrere getter-Methoden fuer die jeweiligen String-Attribute, denen die
 * Eingaben des Benutzers zugewiesen werden, an.
 * 
 * @author Stefan Erceg
 * @version 20150116
 * 
 */

public interface Input {
	
	/**
	 * Mittels der Methode werden die im Parameter uebergebenen Argumente in Attribute gespeichert, um diese spaeter wieder auslesen zu koennen.
	 * 
	 * @param args Eingaben des Benutzers
	 */
	
	public void parseArgs(String[] args);

	/**
	 * Gibt den DBMS namen zurück
	 *
	 * @return DBMS
	 */
	public String getDBMS();

	/**
	 * the output filename
	 *
	 * @return filename
	 */
	public String getFilename();

	/**
	 * Eine getter-Methode fuer den Hostnamen.
	 * 
	 * @return Hostname
	 */
	
	public String getHostname();

	/**
	 * Eine getter-Methode fuer den Usernamen.
	 * 
	 * @return Username
	 */
	
	public String getUsername();
	
	/**
	 * Eine getter-Methode fuer das Passwort des Users.
	 * 
	 * @return User-Passwort
	 */
	
	public String getUserpwd();
	
	/**
	 * Eine getter-Methode fuer den Datenbanknamen.
	 * 
	 * @return Datenbankname
	 */
	
	public String getDBName();
	
	/**
	 * Eine getter-Methode fuer den Port.
	 * 
	 * @return Port
	 */
	
	public int getPort();

	public boolean isCracked();
	
}