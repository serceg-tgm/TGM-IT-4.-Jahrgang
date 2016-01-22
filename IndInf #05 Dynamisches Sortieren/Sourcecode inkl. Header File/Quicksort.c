/*
Name of file	: Quicksort.c
Author			: Stefan Erceg <stefan.erceg@student.tgm.ac.at>
Since			: 20141024
Version			: 20141103
Description		: Quicksort wird implementiert. Beim Quicksort wird die Liste in zwei Teillisten unterteilt. Alle
				  Elemente, die kleiner sind als das Pivot-Element, werden in die linke Teilliste, alle groeßeren
				  Elemente in die rechte Teilliste gegeben.
				  Quicksort zaehlt zu den schnellsten Sortieralgorithmen.
*/

/* Includes */

#include "sortieren.h"

/*
 * Die Funktion fuer den Quicksort wurde von folgender Seite uebernommen:
 * 	http://rosettacode.org/wiki/Sorting_algorithms/Quicksort#C [abgerufen am 24.10.2014 - 8 Uhr]
 *
 * Die Methode wurde folgendermaßen geaendert:
 * 	-> der Parameter "a", welcher ein int-Pointer ist, wurde auf "list" umbenannt
 * 	-> der Parameter "n", welcher ein int ist, wurde auf "length" umbenannt
 *
 * 	Als Parameter werden die zu sortierende Liste und dessen Laenge uebergeben.
 */

void quicksort (int* list, int length) {
    if (length < 2)
        return;
    int p = list[length / 2];
    int *l = list;
    int *r = list + length - 1;
    while (l <= r) {
        if (*l < p) {
            l++;
        }
        else if (*r > p) {
            r--;
        }
        else {
            int t = *l;
            *l = *r;
            *r = t;
            l++;
            r--;
        }
    }
    quicksort(list, r - list + 1);
    quicksort(l, list + length - l);
}
