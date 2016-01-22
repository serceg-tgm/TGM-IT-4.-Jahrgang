<?php
/**
 * In der Main werden alle erstellten Klassen inkludiert und die jeweiligen Eingaben des Benutzers beim Ausfuehren
 * des Programms interpretiert. Bei falschen Eingaben werden Fehlermeldungen angezeigt und das Programm wird
 * beendet.
 *
 * Autor: Stefan Erceg
 * Version: 1.0
 * Datum: 30.11.2014
 */

// alle benoetigten Dateien werden inkludiert
include('Factory.php');
include('XMLFactory.php');
include('YamlFactory.php');
include('Element.php');
include('XMLAdapter.php');
include('XMLClassname.php');
include('XMLUser.php');
include('XMLPassword.php');
include('YamlAdapter.php');
include('YamlClassname.php');
include('YamlUser.php');
include('YamlPassword.php');

// der Benutzer wird aufgefordert, alle fuer den Datenbankverbindungsaufbau notwendigen Eingaben durchzufuehren
// diese Eingaben werden in Variablen abgespeichert

echo "Bitte geben Sie die Adresse des Datenbankservers ein!\n";
$dbserver = trim(fgets(STDIN));

echo "Bitte geben Sie den Namen des Users ein!\n";
$username = trim(fgets(STDIN));

echo "Bitte geben Sie das Passwort des Users ein!\n";
$password = trim(fgets(STDIN));

echo "Bitte geben Sie den Namen der Datenbank ein!\n";
$dbname = trim(fgets(STDIN));

// Verbindung zur Datenbank wird aufgebaut
$dbhandle = mysqli_connect($dbserver, $username, $password, $dbname);

// falls die Verbindung fehlgeschlagen ist, wird eine Fehlermeldung ausgegeben und das Programm wird beendet
if (!$dbhandle) {
	echo 'Es wurden falsche Daten fuer den Verbindungsaufbau zur Datenbank eingegeben!';
	exit;
} else {
    // bei einer erfolgreichen Verbindung wird der Benutzer gefragt, welche Elemente ausgegeben werden sollen
    // diese Eingabe wird ebenfalls in eine Variable gespeichert
    echo "Bitte geben Sie ein, welchen Elemente ausgegeben werden sollen
    (zur Auswahl stehen 'adapter', 'classname', 'user' und 'password')\n";
	echo "Wenn Sie alle Elemente ausgeben moechten, muessen Sie 'alle' eingeben.\n"; 
	$type = strtolower(trim(fgets(STDIN)));

    // je nach Eingabe wird die Abfrage in der Datenbank durchgefuehrt
    // existiert der eingegebene Typ nicht, wird eine Fehlermeldung geworfen und das Programm wird beendet
    switch($type) {

        case "alle":
            $query = "SELECT type, value FROM element;";
            break;

        case "adapter":
            $query = "SELECT value FROM element WHERE type='adapter';";
            break;

        case "classname":
            $query = "SELECT value FROM element WHERE type='classname';";
            break;

        case "user":
            $query = "SELECT value FROM element WHERE type='user';";
            break;

        case "password":
            $query = "SELECT value FROM element WHERE type='password';";
            break;

        default:
            echo 'Das eingegebene Element existiert nicht!';
            exit;

    }

    // der Benutzer wird gefragt, welche Factory (XML oder Yaml) verwendet werden soll
    // diese Eingabe wird ebenfalls in eine Variable gespeichert
	echo "Bitte geben Sie ein, welche Factory Sie verwenden moechten 
	(zur Auswahl stehen XML und Yaml)!\n";
	$language = strtolower(trim(fgets(STDIN)));

    // je nach Eingabe wird ein neues XMLFactory-Objekt oder ein neues YamlFactory-Objekt instanziert
    // existiert die eingegebene Factory nicht, wird eine Fehlermeldung geworfen und das Programm wird beendet
    switch($language) {

        case "xml":
            $factory = new XMLFactory();
            break;

        case "yaml":
            $factory = new YamlFactory();
            break;

        default:
            echo 'Die eingegebene Factory existiert nicht!';
            exit;

    }

	// das Ergebnis der Abfrage wird in eine Variable abgespeichert
	$result = mysqli_query($dbhandle, $query);
	
	// wenn die Abfrage nicht ausgefuehrt werden konnte, wird eine Fehlermeldung ausgegeben
	if (!$result) {
		echo 'MySQL Error: ' . mysqli_error($dbhandle) . "<br>\n";
	// ansonsten werden die Datensaetze als assoziatives Array geliefert
	} else {
		while ($row = mysqli_fetch_assoc($result)) {
            /* falls alle Elemente angefordert wurden, muss der Typ des Elements ebenfalls bei jedem Durchlauf
                mitgeliefert werden */
			if($type === 'alle') {
				$element = $factory->createElement($row['type'], $row['value']);
			} else {
				$element = $factory->createElement($type, $row['value']);
			}
            // getString()-Methode wird aufgerufen
            echo $element->getString()."\n";
		}
	}
	
	// Verbindung zum Datenbankserver wird beendet
	mysqli_close($dbhandle);
}
?>