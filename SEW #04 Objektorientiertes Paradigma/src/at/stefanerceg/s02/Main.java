package at.stefanerceg.s02;

import at.stefanerceg.s02.generics.Wrapper;
import at.stefanerceg.s02.konto.Girokonto;
import at.stefanerceg.s02.konto.Konto;
import at.stefanerceg.s02.konto.Losungswortsparkonto;
import at.stefanerceg.s02.konto.NotEnoughMoneyException;
import at.stefanerceg.s02.konto.Sparkonto;

/**
* 
* In der Klasse "Main" werden mittels einigen Beispielen die Grundlagen des objektorientierten Paradigmas erklärt.
* Es wird hier besonders auf die Polymorphie geachtet. Es wird ebenfalls der Unterschied zwischen der Vererbung
* und der Laufzeit-Polymorphie erarbeitet und auf die unterschiedlichen Typdefinitionen (deklarierter, dynamischer
* und statischer Typ) eingegangen. <br>
* 
* @author Stefan Erceg
* @since 10.10.2014
* @version 1.0
*
*/

public class Main {
	
	/**
	 * 1.Bsp für: Polymorphie / adHoc-Polymorphie / Typanpassung / Explizite Typanpassung. <br>
	 * Bei dieser Methode wird ein neues Losungswortsparkonto-Objekt lsk erstellt, welches auch als Losungswortsparkonto
	 * initialisiert wird. <br>
	 * Danach wird ein neues Sparkonto-Objekt sk erstellt, welches das Losungswortsparkonto-Objekt auf ein Sparkonto 
	 * castet. Es sollte jetzt angenommen werden, dass sk wie ein normales Sparkonto-Objekt reagiert und nur dessen
	 * verfügbare Methoden verwenden kann. <br>
	 * Auf das Sparkonto wurde nun 16 000,- Euro eingezahlt. Eine IllegalArgumentException wird geworfen, da in der 
	 * Losungswortsparkonto-Klasse bei der Methode "einzahlen" definiert wurde, dass eine Exception geworfen werden
	 * soll, wenn ein Betrag größer als 15 000,- Euro eingezahlt werden möchte. Eine Eingrenzung bei der Methode
	 * "einzahlen" von der Klasse Sparkonto existiert nicht. <br>
	 * Dies bedeutet, dass sk auch beim Casten des Losungswortsparkonto-Objekts auf ein Sparkonto die Methoden von lsk 
	 * verwendet und es sich in Wirklichkeit weiterhin um ein Losungswortsparkonto-Objekt handelt.
	 */
	
	public static void explTypanpassung1() {
		Losungswortsparkonto lsk = new Losungswortsparkonto(12345,5,"test");
		Sparkonto sk = (Sparkonto) lsk;
		sk.einzahlen(16000);
		
		System.out.println(sk.getSaldo());
	}
	
	/**
	 * 2.Bsp für: Polymorphie / adHoc-Polymorphie / Typanpassung / Explizite Typanpassung. <br>
	 * 
	 * Bei dieser Methode wird ein Sparkonto-Objekt sk erstellt, welches als Losungswortsparkonto initialisiert wird. 
	 * <br>
	 * Danach wird ein neues Losungswortsparkonto-Objekt lsk erstellt, welches das Sparkonto-Objekt auf ein 
	 * Losungswortsparkonto castet. Da jenes Sparkonto-Objekt als lsk-Objekt initialisiert wurde, wird lsk auch als
	 * Losungswortsparkonto angesehen und kann alle Methoden von jener Klasse verwenden. <br>
	 * Richtigerweise wird eine IllegalArgumentException geworfen, wenn man versucht, 16 000,- Euro einzuzahlen,
	 * da die Grenze beim Losungswortsparkonto wie im 1. Bsp beschrieben bei 15 000,- Euro liegt. <br>
	 */
	
	public static void explTypanpassung2() {
		Sparkonto sk = new Losungswortsparkonto(12345,5,"test");
		Losungswortsparkonto lsk = (Losungswortsparkonto) sk;
		lsk.einzahlen(16000);
		
		System.out.println(lsk.getSaldo());
	}
	
	/**
	 * 3.Bsp für: Polymorphie / adHoc-Polymorphie / Typanpassung / Explizite Typanpassung. <br>
	 * 
	 * Bei dieser Methode wird ein Sparkonto-Objekt sk1 erstellt, welches als Losungswortsparkonto initialisiert wird. 
	 * <br>
	 * Danach wird ein weiteres Sparkonto-Objekt sk2 erstellt, welches das als Losungswortsparkonto initialisierte
	 * Sparkonto-Objekt castet. Sowohl sk1, als auch sk2 werden jedoch als Losungswortsparkonten angesehen. <br>
	 * Richtigerweise wird eine IllegalArgumentException geworfen, wenn man versucht, 16 000,- Euro einzuzahlen,
	 * da die Grenze beim Losungswortsparkonto wie im 1. Bsp beschrieben bei 15 000,- Euro liegt. <br>
	 */
	
