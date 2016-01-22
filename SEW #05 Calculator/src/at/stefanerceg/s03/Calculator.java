package at.stefanerceg.s03;

import java.util.ArrayList;
import java.util.List;

/**
* 
* In der Klasse werden double-Werte in eine Liste gespeichert bzw. entfernt. Diese Werte aus der Liste werden dann durch 
* einen entsprechenden Modifier (addieren/subtrahieren/multiplizieren/dividieren) ge�ndert und in eine neue Liste
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
	 * 	- modifier: Modifier, mit dem die Werte ge�ndert werden
	 * 	- operation: Opperationstyp (Addition/Substraktion/Multiplikation/Division)
	 */
	
	private List<Double> values;

	private double modifier;

	private Calculateable operation;

	/**
	 * Im Konstruktor werden die Attribute initialisiert. F�r die values-Liste wurde eine ArrayList ausgew�hlt.
	 */
	
	public Calculator() {

		values = new ArrayList<Double>();
		modifier = 0.0;
		operation = null;
		
	}

	/**
	 * Der im Parameter �bergebene Wert wird zur Liste an das Ende hinzugef�gt.
	 * @param value Wert, der zur Liste hinzugef�gt werden soll
	 */
	
	public void addValue(double value) {
		
		this.values.add(value);
		
	}

	/**
	 * Der im Parameter �bergebene Wert wird von der Liste gel�scht. Ist dieser Wert in der Liste mehrmals vorhanden,
	 * wird nur der erstvorkommende Wert entfernt.
	 * @param value Wert, der von der Liste gel�scht werden soll
	 */
	
	public void removeValue(double value) {

		this.values.remove(value);
		
	}

	/**
	 * Eine setter-Methode, die den gew�nschten Modifier setzt.
	 * @param modifier Modifier, mit dem die Werte ge�ndert werden
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
	 * Der ausgew�hlte Modifier und die Werte aus der Liste werden in einem String gespeichert und zur�ckgegeben.
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
	 * Mittels dieser Methode kann man sich den aktuell verwendeten Operationstyp zur�ckgeben.
	 * @return aktuell verwendeter Operationstyp
	 */
	
	public Calculateable getCalculateable() {
		
		return this.operation;
	
	}

}
