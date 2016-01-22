package at.erceg_kritzl.s06;

/**
 * Erzeugt die gewueschten Implementierungen der Enten
 * 
 * @author http://examples.oreilly.de/german_examples/hfdesignpatger/
 * @author Martin Kritzl(Kommentare)
 *
 */
public abstract class AbstrakteEntenFabrik {
 
	/**
	 * Erzeugt eine Stockente
	 * @return Stockente
	 */
	public abstract Quakfaehig erzeugeStockEnte();
	
	/**
	 * Erzeugt eine MoorEnte
	 * @return Moorente
	 */
	public abstract Quakfaehig erzeugeMoorEnte();
	
	/**
	 * Erzeugt eine Lockpfeife
	 * @return Moorente
	 */
	public abstract Quakfaehig erzeugeLockPfeife();
	
	/**
	 * Erzeugt eine Gummiente
	 * @return Gummiente
	 */
	public abstract Quakfaehig erzeugeGummiEnte();
}
