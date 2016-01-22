<?php
/**
 * Yaml-Factory implementiert das Interface Factory. Mittels des im Parameter uebergebenen Typs des Elements
 * wird ein neues Objekt dieses Typs instanziert und zurueckgegeben.
 *
 * Autor: Stefan Erceg
 * Version: 1.0
 * Datum: 30.11.2014
 */

class YamlFactory implements Factory {

    /**
     * Ein neues Objekt wird abhaengig vom Typ des Elements instanziert und zurueckgegeben.
     * @param $type Typ des Elements
     * @param $value Wert des Elements
     * @return YamlAdapter|YamlClassname|YamlPassword|YamlUser neues Objekt des jeweiligen Typs
     */

    public function createElement($type, $value) {

        /* der im Parameter uebergebene Typ des Elements wird in Kleinbuchstaben angesehen und mit bestimmten Typen
            von Elementen verglichen */
        if(strtolower($type) === "adapter") {
            return new YamlAdapter($value);
        } else if(strtolower($type) === "classname") {
            return new YamlClassname($value);
        } else if(strtolower($type) === "user") {
            return new YamlUser($value);
        } else if(strtolower($type) === "password") {
            return new YamlPassword($value);
        }

    }

}