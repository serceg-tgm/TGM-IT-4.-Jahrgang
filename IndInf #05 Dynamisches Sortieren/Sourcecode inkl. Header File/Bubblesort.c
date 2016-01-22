/*
Name of file	: Bubblesort.c
Author			: Stefan Erceg <stefan.erceg@student.tgm.ac.at>
Since			: 20141024
Version			: 20141103
Description		: Bubblesort wird implementiert. Beim Bubblesort werden immer zwei nebeneinander liegende Elemente
				  verglichen und dann vertauscht, falls das rechte kleiner ist als das linke.
*/

/* Includes */

#include "sortieren.h"

/*
 * Die Funktion fuer den Bubblesort wurde von folgender Seite uebernommen:
 * 	http://de.wikibooks.org/wiki/Algorithmen_und_Datenstrukturen_in_C/_Bubblesort [abgerufen am 24.10.2014 - 8 Uhr]
 *
 * Die Methode wurde folgendermaßen geaendert:
 * 	-> der Parameter "array", welcher ein int-Pointer ist, wurde auf "list" umbenannt
 *
 * 	Als Parameter werden die zu sortierende Liste und dessen Laenge uebergeben.
 */

void bubblesort(int* list, int length) {
     int i, j;
     for (i = 0; i < length -1; ++i) {
    	 for (j = 0; j < length - i - 1; ++j) {
    		 if (list[j] > list[j + 1]) {
    			 int tmp = list[j];
    			 list[j] = list[j + 1];
    			 list[j + 1] = tmp;
    		 }
    	 }
     }
 }
