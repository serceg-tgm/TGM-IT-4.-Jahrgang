package at.erceg_kritzl.s06;

/**
 * Stellt die Main-Klasse des Programms dar. Der Quakologe gibt aus, welcher Typ von Ente gerade gequakt
 * hat und wie oft die Enten gequakt haben.
 * 
 * @author http://examples.oreilly.de/german_examples/hfdesignpatger/
 * @author Stefan Erceg(Kommentare)
 *
 */

public class EntenSimulator {
	
	/**
	 * Main-Methode des Programms.
	 * @param args
	 */
	
	public static void main(String[] args) {
		EntenSimulator simulator = new EntenSimulator();
		AbstrakteEntenFabrik entenFabrik = new ZaehlendeEntenFabrik();
 
		simulator.simulieren(entenFabrik);
	}
  
	/**
	 * Einige Typen von Enten werden erzeugt, die von einem Quakologen beobachtet werden.
	 * @param entenfabrik AbstrakteEntenFabrik, damit die Typen von Enten erzeugt werden koennen
	 */
	
	public void simulieren(AbstrakteEntenFabrik entenfabrik) {
  
		Quakfaehig moorEnte = entenfabrik.erzeugeMoorEnte();
		Quakfaehig lockPfeife = entenfabrik.erzeugeLockPfeife();
		Quakfaehig gummiEnte = entenfabrik.erzeugeGummiEnte();
		Quakfaehig gansEnte = new GansAdapter(new Gans());
 
		Schar EntenSchar = new Schar();
 
		EntenSchar.hinzufuegen(moorEnte);
		EntenSchar.hinzufuegen(lockPfeife);
		EntenSchar.hinzufuegen(gummiEnte);
		EntenSchar.hinzufuegen(gansEnte);
 
		Schar stockEntenSchar = new Schar();
 
		Quakfaehig stockEnte1 = entenfabrik.erzeugeStockEnte();
		Quakfaehig stockEnte2 = entenfabrik.erzeugeStockEnte();

		stockEntenSchar.hinzufuegen(stockEnte1);
		stockEntenSchar.hinzufuegen(stockEnte2);

		EntenSchar.hinzufuegen(stockEntenSchar);

		System.out.println("\nEntensimulator: mit Observer");

		Quakologe quakologe = new Quakologe();
		EntenSchar.registriereBeobachter(quakologe);

		simulieren(EntenSchar);

		System.out.println("\nDie Enten haben " + 
		                   QuakZaehler.getQuaks() + 
		                   "-mal gequakt.");
	}
 
	/**
	 * Die Enten fangen zu quaken an.
	 * @param ente Typen von Enten
	 */
	
	void simulieren(Quakfaehig ente) {
		ente.quaken();
	}
}
