<?php
/**
 * Yaml-Adapter implementiert das Interface Element. Der im Parameter vom Konstruktor uebergebene Wert wird
 * verarbeitet und mittels der Methode "getString()" in richtiger Form zurueckgegeben.
 *
 * Autor: Stefan Erceg
 * Version: 1.0
 * Datum: 30.11.2014
 */

class YamlAdapter implements Element {
	
	private $value;

    /**
     * Im Konstruktor wird dem deklarierten Attribut "value" der im Parameter uebergebene Wert zugewiesen. Ausserdem
     * wird die Methode "getString()" ausgefuehrt.
     * @param $value Wert des Elements
     */

	public function __construct($value) {
		$this->value = $value;
        $this->getString();
	}

    /**
     * Die Adapter-Elemente werden in Yaml-Form zurueckgegeben.
     * @return string Adapter-Elemente in Yaml-Form
     */

	public function getString() {
		return 'adapter: '.$this->value;
	}
} 