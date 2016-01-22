package at.stefanerceg.s03;

import java.util.ArrayList;
import java.util.List;

/**
* 
* In der Klasse werden double-Werte in eine Liste gespeichert bzw. entfernt. Diese Werte aus der Liste werden dann durch 
* einen entsprechenden Modifier (addieren/subtrahieren/multiplizieren/dividieren) geändert und in eine neue Liste
* gespeichert. <br>
* 
* @author Stefan Erceg
* @since 06.11.2014
* @version 20141112
*
*/

public class Calculator {

	/* Attribute:
	 * 	- values: Liste, welche die Werte beinhaltet
	 * 	- modifier: Modifier, mit dem die Werte geändert werden
	 * 	- operation: Opperationstyp (Addition/Substraktion/Multiplikation/Division)
	 */
	
	private List<Double> values;

	private double modifier;

	private Calculateable operation;

	/**
	 * Im Konstruktor werden die Attribute initialisiert. Für die values-Liste wurde eine ArrayList ausgewählt.
	 */
	
	public Calculator() {

		values = new ArrayList<Double>();
		modifier = 0.0;
		operation = null;
		
	}

	/**
	 * Der im Parameter übergebene Wert wird zur Liste an das Ende hinzugefügt.
	 * @param value Wert, der zur Liste hinzugefügt werden soll
	 */
	
	public void addValue(double value) {
		
		this.values.add(value);
		
	}

	/**
	 * Der im Parameter übergebene Wert wird von der Liste gelöscht. Ist dieser Wert in der Liste mehrmals vorhanden,
	 * wird nur der erstvorkommende Wert entfernt.
	 * @param value Wert, der von der Liste gelöscht werden soll
	 */
	
	public void removeValue(double value) {

		this.values.remove(value);
		
	}

	/**
	 * Eine setter-Methode, die den gewünschten Modifier setzt.
	 * @param modifier Modifier, mit dem die Werte geändert werden
	 */
	
	public void setModifier(double modifier) {

		this.modifier = modifier;
		
	}

	/**
	 * Die jeweilige Operation wird angewendet und die Liste dem Attribut "values" zugewiesen.
	 */
	
	public void executeCalculation() {
		
		this.values = this.operation.execute(values, modifier);
		
	}

	/**
	 * Der ausgewählte Modifier und die Werte aus der Liste werden in einem String gespeichert und zurückgegeben.
	 */
	
	public String toString() {

		String temp = "Modifier: " + this.modifier + "\n";
		
		temp += "Values: ";
		
		for(Double value : this.values) {
			temp += Double.valueOf(value) + " | ";
		}
		
		return temp;
		
	}

	/**
	 * Mittels dieser Methode kann der Operationstyp gesetzt werden.
	 * @param c Operationstyp
	 */

	public void setCalculateable(Calculateable c) {
		
		this.operation = c;	
	
	}

	/**
	 * Mittels dieser Methode kann man sich den aktuell verwendeten Operationstyp zurückgeben.
	 * @return aktuell verwendeter Operationstyp
	 */
	
	public Calculateable getCalculateable() {
		
		return this.operation;
	
	}

}
