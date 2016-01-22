/*
Name of file	: Datentypen_Erceg.c
Author			: Stefan Erceg <stefan.erceg@student.tgm.ac.at>
Version1		: 20141006
Version2		: 20141012
Description		: Es wird ein Taschenrechner implementiert, der mit umgekehrter polnischer Notation die
				  Addition und Subtraktion erlaubt. Der User soll ohne Einschraenkung Werte eingeben koennen,
				  die entsprechend behandelt werden. Es sollen dabei nur zwei Funktionen benutzt werden, die
				  das Addieren und Subtrahieren durchfuehren.
				  Geachtet wird dabei auf die korrekte Eingabe und der Benutzer wird bei etwaigen Problemen
				  informiert. Die Verwendung von void* ist dabei Pflicht.
*/

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <string.h>
#include <ctype.h>
#include <float.h>

void* addieren(char* typ, void* zahl1, void* zahl2);
void* subtrahieren(char* typ, void* zahl1, void* zahl2);

int main(int argc, char** argv) {

	/* Hiermit wird der Buffer der Konsole auf 0 gesetzt (damit Eclipse die Ausgabe in der Konsole in der
	   richtigen Reihenfolge anzeigt, muss dies am Anfang der main ausgefuehrt werden). */

	setvbuf(stdout, NULL, _IONBF, 0);

	// die 2 Zahlenvariablen werden mit dem Datentyp void* und die Variable wird mit dem Datentyp char definiert
	// mittels void* koennen fuer die Zahlen alle Basisdatentypen verwendet werden

	char zahl1[30];
	char zahl2[30];
	char op[2];

	// der Benutzer wird auf den gueltigen Wertebereich hingewiesen (groeﬂter verwendeter Datentyp = long long)
	// er soll ebenfalls keine Leerzeichen oder sonstige Zeichen eingeben

	printf("Gueltiger Wertebereich: ñ9,223,372,036,854,775,808 bis 9,223,372,036,854,775,807\n");
	printf("Bitte geben Sie zwischen der jeweiligen Zahl keine Leerzeichen oder sonstige Zeichen ein.\n");

	// der Benutzer wird aufgefordert, 2 Zahlen und den gewuenschten Operator einzugeben

	printf("Bitte geben Sie die erste Zahl ein: ");
	scanf("%30s", &zahl1[0]);

	printf("Bitte geben Sie die zweite Zahl ein: ");
	scanf("%30s", &zahl2[0]);

	printf("Bitte geben Sie den gewuenschten Operator ein: ");
	scanf("%2s", &op[0]);

	// es wird ueberprueft, ob gueltige Eingaben fuer die Zahlen durchgefuehrt wurden

	for (int i=0; i < zahl1[i]; i++) {
		// "isdigit" ueberprueft, ob ein bestimmter Char die Ziffer 0-9 enthaelt
		// wenn dies nicht so ist, wird 0 returnt
		if (isdigit(zahl1[i]) == 0) {
			/* mittels "strncmp" kann angegeben werden, welche Stelle des String verglichen werden soll
			 * (in diesem Fall ist dies die erste Stelle) */
			/* mittels "strstr" wird nach einem bestimmten Zeichen in einem String gesucht
			 * (in diesem Fall nach einem Punkt) */
			/* mittels "strlen" wird die Laenge von der Zahl ueberprueft -> wenn diese bei der Eingabe von einem
			 * '+', '-' oder '.' bei der Zahl kleiner 2 ergibt, wird ebenfalls eine Fehlermeldung geworfen, da in
			 * diesem Fall noch mind. eine Ziffer fehlt */
			if(((strncmp(zahl1,"+",1) != 0) && (strncmp(zahl1,"-",1) != 0) && (strstr(zahl1, ".") == NULL))
				|| ((strlen(zahl1)) < 2)) {
					printf("Es wurde keine gueltige Eingabe fuer die 1. Zahl ausgefuehrt.");
					return EXIT_FAILURE;
			}
		}
	}

	for (int i=0; i < zahl2[i]; i++) {
		if (isdigit(zahl2[i]) == 0) {
			if(((strncmp(zahl2,"+",1) != 0) && (strncmp(zahl2,"-",1) != 0) && (strstr(zahl2, ".") == NULL))
				|| ((strlen(zahl2)) < 2)) {
					printf("Es wurde keine gueltige Eingabe fuer die 2. Zahl ausgefuehrt.");
					return EXIT_FAILURE;
			}
		}
	}

	// es wird ueberprueft, ob als Operator "+" oder "-" eingegeben worden ist
	/* "strcmp" returnt bei einer Uebereinstimmung der Strings 0 und bei keiner entweder groeﬂer oder kleiner 0,
	 * daher muss der Vergleich folgendermaﬂen durchgefuehrt werden */

	if ((strcmp(op, "+") != 0) && (strcmp(op, "-")) != 0) {

		printf("Ungueltige Eingabe! Als Operatoren koennen nur + oder - verwendet werden.");
		return EXIT_FAILURE;

	} else {

		/* falls bei mind. einer der beiden Zahlen ein '.' vorkommt, werden die Zahlen als Flieﬂkomma-Datentypen
		 * interpretiert */

		if (strstr(zahl1, ".") != NULL || strstr(zahl2, ".") != NULL) {

			/* "strtod" konvertiert einen String in einen Double-Datentyp
			 * [Parameteruebergabe: Zahl und Endpointer (falls man diesen nicht nutzen will, setzt man einen
			 * NULL-Pointer)] */

			/* wenn eine der beiden eingegebenen Zahlen entweder groeﬂer als der groeﬂte Float-Wert
			 * oder kleiner als der kleinste Float-Wert ist, werden die jeweiligen Zahlen als Double-Datentypen
			 * interpretiert */

			if (strtod(zahl1, NULL) >= FLT_MAX || strtod(zahl2, NULL) >= FLT_MAX
				|| strtod(zahl1, NULL) <= FLT_MIN || strtod(zahl2, NULL) <= FLT_MIN) {

				double z1;
				double z2;

				z1 = strtod(zahl1, NULL);
				z2 = strtod(zahl2, NULL);

				// void-Pointer wird in ein double-Pointer konvertiert

				if (strcmp(op, "+") == 0) {
					printf("%lf", *(double *) addieren("double", &z1, &z2));
				} else if (strcmp(op, "-") == 0) {
					printf("%lf", *(double *) subtrahieren("double", &z1, &z2));
				}

			/* trifft der oben genannte Fall nicht zu, werden die Zahlen als float-Datentypen
			 * interpretiert */

			} else {

				float z1;
				float z2;

				// "strtof" konvertiert einen String in einen Float-Datentyp

				z1 = strtof(zahl1, NULL);
				z2 = strtof(zahl2, NULL);

				// void-Pointer wird in ein float-Pointer konvertiert

				if (strcmp(op, "+") == 0) {
					printf("%f", *(float *) addieren("float", &z1, &z2));
				} else if (strcmp(op, "-") == 0) {
					printf("%f", *(float *) subtrahieren("float", &z1, &z2));
				}

			}

		} else {

			/* "strtoll" konvertiert einen String in einen Long-Long-Datentyp
			 * [Parameteruebergabe: Zahl, Endpointer (falls man diesen nicht nutzen will, setzt man einen
			 * NULL-Pointer und die Base (wenn diese 0 ist, wird die Base vom Format bestimmt)] */

			/* wenn eine der beiden eingegebenen Zahlen entweder groeﬂer als der groeﬂte Long-Wert
			 * oder kleiner als der kleinste Long-Wert ist, werden die jeweiligen Zahlen als long long-Datentypen
			 * interpretiert */

			if (strtoll(zahl1, NULL, 0) >= LONG_MAX || strtoll(zahl2, NULL, 0) >= LONG_MAX
				|| strtoll(zahl1, NULL, 0) <= LONG_MIN|| strtoll(zahl2, NULL, 0) <= LONG_MIN) {

				long long z1;
				long long z2;

				z1 = strtoll(zahl1, NULL, 0);
				z2 = strtoll(zahl2, NULL, 0);

				// void-Pointer wird in ein long long-Pointer konvertiert

				if (strcmp(op, "+") == 0) {
					printf("%lld", *(long long *) addieren("longlong", &z1, &z2));
				} else if (strcmp(op, "-") == 0) {
					printf("%lld", *(long long *) subtrahieren("longlong", &z1, &z2));
				}

			/* wenn eine der beiden eingegebenen Zahlen entweder groeﬂer als der groeﬂte int-Wert
			 * oder kleiner als der kleinste int-Wert ist, werden die jeweiligen Zahlen als long-Datentypen
			 * interpretiert */

			} else if (strtol(zahl1, NULL, 0) >= INT_MAX || strtol(zahl2, NULL, 0) >= INT_MAX
						|| strtoll(zahl1, NULL, 0) <= INT_MIN || strtoll(zahl2, NULL, 0) <= INT_MIN) {

				long z1;
				long z2;

				// "strtol" konvertiert einen String in einen Long-Datentyp

				z1 = strtol(zahl1, NULL, 0);
				z2 = strtol(zahl2, NULL, 0);

				// void-Pointer wird in ein long-Pointer konvertiert

				if (strcmp(op, "+") == 0) {
					printf("%ld", *(long *) addieren("long", &z1, &z2));
				} else if (strcmp(op, "-") == 0) {
					printf("%ld", *(long *) subtrahieren("long", &z1, &z2));
				}

			/* wenn eine der beiden eingegebenen Zahlen entweder groeﬂer als der groeﬂte short-Wert
			 * oder kleiner als der kleinste short-Wert ist, werden die jeweiligen Zahlen als int-Datentypen
			 * interpretiert */

			} else if (strtol(zahl1, NULL, 0) >= SHRT_MAX || strtol(zahl2, NULL, 0) >= SHRT_MAX
					|| strtoll(zahl1, NULL, 0) <= SHRT_MIN || strtoll(zahl2, NULL, 0) <= SHRT_MIN) {

				int z1;
				int z2;

				// "atoi" konvertiert einen String in einen Int-Datentyp

				z1 = atoi(zahl1);
				z2 = atoi(zahl2);

				// void-Pointer wird in ein int-Pointer konvertiert

				if (strcmp(op, "+") == 0) {
					printf("%d", *(int *) addieren("int", &z1, &z2));
				} else if (strcmp(op, "-") == 0) {
					printf("%d", *(int *) subtrahieren("int", &z1, &z2));
				}

			/* wenn keines der oben genannten Faelle zutrifft, werden die Zahlen als short-Datentypen
			 * interpretiert */

			} else {

				short z1;
				short z2;

				/* zuerst wird der String in einen Int-Datentyp konvertiert, zusaetZlich wird noch nach
				 * short gecastet */

				z1 = (short)atoi(zahl1);
				z2 = (short)atoi(zahl2);

				// void-Pointer wird in ein short-Pointer konvertiert

				if (strcmp(op, "+") == 0) {
					printf("%hd", *(short *) addieren("short", &z1, &z2));
				} else if (strcmp(op, "-") == 0) {
					printf("%hd", *(short *) subtrahieren("short", &z1, &z2));
				}

			}

		}

	}

}