	public static void explTypanpassung3() {
		Sparkonto sk1 = new Losungswortsparkonto(12345,5,"test");
		Sparkonto sk2 = (Sparkonto) sk1;
		sk2.einzahlen(16000);
		
		System.out.println(sk2.getSaldo());
	}
	
	/**
	 * 1.Bsp für: Polymorphie / Universelle Polymorphie / Laufzeitpolymorphie. <br>
	 * 
	 * Laufzeitpolymorphie bedeutet, dass erst zur Laufzeit bestimmt wird, welche Implementierung für eine bestimmte 
	 * Methode aufzurufen ist. <br>
	 * Bei dieser Methode wird ein Konto-Objekt k erstellt, welches als Girokonto initialisiert wird. <br>
	 * Wenn dieses Objekt nun die Methode "abheben" ausführt, welche bei der Girokonto-Klasse überschrieben wird, wird 
	 * jene Methode aus der Girokonto-Klasse und nicht jene aus der Konto-Klasse ausgeführt. <br>
	 * Richtigerweise wird eine NotEnoughMoneyException geworfen, da der Ueberziehungsrahmen mit 500,- Euro
	 * definiert wurde und man 600,- Euro abheben wollte.
	*/
	
	public static void laufzeitpolymorphie1() throws IllegalArgumentException, NotEnoughMoneyException {
		Konto k = new Girokonto(123456,500);
		k.abheben(600);
	}
	
	/**
	 * 2.Bsp für: Polymorphie / Universelle Polymorphie / Laufzeitpolymorphie. <br>
	 * 
	 * Bei dieser Methode wird ein Konto-Objekt k erstellt, welches als Losungswortsparkonto initialisiert wird.
	 * Losungswortsparkonto erbt von Sparkonto, Sparkonto wiederum von Konto, d.h. hier handelt es sich um eine
	 * dreistufige Klassenhierarchie. <br>
	 * Wenn dieses Objekt nun die Methode "einzahlen" ausführt, welche bei der Losungswortsparkonto-Klasse 
	 * überschrieben wird, wird jene Methode aus der Losungswortsparkonto-Klasse und nicht jene aus der Konto-Klasse
	 * ausgeführt. <br>
	 * Richtigerweise wird eine IllegalArgumentException geworfen, wenn man versucht, 16 000,- Euro einzuzahlen,
	 * da die Grenze beim Losungswortsparkonto wie im 1. Bsp beschrieben bei 15 000,- Euro liegt. <br>
	 * 
	 */
	
	public static void laufzeitpolymorphie2() {
		Konto k = new Losungswortsparkonto(12345,5,"test");
		k.einzahlen(16000);
		
		System.out.println(k.getSaldo());
	}
	
	/**
	 * 1.Bsp für: Polymorphie / Universelle Polymorphie / Generizität. <br>
	 * 
	 * Unter Generizizät versteht man die Verwendung von Typparametern, welche an Stelle von expliziten Typen verwendet 
	 * werden. Diese Typparameter können später durch Typen ersetzt werden. <br>
	 * Bei dieser Methode wird beim Typparameter die Wrapper-Klasse String verwendet.
	 */
	
	public static void generizitaet1() {
		Wrapper<String> ws = new Wrapper<String>("Hallo Welt");
		System.out.println(ws.toString());
	}
	
	/**
	 * 2.Bsp für: Polymorphie / Universelle Polymorphie / Generizität. <br>
	 * 
	 * Bei dieser Methode wird beim Typparameter die Wrapper-Klasse Integer verwendet.
	 */
	
	public static void generizitaet2() {
		Wrapper<Integer> ws = new Wrapper<Integer>(12345);
		System.out.println(ws.toString());
	}
	
	/**
	 * 3.Bsp für: Polymorphie / Universelle Polymorphie / Generizität. <br>
	 * 
	 * Bei dieser Methode wird beim Typparameter die Wrapper-Klasse Double verwendet.
	 */
	
	public static void generizitaet3() {
		Wrapper<Double> ws = new Wrapper<Double>(500.5);
		System.out.println(ws.toString());
	}
	
	/**
	 * Testmethoden werden aufgerufen
	 */
	
	public static void main(String[] args) throws IllegalArgumentException, NotEnoughMoneyException {
		
		/* alle Methodenaufrufe, die eine geplante Exception werfen,  wurden auskommentiert */
		
		//explTypanpassung1();
		//explTypanpassung2();
		//explTypanpassung3();
		//laufzeitpolymorphie1();
		//laufzeitpolymorphie2();
		generizitaet1();
		generizitaet2();
		generizitaet3();
	}
	
}
