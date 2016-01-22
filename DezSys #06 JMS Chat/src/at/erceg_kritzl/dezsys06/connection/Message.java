package at.erceg_kritzl.dezsys06.connection;

/**Stellt eine Nachricht dar
 * 
 * @author Martin Kritzl
 *
 */
public class Message implements MessageBehavior {

	private String content;
	
	public Message(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

}