/* Funktion fuer die Addition */

void* addieren(char* typ, void* zahl1, void* zahl2) {

	if(strcmp(typ, "double") == 0) {

		double z1 = *(double *)zahl1;
		double z2 = *(double *)zahl2;
		// mittels "maloc" wird einer Funktion dynamisch Speicher reserviert
		double* z3 = (double *) malloc(sizeof(double));
		*z3 = z1 + z2;
		return z3;

	} else if(strcmp(typ, "float") == 0) {

		float z1 = *(float *)zahl1;
		float z2 = *(float *)zahl2;
		float* z3 = (float *) malloc(sizeof(float));
		*z3 = z1 + z2;
		return z3;

	} else if (strcmp(typ, "longlong") == 0) {

		long long z1 = *(long long *)zahl1;
		long long z2 = *(long long *)zahl2;
		long long* z3 = (long long *) malloc(sizeof(long long));
		*z3 = z1 + z2;
		return z3;

	} else if (strcmp(typ, "long") == 0) {

		long z1 = *(long *)zahl1;
		long z2 = *(long *)zahl2;
		long* z3 = (long *) malloc(sizeof(long));
		*z3 = z1 + z2;
		return z3;

	} else if (strcmp(typ, "int") == 0) {

		int z1 = *(int *)zahl1;
		int z2 = *(int *)zahl2;
		int* z3 = (int *) malloc(sizeof(int));
		*z3 = z1 + z2;
		return z3;

	} else if (strcmp(typ, "short") == 0) {

		short z1 = *(short *)zahl1;
		short z2 = *(short *)zahl2;
		short* z3 = (short *) malloc(sizeof(short));
		*z3 = z1 + z2;
		return z3;

	}

	return NULL;

}

