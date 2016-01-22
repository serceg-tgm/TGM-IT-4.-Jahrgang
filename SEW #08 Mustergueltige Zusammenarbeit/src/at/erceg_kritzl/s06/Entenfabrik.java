package at.erceg_kritzl.s06;

/**
 * Erbt von der Klasse AbstrakteEntenFabrik und erzeugt daher die gewueschten Implementierungen der Enten.
 * 
 * @author http://examples.oreilly.de/german_examples/hfdesignpatger/
 * @author Stefan Erceg(Kommentare)
 *
 */
public class Entenfabrik extends AbstrakteEntenFabrik {
  
	/**Erzeugt eine Stockente
	 * 
	 * @return Stockente
	 */
	public Quakfaehig erzeugeStockEnte() {
		return new StockEnte();
	}
  
	/**Erzeugt eine MoorEnte
	 * 
	 * @return Moorente
	 */
	public Quakfaehig erzeugeMoorEnte() {
		return new MoorEnte();
	}
  
	/**Erzeugt eine Lockpfeife
	 * 
	 * @return Moorente
	 */
	public Quakfaehig erzeugeLockPfeife() {
		return new LockPfeife();
	}
   
	/**Erzeugt eine Gummiente
	 * 
	 * @return Gummiente
	 */
	public Quakfaehig erzeugeGummiEnte() {
		return new GummiEnte();
	}
}
