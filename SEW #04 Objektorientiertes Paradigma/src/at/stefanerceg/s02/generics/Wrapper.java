package at.stefanerceg.s02.generics;

/**
 * In der Klasse "Wrapper" wird mit generischen Datentypen gearbeitet. Ein bestimmter Wert, welcher über den 
 * Konstruktor angegeben wird, mittels getter- und setter-Methoden ausgelesen und verändert. <br>
 * 
 * @author Stefan Erceg
 * @since 10.10.2014
 * @version 1.0
 *
 */

public class Wrapper<T> {
	
	private T value;
	
	/**
	 * der Konstruktor weist dem Attribut "value" den im Parameter eingegeben Wert zu
	 * @param t generischer Datentyp wird definiert
	 */
	
	public Wrapper(T t)	{
		this.value = t;
	}
	
	/**
	 * getter-Methode, welche den Wert zurückgibt
	 * @return Wert
	 */
	
	public T getValue()	{
		return value;
	}
	
	/**
	 * setter-Methode, mittels welcher der Wert verändert werden kann
	 * @param value neuer Wert
	 */
	
	public void setValue(T value)	{
		this.value = value;
	}
	
	/**
	 * der Wert des generischen Datentyps wird zurückgegeben
	 * @see java.lang.Object#toString()
	 */
	
	@Override
	public String toString()	{
		return "" + getValue();
	}
}
