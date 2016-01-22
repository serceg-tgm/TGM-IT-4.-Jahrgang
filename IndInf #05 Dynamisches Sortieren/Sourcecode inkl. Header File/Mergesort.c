/*
Name of file	: Mergesort.c
Author			: Stefan Erceg <stefan.erceg@student.tgm.ac.at>
Since			: 20141024
Version			: 20141103
Description		: Mergesort wird implementiert. Beim Mergesort werden die Zahlen in einer Liste betrachtet und in
				  kleinere Listen zerlegt, die dann jeweils fuer sich sortiert werden. Die sortierten kleinen Listen
				  werden danach wieder zu einer Gesamtlist hinzugefuegt.
*/

/* Includes */

#include "sortieren.h"

/*
 * Die Funktion fuer den Mergesort wurde von folgender Seite uebernommen:
 * 	http://de.wikibooks.org/wiki/Algorithmen_und_Datenstrukturen_in_C/_Mergesort [abgerufen am 24.10.2014 - 8 Uhr]
 *
 * Die Methode wurde folgendermaßen geaendert:
 * 	-> der Parameter "list", welcher ein int-Array ist, wurde auf einen int-Pointer geandert und auf "list"
 * 		umbenannt
 * 	-> der Parameter "length", welcher ein int-Pointer ist, wurde auf "length" umbenannt
 * 	-> die lokalen Variablen "half1" und "half2" wurden auf "half1" und "half2" umbenannt
 *
 * Als Parameter werden die zu sortierende Liste und dessen Laenge uebergeben.
*/

void mergesort(int* list, int length) {

     if(length > 1){

         int half1[length/2];
         int half2[(length + 1)/2];
         int i;
         for(i = 0; i < length/2; ++i)
             half1[i] = list[i];
         for(i = length/2; i < length; ++i)
             half2[i - length/2] = list[i];

         mergesort(half1,length/2);
         mergesort(half2,(length + 1)/2);

         int *pos1 = &half1[0];
         int *pos2 = &half2[0];
         for(i = 0; i < length; ++i){
             if(*pos1 <= *pos2){
                 list[i] = *pos1;
                 if (pos1 != &half2[(length+1)/2 - 1]) {
                     if(pos1 == &half1[length/2 - 1]){
                         pos1 = &half2[(length+1)/2 - 1];
                     }
                     else{
                         ++pos1;
                     }
                 }
             }
             else{
                 list[i] = *pos2;
                 if(pos2 == &half2[(length + 1)/2 - 1]){
                     pos2 = &half1[length/2 - 1];
                 }
                 else{
                     ++pos2;
                 }
             }
         }
     }
}
