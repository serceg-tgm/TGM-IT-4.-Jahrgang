/*
Name of file	: Mergesort_Erceg.c
Author			: Stefan Erceg <stefan.erceg@student.tgm.ac.at>
Version1		: 20141014
Version2		: 20141022
Description		: Es wird ein Programm geschrieben, welches den Sortieralgorithmus "Mergesort" implementiert. Bei
				  der Implementierung werden Pointer verwendet.
				  Als Input wird ein randomized gefuelltes Array in einem Struct verwendet. Die Ausgabe des
				  unsortierten und dann sortierten Arrays erfolgt mittels einer eigenen Funktion, die ueber einen
				  Functionpointer aus der Struct erreichbar ist (Uebergabe mittels call-by-reference).
*/

/* Includes */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

/* Definition der Arraygroeﬂe */

#define MAX 10

/* Prototypen */

struct randomizedArray;
int* zufallszahlen(int* zahlen);
void mergesort(int liste[], int groesse);
void output(int* zahlen);

/* eine Struct, welche ein int-Array und zwei Functionpointer beinhaltet, wird erstellt */

struct randomizedArray {

	// int-Array (Arraygroeﬂe = 10)
	int zahlen[MAX];

	// Functionpointer fuer die Funktionen "mergesort" und "output" werden erzeugt
	void (*sort) (int*, int);
	void (*output) (int*);

};

/* die Funktion "zufallszahlen" generiert 10 Zufallszahlen und gibt diese als int-Array zurueck */

int* zufallszahlen(int* zahlen) {

	srand( (unsigned) time(NULL) );

	for (int i = 0; i < MAX; i++)	{
		zahlen[i] = rand()%1234+1;
	}

	return zahlen;

}

/*
 * Die Funktion fuer den Mergesort wurde von folgender Seite uebernommen:
 * 	http://de.wikibooks.org/wiki/Algorithmen_und_Datenstrukturen_in_C/_Mergesort [abgerufen am 22.10.2014 - 22 Uhr]
 *
 * Beim Mergesort werden die Zahlen in einer Liste betrachtet und in kleinere Listen zerlegt, die dann jeweils
 * fuer sich sortiert werden. Die sortierten kleinen Listen werden danach wieder zu einer Gesamtliste hinzugefuegt.
 *
 * Als Parameter werden das zu sortierende Array und dessen Laenge uebergeben.
 *
*/

void mergesort(int liste[], int groesse) {

     if(groesse > 1){

         int haelfte1[groesse/2];
         int haelfte2[(groesse + 1)/2];
         int i;
         for(i = 0; i < groesse/2; ++i)
             haelfte1[i] = liste[i];
         for(i = groesse/2; i < groesse; ++i)
             haelfte2[i - groesse/2] = liste[i];

         mergesort(haelfte1,groesse/2);
         mergesort(haelfte2,(groesse + 1)/2);

         int *pos1 = &haelfte1[0];
         int *pos2 = &haelfte2[0];
         for(i = 0; i < groesse; ++i){
             if(*pos1 <= *pos2){
                 liste[i] = *pos1;
                 // pos1 nicht veraendern, wenn der grˆﬂte Wert mehrmals vorkommt
                 if (pos1 != &haelfte2[(groesse+1)/2 - 1]) {
                     if(pos1 == &haelfte1[groesse/2 - 1]){
                         pos1 = &haelfte2[(groesse+1)/2 - 1];
                     }
                     else{
                         ++pos1;
                     }
                 }
             }
             else{
                 liste[i] = *pos2;
                 if(pos2 == &haelfte2[(groesse + 1)/2 - 1]){
                     pos2 = &haelfte1[groesse/2 - 1];
                 }
                 else{
                     ++pos2;
                 }
             }
         }
     }
}

/* die Funktion "output" gibt alle Zahlen, die im Array enthalten sind, aus */

void output(int* zahlen) {

	// alle Zahlen im Array werden durchgegangen
	for (int i = 0; i < MAX; i++)	{
		// falls man nicht beim letzten Element des Arrays gelangt ist, werden die Zahlen durch Beistriche getrennt
		if (i == MAX-1) {
			printf("%d", zahlen[i]);
		} else {
			printf("%d, \t ", zahlen[i]);
		}
	}
	printf("\n");

}

/* in der "main" wird eine struct-Variable erstellt und Zahlen unsortiert und sortiert ausgegeben  */

int main(int argc, char** argv) {

	// struct-Variable
	struct randomizedArray ra;

	/* Zufallszahlen werden in das int-Array, welches im Struct enthalten ist, eingetragen und einem int-Pointer
		zugewiesen */
	int* generierteZahlen = zufallszahlen(ra.zahlen);

	// Zahlen werden unsortiert ausgegeben
	printf("Zahlen in unsortierter Reihenfolge:	");
	output(ra.zahlen);

	// Functionpointer zeigt auf die Funktion "mergesort"
	ra.sort = mergesort;

	// Zahlen werden mittels Mergesort sortiert
	mergesort(generierteZahlen, MAX);

	// Zahlen werden sortiert ausgegeben
	printf("Zahlen in sortierter Reihenfolge:	");
	output(ra.zahlen);

	return EXIT_SUCCESS;

}
