package at.stefanerceg.s03;

import java.util.List;

/**
* 
* Das Interface, welche von den Klassen, die einen bestimmten Operationstypen ausführen, implementiert wird, 
* beinhaltet die Methode "execute". <br>
* 
* @author Stefan Erceg
* @since 06.11.2014
* @version 20141112
*
*/

public interface Calculateable {

	/**
	 * 
	 * Der Operationstyp wird angewendet und die Werte aus der Liste werden je nach gewähltem Operationstyp
	 * zusammenaddiert/subtrahiert/multipliziert/dividiert und in eine neue Liste abgespeichert.
	 * @param l Liste
	 * @param mod Modifier
	 * @return veränderte Liste
	 */
	
	public abstract List<Double> execute(List<Double> l, double mod);

}
