/*
Name of file	: Main.c
Author			: Stefan Erceg <stefan.erceg@student.tgm.ac.at>
Since			: 20141024
Version			: 20141103
Description		: In der Main wird ein CLI-Menue erstellt, welches dem Benutzer ermoeglicht, die Anzahl der zu
				  sortierenden Elemente einzugeben und den gewuenschten Sortieralgorithmus auszuwaehlen.

				  Es werden ebenfalls Abfragen erstellt, bei denen der Benutzer gefragt wird, ob er den unsortierten
				  und/oder den sortierten Zahlenvektor angezeigt haben moechte. Wenn mit "y" (yes) geantwortet
				  wird, kann der Benutzer entscheiden, wie viele Zahlen er pro Zeile angezeigt haben moechte.

				  Die Ausgabe der benoetigen Zeit der Sortierung ist ebenfalls Bestandteil des Programms.

				  Das Programm kann danach durch die Eingabe von q beendet werden.
*/

/* Includes */

#include "sortieren.h"

int main() {

	setvbuf(stdout, NULL, _IONBF, 0);

	// Benutzer wird aufgefordert, die Anzahl der zu generierenden Zufallszahlen einzugeben

	char checkZufallszahlen[16];

	printf("Bitte geben Sie an, wie viele Zufallszahlen generiert werden sollen: ");
	scanf("%16s", &checkZufallszahlen[0]);

	// es wird ueberprueft, ob eine gueltige Eingabe fuer die Anzahl der Zufallszahlen durchgefuehrt wurde

	for (int i = 0; i < checkZufallszahlen[i]; i++) {

		// "isdigit" ueberprueft, ob ein bestimmter Char die Ziffer 0-9 enthaelt
		// wenn dies nicht so ist, wird 0 returnt und das Programm beendet
		if (isdigit(checkZufallszahlen[i]) == 0) {

			printf("Es wurde keine gueltige Zahl eingegeben.");
			return EXIT_FAILURE;

		}

	}

	// der int-Variable wird der eingegebene Wert, welcher in dem String vorhanden ist, zugewiesen

	int anzZufallszahlen;
	sscanf(checkZufallszahlen, "%d", &anzZufallszahlen);

	// bei der Anzahl der Zufallszahlen muss mind. 2 eingegeben werden, ansonsten gibt es nichts zu sortieren

	if (anzZufallszahlen <= 1) {

		printf("Fuer die generierten Zufallszahlen muss eine Zahl groeßer 1 eingegeben werden!");
		return EXIT_FAILURE;

	}

	// Zufallszahlen werden generiert; dazu wird vorher eine struct erstellt
	// die Groeße des Arrays wird dynamisch verwaltet (Verwendung von malloc!)

	struct zahlen z;
	z.array = malloc(anzZufallszahlen * sizeof(int));

	// die Zufallszahlen werden dem int-Pointer, welcher in der Struct enthalten ist, zugewiesen
	int* generierteZahlen = zufallszahlen(z.array, anzZufallszahlen);

	// der Benutzer wird gefragt, ob er die Zahlen unsortiert ausgeben moechte

	char ausgabeUnsortiert[2];

	printf("Sollen die Zahlen in unsortierter Reihenfolge ausgegeben werden (y/n)? ");
	scanf("%2s", &ausgabeUnsortiert[0]);

	char checkZahlenProZeile[4];
	int anzZahlenProZeile;

	/* Wenn "y" (yes) eingegeben wurde, wird der Benutzer zusaetzlich noch gefragt, wie viele Zahlen er pro
		Zeile angezeigt haben moechte. */

	if(strcmp(ausgabeUnsortiert,"y") == 0) {

		printf("Wie viele Zahlen sollen pro Zeile dargestellt werden? Max. 100 sind moeglich. ");
		scanf("%4s", &checkZahlenProZeile[0]);

		// Ueberpruefung von ungueltigen Eingaben

		for (int i = 0; i < checkZahlenProZeile[i]; i++) {

			if (isdigit(checkZahlenProZeile[i]) == 0) {

				printf("Es wurde keine gueltige Zahl eingegeben.");
				return EXIT_FAILURE;

			}

		}

		sscanf(checkZahlenProZeile, "%d", &anzZahlenProZeile);

		// die Anzahl der Zahlen pro Zeile darf max. 100 betragen und muss logischerweise größer gleich 1 sein
		// die Anzahl der Zahlen pro Zeile darf ebenfalls nicht größer als die gesamte Anzahl der Zufallszahlen sein

		if((anzZahlenProZeile >= 1) && (anzZahlenProZeile <= 100)
				&& (anzZufallszahlen >= anzZahlenProZeile)) {

			output(generierteZahlen, anzZufallszahlen, anzZahlenProZeile);

		} else {

			printf("Es muss eine Zahl groeßer 0 und kleiner 100 eingegeben werden bzw. die Anzahl der "
					"Zufallszahlen muss größer gleich der Anzahl der Zahlen pro Zeile sein!");
			return EXIT_FAILURE;

		}

	}

	// Benutzer wird aufgefordert, den gewuenschten Sortieralgorithmus einzugeben (zur Auswahl stehen 3)

	char checkAlgorithm[3];

	printf("Bitte geben Sie den gewuenschten Sortieralgorithmus ein "
			"(bs fuer Bubblesort, ms fuer Mergesort und qs fuer Quicksort): ");
	scanf("%3s", &checkAlgorithm[0]);

	// dazwischen wird die benoetigte Zeit fuer die Sortierung gemessen

	clock_t uptime;
	clock_t diff;

	// der Functionpointer "sort" der Struct wird der jeweiligen Methode des Sortieralgorithmus zugewiesen

	if (strcmp(checkAlgorithm, "bs") == 0) {

		z.sort = &bubblesort;

		uptime = clock();

		bubblesort(z.array, anzZufallszahlen);

		// Differenz von Beginn und Ende der Sortierung wird berechnet
		diff = clock() - uptime;

	} else if (strcmp(checkAlgorithm, "ms") == 0) {

		z.sort = &mergesort;

		uptime = clock();

		mergesort(z.array, anzZufallszahlen);

		diff = clock() - uptime;

	} else if (strcmp(checkAlgorithm, "qs") == 0) {

		z.sort = &quicksort;

		uptime = clock();

		quicksort(z.array, anzZufallszahlen);

		diff = clock() - uptime;

	/* falls keine von den oben genannten Abkuerzungen fuer die Sortieralgorithmen eingegeben wurde,
		wird eine Fehlermeldung ausgegeben und das Programm beendet */

	} else {

		printf("Es wurde kein gueltiger Sortieralgorithmus angegeben!");
		return EXIT_FAILURE;

	}

	// der Benutzer wird gefragt, ob er die Zahlen sortiert ausgeben moechte

	char ausgabeSortiert[2];

	printf("Sollen die Zahlen in sortierter Reihenfolge ausgegeben werden (y/n)? ");
	scanf("%2s, \n", &ausgabeSortiert[0]);

	char checkZahlenProZeileSortiert[4];
	int anzZahlenProZeileSortiert;

	/* Wenn "y" (yes) eingegeben wurde, wird der Benutzer zusaetzlich noch gefragt, wie viele Zahlen er pro
		Zeile angezeigt haben moechte. */

	if(strcmp(ausgabeSortiert,"y") == 0) {

		printf("Wie viele Zahlen sollen pro Zeile dargestellt werden? Max. 100 sind moeglich. ");
		scanf("%4s", &checkZahlenProZeileSortiert[0]);

		// Ueberpruefung von ungueltigen Eingaben

		for (int i = 0; i < checkZahlenProZeileSortiert[i]; i++) {

			if (isdigit(checkZahlenProZeileSortiert[i]) == 0) {

				printf("Es wurde keine gueltige Zahl eingegeben.");
				return EXIT_FAILURE;

			}

		}

		sscanf(checkZahlenProZeileSortiert, "%d", &anzZahlenProZeileSortiert);

		if((anzZahlenProZeileSortiert >= 1) && (anzZahlenProZeileSortiert <= 100)
				&& (anzZufallszahlen >= anzZahlenProZeileSortiert)) {

			output(generierteZahlen, anzZufallszahlen, anzZahlenProZeileSortiert);

		} else {

			printf("Es muss eine Zahl groeßer 0 und kleiner 100 eingegeben werden bzw. die Anzahl der "
					"Zufallszahlen muss größer gleich der Anzahl der Zahlen pro Zeile sein!");
			return EXIT_FAILURE;

		}

	}

	// Ausgabe der benoetigten Zeit vom ausgewaehlten Sortieralgorithmus
	printf("Benoetigte Zeit des ausgewaehlten Sortieralgorithmus (Angabe in Sekunden): "
			"%f\n", ((float) diff) / CLOCKS_PER_SEC);

	// Speicher wird erneut freigegeben
	free(z.array);

	// falls "q" eingegeben wird, wird das Programm beendet

	char checkExit[2];
	printf("Vielen Dank fuer die Verwendung des Programms! Das Programm kann nun mit q beendet werden.\n");
	scanf("%2s", &checkExit[0]);

	if (strcmp(checkExit,"q")) {

		return EXIT_SUCCESS;

	}

}
