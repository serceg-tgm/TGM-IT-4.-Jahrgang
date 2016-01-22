package at.erceg_kritzl.s06;

/**
 * Erbt von der Klasse AbstrakteEntenFabrik und erzeugt daher die gewueschten Implementierungen der Enten.
 * Die Anzahl der Enten werden zusaetzlich noch gezaehlt.
 * 
 * @author http://examples.oreilly.de/german_examples/hfdesignpatger/
 * @author Stefan Erceg(Kommentare)
 *
 */
public class ZaehlendeEntenFabrik extends AbstrakteEntenFabrik {
  
	/**Erzeugt eine Stockente
	 * 
	 * @return Stockente
	 */
	public Quakfaehig erzeugeStockEnte() {
		return new QuakZaehler(new StockEnte());
	}
  
	/**Erzeugt eine MoorEnte
	 * 
	 * @return Moorente
	 */
	public Quakfaehig erzeugeMoorEnte() {
		return new QuakZaehler(new MoorEnte());
	}
  
	/**Erzeugt eine Lockpfeife
	 * 
	 * @return Moorente
	 */
	public Quakfaehig erzeugeLockPfeife() {
		return new QuakZaehler(new LockPfeife());
	}
   
	/**Erzeugt eine Gummiente
	 * 
	 * @return Gummiente
	 */
	public Quakfaehig erzeugeGummiEnte() {
		return new QuakZaehler(new GummiEnte());
	}
}
