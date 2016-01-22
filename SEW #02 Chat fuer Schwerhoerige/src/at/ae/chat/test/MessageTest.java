package at.ae.chat.test;

import at.ae.chat.message.BadWordFilter;
import at.ae.chat.message.Message;
import at.ae.chat.message.MessageBus;
import at.ae.chat.message.MessageReceiver;
import at.ae.chat.message.MessageSender;
import at.ae.chat.message.Translator;
import at.ae.chat.network.Client;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Testclass, which test the BadWordFilter and the Translator.
 *
 * @version 1.0
 * @author Stefan Erceg
 */
public class MessageTest {

	private Message message;

	/**
	 * Added some bad words to a list and tested if the words in the message are replaced
	 */
	
	@Test
	public void testBadWordFilter() {

		message = new Message("asdf", "fuck lol Erceg Ableitinger");
		BadWordFilter filter = new BadWordFilter(message);
		List<String> badWords = new ArrayList<>();
		badWords.add("fuck");
		badWords.add("Erceg");

		filter.addBadWords(badWords);

		assertEquals("$%&* lol $%&* Ableitinger", filter.toString());
	
	}

	/**
	 * Added some translator to a map and tested if the words in the massage are translated
	 */
	
	@Test
	public void testTranslator() {

		message = new Message("asdf", "fuck lol Erceg Ableitinger");
		Translator filter = new Translator(message);
		Map<String, String> translations = new HashMap<>();
		translations.put("lol", "*ha*");
		translations.put("Ableitinger", "Meister");

		filter.addTranslations(translations);

		assertEquals("FUCK *HA* ERCEG MEISTER", filter.toString());
	
	}
	
}