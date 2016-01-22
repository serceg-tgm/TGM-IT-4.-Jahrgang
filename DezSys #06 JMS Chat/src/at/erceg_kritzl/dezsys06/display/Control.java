package at.erceg_kritzl.dezsys06.display;

import at.erceg_kritzl.dezsys06.connection.Connect;
import at.erceg_kritzl.dezsys06.connection.Connectable;
import at.erceg_kritzl.dezsys06.connection.JMSClient;
import at.erceg_kritzl.dezsys06.connection.JMSServer;
import at.erceg_kritzl.dezsys06.connection.Receiveable;
import at.erceg_kritzl.dezsys06.connection.Sendable;
import at.erceg_kritzl.dezsys06.handler.Handler;
import at.erceg_kritzl.dezsys06.handler.InputHandler;

/**Initialisiert alle notwendigen Komponenten und stellt die Hauptklasse dar
 * 
 * @author Martin Kritzl
 *
 */

public class Control {

	private Handler handler;
	private Display display;
	private Connectable connection;
	private Sendable client;
	private Receiveable server;

	public Control(){
		this.connection = new Connect();
		this.handler = new InputHandler();
		this.server = new JMSServer(this.connection);
		Thread JMSServer = new Thread((Runnable)this.server);
		JMSServer.start();
		this.client = new JMSClient(this.connection);
		this.display = new CLI(this);
		Thread JMSCLI = new Thread((Runnable)this.display);
		JMSCLI.start();
	}
	
	public Handler getHandler() {
		return handler;
	}
	
	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Display getDisplay() {
		return display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

	public Connectable getConnection() {
		return connection;
	}

	public void setConnection(Connectable connection) {
		this.connection = connection;
	}
	
	public static void main(String[] args) {
		new Control();
	}
	
	

	/**
	 * @return the client
	 */
	public Sendable getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Sendable client) {
		this.client = client;
	}

	/**
	 * @return the server
	 */
	public Receiveable getServer() {
		return server;
	}

	/**
	 * @param server the server to set
	 */
	public void setServer(Receiveable server) {
		this.server = server;
	}

}
