/*
Name of file	: Verarbeitung.c
Author			: Stefan Erceg <stefan.erceg@student.tgm.ac.at>
Since			: 20141024
Version			: 20141103
Description		: In dieser Datei werden 2 Methoden implementiert.

				  Die eine, "zufallszahlen", generiert eine vom Benutzer vorgegebene Anzahl an Zufallszahlen.
				  Die andere, "output", gibt alle Zahlen, die im Array enthalten sind, unsortiert oder sortiert
				  aus.
*/

/* Includes */

#include "sortieren.h"

/* Die Funktion "zufallszahlen" generiert eine bestimmte Anzahl an Zufallszahlen und gibt diese als int-Array
 * zurueck. Die Liste der Zahlen und die Anzahl der zu generierenden Zufallszahlen werden im Parameter uebergeben. */

int* zufallszahlen(int* numbers, int length) {

	srand( (unsigned) time(NULL) );

	for (int i = 0; i < length; i++)	{
		numbers[i] = rand()%1234+1;
	}

	return numbers;

}

/* Die Funktion "output" gibt alle Zahlen, die im Array enthalten sind, aus. Die Liste der Zahlen, die Anzahl
 * der Zufallszahlen und die Anzahl der Zahlen pro Zeile werden im Parameter uebergeben. */

void output(int* numbers, int length, int numbersPerRow) {

	// alle Zahlen im Array werden durchgegangen
	for (int i = 0; i < length; i++) {

		/* je nach Angabe der Zahlen pro Zeile des Benutzers wird nach einer bestimmter Zeit ein Zeilenumbruch
		 * gemacht */
		if ((i+1) % numbersPerRow == 0) {
			printf("%d \n", numbers[i]);
		} else {
			printf("%d, \t ", numbers[i]);
		}

	}

}
