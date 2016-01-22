<?php
/**
 * Dieses Interface bietet eine Methode an, bei welcher das Element (der Typ des Elements und dessen Wert) im Parameter
 * uebergeben wird.
 *
 * Autor: Stefan Erceg
 * Version: 1.0
 * Datum: 30.11.2014
 */

interface Factory {
    public function createElement($type, $value);
} 