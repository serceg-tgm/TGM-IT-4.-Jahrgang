package at.ae.chat.gui;

import at.ae.chat.message.MessageBus;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BadWordFilter decorator for Message Objects.
 *
 * @version 1.0
 * @author Stefan Erceg
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	/* Attributes for the gui are declared */
	
	JPanel mainPanel;
	JPanel botPanel;
	JTextField inputField;
	JTextArea contentPane;
	JButton sendButton;
	JCheckBox filterBox;

	List<String> badWords;
	Map<String, String> translations;

	String uname;

	/**
	 * All the attributes for the gui are initialized in the constructor and some elements are added to a specific
	 * JPanel.
	 * @param username Name of the user
	 */
	
	public MainFrame(String username) {

		super("CHAT");

		this.uname = username;

		/* an ArrayList is created, where some bad words are saved */
		
		badWords = new ArrayList<>();
		badWords.add("fuck");
		badWords.add("shit");
		badWords.add("bitch");

		/* a HashMap is created, where some translations are saved */
		
		translations = new HashMap<>();
		translations.put(":)", "*lol*");

		GuiListener listener = new GuiListener(this);

		/* GUI initialization */

		mainPanel = new JPanel(new BorderLayout());
		botPanel = new JPanel();

		inputField = new JTextField(30);
		inputField.addKeyListener(listener);
		contentPane = new JTextArea();
		sendButton = new JButton("send");
		sendButton.addActionListener(listener);
		filterBox = new JCheckBox("Censor", false);

		mainPanel.add(botPanel, BorderLayout.SOUTH);
		mainPanel.add(contentPane);

		botPanel.add(inputField);
		botPanel.add(sendButton);
		botPanel.add(filterBox);

		MessageBus.registerMessageListener(listener);

		this.add(mainPanel);

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
