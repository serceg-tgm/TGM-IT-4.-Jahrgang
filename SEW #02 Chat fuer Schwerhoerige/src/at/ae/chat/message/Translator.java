package at.ae.chat.message;

import java.util.HashMap;
import java.util.Map;

/**
 * Translator decorator for Message Objects.
 *
 * @version 1.0
 * @author Klaus Ableitinger
 */
@SuppressWarnings("serial")
public class Translator extends Message {

	private Map<String, String> translations;

	private Message message;

	/**
	 * Creates a new Translator by decorating the given Message.
	 *
	 * @param message the message to decorate
	 */
	public Translator(Message message) {

		super(message.getSender(), message.getText());

		this.translations = new HashMap<>();
		this.message = message;
	}

	/**
	 * Sets the Message this Translator should decorate.
	 *
	 * @param message the new Message to decorate
	 */
	public void setMessage(Message message) {

		this.message = message;
	}

	/**
	 * Adds a map of translation to this Translator.
	 *
	 * Every key in the map will be replaced by its corresponding value.
	 *
	 * @param translations the translations to add
	 */
	public void addTranslations(Map<String, String> translations) {

		this.translations.putAll(translations);
	}

	/**
	 * Adds a single Translation to this translator.
	 *
	 * @param from  the String to translate from
	 * @param to    the String to translate to
	 */
	public void addTranslation(String from, String to) {

		translations.put(from, to);
	}

	/**
	 * Returns the text returned by the defined message,
	 * with all words translated.
	 *
	 * @return bad word filtered message
	 */
	@Override
	public String toString() {

		String[] words = this.message.toString().split(" ");

		for(int i = 0; i < words.length; i++)
			for(Map.Entry<String, String> entry : translations.entrySet())
				if(words[i].equalsIgnoreCase(entry.getKey()))
					words[i] = entry.getValue();

		return String.join(" ", words).toUpperCase();
	}
}