/* Funktion fuer die Subtraktion */

void* subtrahieren(char* typ, void* zahl1, void* zahl2) {

	if(strcmp(typ, "double") == 0) {

		double z1 = *(double *)zahl1;
		double z2 = *(double *)zahl2;
		// mittels "maloc" wird einer Funktion dynamisch Speicher reserviert
		double* z3 = (double *) malloc(sizeof(double));
		*z3 = z1 - z2;
		return z3;

	} else if(strcmp(typ, "float") == 0) {

		float z1 = *(float *)zahl1;
		float z2 = *(float *)zahl2;
		float* z3 = (float *) malloc(sizeof(float));
		*z3 = z1 - z2;
		return z3;

	} else if (strcmp(typ, "longlong") == 0) {

		long long z1 = *(long long *)zahl1;
		long long z2 = *(long long *)zahl2;
		long long* z3 = (long long *) malloc(sizeof(long long));
		*z3 = z1 - z2;
		return z3;

	} else if (strcmp(typ, "long") == 0) {

		long z1 = *(long *)zahl1;
		long z2 = *(long *)zahl2;
		long* z3 = (long *) malloc(sizeof(long));
		*z3 = z1 - z2;
		return z3;

	} else if (strcmp(typ, "int") == 0) {

		int z1 = *(int *)zahl1;
		int z2 = *(int *)zahl2;
		int* z3 = (int *) malloc(sizeof(int));
		*z3 = z1 - z2;
		return z3;

	} else if (strcmp(typ, "short") == 0) {

		short z1 = *(short *)zahl1;
		short z2 = *(short *)zahl2;
		short* z3 = (short *) malloc(sizeof(short));
		*z3 = z1 - z2;
		return z3;

	}

	return NULL;

}
