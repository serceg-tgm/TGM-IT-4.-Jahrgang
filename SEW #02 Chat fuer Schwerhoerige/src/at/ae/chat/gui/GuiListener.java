package at.ae.chat.gui;

import at.ae.chat.message.BadWordFilter;
import at.ae.chat.message.Message;
import at.ae.chat.message.MessageBus;
import at.ae.chat.message.MessageReceiver;
import at.ae.chat.message.Translator;
import at.ae.chat.simulation.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The class implements the interfaces MessageReceiver, ActionListener and KeyListener. 
 *
 * @version 1.0
 * @author Stefan Erceg
 */
public class GuiListener implements MessageReceiver, ActionListener, KeyListener {

	/* Attributes for the MainFrame, the bad word filter and the translator are declared  */
	
	MainFrame frame;

	BadWordFilter filter;
	Translator translator;

	/**
	 * Creates a new GuiListener with the given MainFrame.
	 *
	 * @param frame the MainFrame this listener belongs to
	 */
	public GuiListener(MainFrame frame) {

		this.frame = frame;

		/* the bad words, which are available in the class MainFrame, are added to the bad word 
		   filter-object */
		
		filter = new BadWordFilter(new Message("", ""));
		filter.addBadWords(frame.badWords);

		/* the translations, which are available in the class MainFrame, are added to the 
		   translator-object */
		
		translator = new Translator(filter);
		translator.addTranslations(frame.translations);
	}
	
	@Override
	public void receiveMessage(Message message) {

		/* a log-info is going to be created when a message is received */
		
		Main.LOG.info("Received Message from " + message.getSender() + ": " + message);

		/* it is checked, if the message has one or more bad words
		 * if there are some bad words, the message is going to be new set */
		
		filter.setActive(frame.filterBox.isSelected());
		filter.setMessage(message);
		
		/* if you receive a message, the sender and the text are going to be shown on your display */
		
		frame.contentPane.append(message.getSender() + ": " + translator + "\n");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String text = frame.inputField.getText();

		/* a log-info is going to be created when a message is sent */
		
		Main.LOG.info("Sending Message: " + text);

		/* if you send a message, the characters ">>" and the text are going to be shown on your 
		   display */
		
		frame.contentPane.append(">> " + text + "\n");
		MessageBus.sendMessage(new Message(frame.uname, text));
		frame.inputField.setText("");
	}

	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyPressed(KeyEvent ev) {

		if(ev.getKeyCode() == KeyEvent.VK_ENTER)
			frame.sendButton.doClick();
	}

	@Override
	public void keyReleased(KeyEvent e) { }
}
