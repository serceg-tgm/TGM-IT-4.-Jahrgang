package at.ae.chat.message;

import java.util.ArrayList;
import java.util.List;

/**
 * BadWordFilter decorator for Message Objects.
 *
 * @version 1.0
 * @author Klaus Ableitinger
 */
@SuppressWarnings("serial")
public class BadWordFilter extends Message {

	/** Default replacement String for bad words */
	public static final String BAD_WORD_REPLACEMENT = "$%&*";

	private List<String> badWords;

	private Message message;

	private boolean active;

	/**
	 * Creates a new BadWordFilter by decorating the given Message.
	 *
	 * @param message the message to decorate
	 */
	public BadWordFilter(Message message) {

		super(message.getSender(), message.getText());

		this.badWords = new ArrayList<>();
		this.message = message;
		this.active = true;
	
	}

	/**
	 * Sets the Message this BadWordFilter should decorate.
	 *
	 * @param message the new Message to decorate
	 */
	public void setMessage(Message message) {

		this.message = message;
	}

	/**
	 * Returns the text returned by the defined message,
	 * filtered from the given bad words.
	 *
	 * @return bad word filtered message
	 */
	public String toString() {

		if(!active) return message.toString();

		String[] words = message.toString().split(" ");

		for(int i = 0; i < words.length; i++)
			for(String badWord : badWords)
				if(words[i].equalsIgnoreCase(badWord))
					words[i] = BAD_WORD_REPLACEMENT;

		return String.join(" ", words);
	}


	/**
	 * Adds a list of bad words to this BadWordFilter.
	 *
	 * @param badWords the bad words to add
	 */
	public void addBadWords(List<String> badWords) {

		this.badWords.addAll(badWords);
	}

	/**
	 * Set whether this BadWordFilter should filter the bad words.
	 *
	 * @param active whether to activate this BadWordFilter
	 */
	public void setActive(boolean active) {

		this.active = active;
	}
}
