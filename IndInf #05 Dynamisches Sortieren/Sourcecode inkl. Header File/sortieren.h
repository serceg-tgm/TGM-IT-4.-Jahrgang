/*
Name of file	: sortieren.h
Author			: Stefan Erceg <stefan.erceg@student.tgm.ac.at>
Since			: 20141024
Version			: 20141103
Description		: Die benoetigten c-Dateien, stdio.h (Standard-I/O-Funktionen), stdlib.h (Allgemeine Hilfsfunktionen)
				  und time.h (Datums- und Zeitfunktionen) werden inkludiert.
 */

#ifndef SORTIEREN_H

#define SORTIEREN_H

/* Includes */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <time.h>

/* in der struct-Variable befindet sich ein int-Pointer, bei dem die Zahlen in ein Array gefuellt werden und
 * Functionpointer, die in der main-Funktion benoetigt werden */

struct zahlen {

	int* array;

	void (*sort) (int*, int);
	int* (*zufallszahlen) (int*, int);
	void (*output) (int*, int);

};

/* Prototypen fuer Bubblesort.c */

void bubblesort(int*, int);

/* Prototypen fuer Mergesort.c */

void mergesort(int*, int);

/* Prototypen fuer Quicksort.c */

void quicksort(int*, int);

/* Prototypen fuer Verarbeitung.c */

int* zufallszahlen(int*, int);
void output(int*, int, int);

/* Prototypen fuer Main.c */
int main();

#endif /* SORTIEREN_H */
