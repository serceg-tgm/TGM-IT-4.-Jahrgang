/*
Name of file	: Datentypen_Erceg.c
Author			: Stefan Erceg <stefan.erceg@student.tgm.ac.at>
Version			: 20140928.1
Description		: Creation of a simple calculator with all datatypes in C.
*/

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <string.h>
#include <float.h>

void addieren(char typ[20], void* zahl1, void* zahl2);
void subtrahieren(char typ[20], void* zahl1, void* zahl2);

int main(int argc, char** argv) {

	/* Hiermit wird der Buffer der Konsole auf 0 gesetzt (damit Eclipse die Ausgabe in der Konsole in der
	   richtigen Reihenfolge anzeigt, muss dies am Anfang der main ausgefuehrt werden). */

	setvbuf(stdout, NULL, _IONBF, 0);

	// die 2 Zahlenvariablen werden mit dem Datentyp void* definiert
	// mittels void* koennen fuer die Zahlen alle Basisdatentypen verwendet werden

	void* zahl1;
	void* zahl2;

	// die Operator- und Datentypvariable werden mit dem Datentyp char definiert
	// die Datentypvariable darf maximal 20 Zeichen enthalten

	char op;
	char dtyp[20];

	// der Benutzer wird aufgefordert, den gewuenschten Datentyp einzugeben
	// mittels "printf" werden Ausgaben in der Konsole erzeugt

	printf("Bitte geben Sie den Datentyp an, den Sie für die Rechnung verwenden wollen\n");

	// die Eingabe des Benutzers wird mittels "scanf" eingelesen
	// die Formatierungszeichen '%..' sind Platzhalter für bestimmte Datentypen (z.B. %s steht fuer einen String)
	// in eckiger Klammer wird angegeben, welche Zeichen verwendet werden duerfen
	/* damit von der jeweiligen Variable die Speicheradresse eingesetzt wird, schreibt man vor dem Variablennamen
	ein '&' */

	scanf("%20[ a-z]s", &dtyp[0]);

	// der Benutzer wird aufgefordert, 2 Zahlen und den gewuenschten Operator einzugeben

	printf("Bitte geben Sie die Rechenoperation in folgender Syntax ein: Zahl1(+/-)Zahl2\n");

	// if-Unterscheidungen werden zur Unterscheidung der jeweiligen Datentypen durchgefuehrt
	// mittels "strcmp" (String Compare) wird der Inhalt von 2 Strings verglichen
	// stimmen sie ueberein, wird 0 returnt

	// Datentyp "unsigned char" (Value range: 0 to 255)

	if (strcmp(dtyp, "unsigned char") == 0) {

		// der Benutzer wird auf den gueltigen Wertebereich hingewiesen

		printf("Gueltiger Wertebereich fuer diesen Datentyp: 0 bis %d\n", UCHAR_MAX);

		unsigned char z1;
		unsigned char z2;

		// Eingabe wird auf Gueltigkeit ueberprueft

		if (scanf("%hhu %c %hhu", &z1, &op, &z2) != 3) {
			printf("Ungueltige Eingabe! Bitte erneut eingeben.");
			return EXIT_FAILURE;
		}
		zahl1 = &z1;
		zahl2 = &z2;

	// Datentyp "int" (Value range: -2,147,483,648 to 2,147,483,647)

	} else if (strcmp(dtyp, "int") == 0) {

		printf("Gueltiger Wertebereich fuer diesen Datentyp: %d bis %d\n", INT_MIN, INT_MAX);

		int z1;
		int z2;
		if (scanf("%d %c %d", &z1, &op, &z2) != 3) {
			printf("Ungueltige Eingabe! Bitte erneut eingeben.");
			return EXIT_FAILURE;
		}
		zahl1 = &z1;
		zahl2 = &z2;

	// Datentyp "unsigned int" (Value range: 0 to 4,294,967,295)

	} else if (strcmp(dtyp, "unsigned int") == 0) {

		printf("Gueltiger Wertebereich fuer diesen Datentyp: 0 bis %u\n", UINT_MAX);

		unsigned int z1;
		unsigned int z2;
		if (scanf("%u %c %u", &z1, &op, &z2) != 3) {
			printf("Ungueltige Eingabe! Bitte erneut eingeben.");
			return EXIT_FAILURE;
		}
		zahl1 = &z1;
		zahl2 = &z2;

	// Datentyp "short" (Value range: -32,768 to 32,767)

	} else if (strcmp(dtyp, "short") == 0) {

		printf("Gueltiger Wertebereich fuer diesen Datentyp: %hd bis %hd\n", SHRT_MIN, SHRT_MAX);

		short z1;
		short z2;
		if (scanf("%hd %c %hd", &z1, &op, &z2) != 3) {
			printf("Ungueltige Eingabe! Bitte erneut eingeben.");
			return EXIT_FAILURE;
		}
		zahl1 = &z1;
		zahl2 = &z2;

	// Datentyp "unsigned short" (Value range: 0 to 65,535)

	} else if (strcmp(dtyp, "unsigned short") == 0) {

		printf("Gueltiger Wertebereich fuer diesen Datentyp: 0 bis %hu\n", USHRT_MAX);

		unsigned short z1;
		unsigned short z2;
		if (scanf("%hu %c %hu", &z1, &op, &z2) != 3) {
			printf("Ungueltige Eingabe! Bitte erneut eingeben.");
			return EXIT_FAILURE;
		}
		zahl1 = &z1;
		zahl2 = &z2;

	// Datentyp "long" (Value range: -2,147,483,648 to 2,147,483,647)

	} else if (strcmp(dtyp, "long") == 0) {

		printf("Gueltiger Wertebereich fuer diesen Datentyp: %ld bis %ld\n", LONG_MIN, LONG_MAX);

		long z1;
		long z2;
		if (scanf("%ld %c %ld", &z1, &op, &z2) != 3) {
			printf("Ungueltige Eingabe! Bitte erneut eingeben.");
			return EXIT_FAILURE;
		}
		zahl1 = &z1;
		zahl2 = &z2;

	// Datentyp "unsigned long" (Value range: 0 to 4,294,967,295)

	} else if (strcmp(dtyp, "unsigned long") == 0) {

		printf("Gueltiger Wertebereich fuer diesen Datentyp: 0 bis %lu\n", ULONG_MAX);

		unsigned long z1;
		unsigned long z2;
		if (scanf("%lu %c %lu", &z1, &op, &z2) != 3) {
			printf("Ungueltige Eingabe! Bitte erneut eingeben.");
			return EXIT_FAILURE;
		}
		zahl1 = &z1;
		zahl2 = &z2;

	// Datentyp "long long" (Value range: –9,223,372,036,854,775,808 to 9,223,372,036,854,775,807)

	} else if (strcmp(dtyp, "long long") == 0) {

		printf("Gueltiger Wertebereich fuer diesen Datentyp: %lld bis %lld\n", LLONG_MIN, LLONG_MAX);

		long long z1;
		long long z2;
		if (scanf("%lld %c %lld", &z1, &op, &z2) != 3) {
			printf("Ungueltige Eingabe! Bitte erneut eingeben.");
			return EXIT_FAILURE;
		}
		zahl1 = &z1;
		zahl2 = &z2;

	// Datentyp "unsigned long long" (Value range: 0 to 18,446,744,073,709,551,615)

	} else if (strcmp(dtyp, "unsigned long long") == 0) {

		printf("Gueltiger Wertebereich fuer diesen Datentyp: 0 bis %llu\n", ULLONG_MAX);

		unsigned long long z1;
		unsigned long long z2;
		if (scanf("%llu %c %llu", &z1, &op, &z2) != 3) {
			printf("Ungueltige Eingabe! Bitte erneut eingeben.");
			return EXIT_FAILURE;
		}
		zahl1 = &z1;
		zahl2 = &z2;

	// Datentyp "float" (Precision: 6 decimal places)

	} else if (strcmp(dtyp, "float") == 0) {

		printf("Gueltiger Wertebereich fuer diesen Datentyp: 0 bis %f\n", FLT_MAX);
		printf("Genauigkeit: 6-stellig\n");

		float z1;
		float z2;
		if (scanf("%f %c %f", &z1, &op, &z2) != 3) {
			printf("Ungueltige Eingabe! Bitte erneut eingeben.");
			return EXIT_FAILURE;
		}
		zahl1 = &z1;
		zahl2 = &z2;

	// Datentyp "double" (Precision: 15 decimal places)

	} else if (strcmp(dtyp, "double") == 0) {

		printf("Gueltiger Wertebereich fuer diesen Datentyp: 0 bis %f\n", DBL_MAX);
		printf("Genauigkeit: 15-stellig\n");

		double z1;
		double z2;
		if (scanf("%lf %c %lf", &z1, &op, &z2) != 3) {
			printf("Ungueltige Eingabe! Bitte erneut eingeben.");
			return EXIT_FAILURE;
		}
		zahl1 = &z1;
		zahl2 = &z2;

	// falls ein Datentyp eingegeben wird, der nicht verwendet werden kann, erfolgt eine Meldungsausgabe

	} else {
		printf("Der eingegebene Datentyp kann nicht verwendet werden!");
		return EXIT_FAILURE;
	}

	// die Eingabe des Operators wird ueberprueft

	if (op == '+') {
		addieren(dtyp, zahl1, zahl2);
	} else if (op == '-')
		subtrahieren(dtyp, zahl1, zahl2);
	else {
		printf("Ungueltige Eingabe! Als Operatoren koennen nur + oder - verwendet werden.");
		return EXIT_FAILURE;
	}

	return EXIT_SUCCESS;

}

/* Funktion fuer die Addition */

void addieren(char typ[20], void* zahl1, void* zahl2) {

	if (strcmp(typ, "int") == 0) {
		int z1 = *(int *)zahl1;
		int z2 = *(int *)zahl2;
		int z3 = z1 + z2;

		// falls die 1. Zahl kleiner-gleich dem Ergebnis ist, wird das Ergebnis angezeigt

		if (z1 <= z3) {
			printf("%d", z3);

		// ansonsten handelt es sich um einen Ueberlauf

		} else {
			printf("Bitte beachten Sie den gueltigen Wertebereich!");
		}

	} else if (strcmp(typ, "float") == 0) {
		float z1 = *(float *)zahl1;
		float z2 = *(float *)zahl2;
		float z3 = z1 + z2;
		if (z1 <= z3) {
			printf("%f", z3);
		} else {
			printf("Bitte beachten Sie den gueltigen Wertebereich!");
		}

	} else if (strcmp(typ, "double") == 0) {
		double z1 = *(double *)zahl1;
		double z2 = *(double *)zahl2;
		double z3 = z1 + z2;
		if (z1 <= z3) {
			printf("%lf", z3);
		} else {
			printf("Bitte beachten Sie den gueltigen Wertebereich!");
		}

	} else if (strcmp(typ, "short") == 0) {
		short z1 = *(short *)zahl1;
		short z2 = *(short *)zahl2;
		short z3 = z1 + z2;
		if (z1 <= z3) {
			printf("%hd", z3);
		} else {
			printf("Bitte beachten Sie den gueltigen Wertebereich!");
		}

	} else if (strcmp(typ, "long") == 0) {
		long z1 = *(long *)zahl1;
		long z2 = *(long *)zahl2;
		long z3 = z1 + z2;
		if (z1 <= z3) {
			printf("%ld", z3);
		} else {
			printf("Bitte beachten Sie den gueltigen Wertebereich!");
		}

	} else if (strcmp(typ, "long long") == 0) {
		long long z1 = *(long long *)zahl1;
		long long z2 = *(long long *)zahl2;
		long long z3 = z1 + z2;
		if (z1 <= z3) {
			printf("%lld", z3);
		} else {
			printf("Bitte beachten Sie den gueltigen Wertebereich!");
		}

	} else if (strcmp(typ, "unsigned int") == 0) {
		unsigned int z1 = *(unsigned int *)zahl1;
		unsigned int z2 = *(unsigned int *)zahl2;
		unsigned int z3 = z1 + z2;
		if (z1 <= z3) {
			printf("%u", z3);
		} else {
			printf("Bitte beachten Sie den gueltigen Wertebereich!");
		}

	} else if (strcmp(typ, "unsigned char") == 0) {
		unsigned char z1 = *(unsigned char *)zahl1;
		unsigned char z2 = *(unsigned char *)zahl2;
		unsigned char z3 = z1 + z2;
		if (z1 <= z3) {
			printf("%d", z3);
		} else {
			printf("Bitte beachten Sie den gueltigen Wertebereich!");
		}

	} else if (strcmp(typ, "unsigned long") == 0) {
		unsigned long z1 = *(unsigned long *)zahl1;
		unsigned long z2 = *(unsigned long *)zahl2;
		unsigned long z3 = z1 + z2;
		if (z1 <= z3) {
			printf("%lu", z3);
		} else {
			printf("Bitte beachten Sie den gueltigen Wertebereich!");
		}

	} else if (strcmp(typ, "unsigned long long") == 0) {
		unsigned long long z1 = *(unsigned long long *)zahl1;
		unsigned long long z2 = *(unsigned long long *)zahl2;
		unsigned long long z3 = z1 + z2;
		if (z1 <= z3) {
			printf("%llu", z3);
		} else {
			printf("Bitte beachten Sie den gueltigen Wertebereich!");
		}

	} else if (strcmp(typ, "unsigned short") == 0) {
		unsigned short z1 = *(unsigned short *)zahl1;
		unsigned short z2 = *(unsigned short *)zahl2;
		unsigned short z3 = z1 + z2;
		if (z1 <= z3) {
			printf("%hu", z3);
		} else {
			printf("Bitte beachten Sie den gueltigen Wertebereich!");
		}
	}

}

/* Funktion fuer die Subtraktion */

void subtrahieren(char typ[20], void* zahl1, void* zahl2) {

	if (strcmp(typ, "int") == 0) {
		int z1 = *(int *)zahl1;
		int z2 = *(int *)zahl2;
		int z3 = z1 - z2;

		// falls die 1. Zahl groeßer-gleich dem Ergebnis ist, wird das Ergebnis angezeigt

		if (z1 >= z3) {
			printf("%d", z3);

		// ansonsten handelt es sich um einen Ueberlauf

		} else {
			printf("Bitte beachten Sie den gueltigen Wertebereich!");
		}

	} else if (strcmp(typ, "float") == 0) {
		float z1 = *(float *)zahl1;
		float z2 = *(float *)zahl2;
		float z3 = z1 - z2;
		if (z1 >= z3) {
			printf("%f", z3);
		} else {
			printf("Bitte beachten Sie den gueltigen Wertebereich!");
		}

	} else if (strcmp(typ, "double") == 0) {
		double z1 = *(double *)zahl1;
		double z2 = *(double *)zahl2;
		double z3 = z1 - z2;
		if (z1 >= z3) {
			printf("%lf", z3);
		} else {
			printf("Bitte beachten Sie den gueltigen Wertebereich!");
		}

	} else if (strcmp(typ, "short") == 0) {
		short z1 = *(short *)zahl1;
		short z2 = *(short *)zahl2;
		short z3 = z1 - z2;
		if (z1 >= z3) {
			printf("%hd", z3);
		} else {
			printf("Bitte beachten Sie den gueltigen Wertebereich!");
		}

	} else if (strcmp(typ, "long") == 0) {
		long z1 = *(long *)zahl1;
		long z2 = *(long *)zahl2;
		long z3 = z1 - z2;
		if (z1 >= z3) {
			printf("%ld", z3);
		} else {
			printf("Bitte beachten Sie den gueltigen Wertebereich!");
		}

	} else if (strcmp(typ, "long long") == 0) {
		long long z1 = *(long long *)zahl1;
		long long z2 = *(long long *)zahl2;
		long long z3 = z1 - z2;
		if (z1 >= z3) {
			printf("%lld", z3);
		} else {
			printf("Bitte beachten Sie den gueltigen Wertebereich!");
		}


	} else if (strcmp(typ, "unsigned int") == 0) {
		unsigned int z1 = *(unsigned int *)zahl1;
		unsigned int z2 = *(unsigned int *)zahl2;
		unsigned int z3 = z1 - z2;
		if (z1 >= z3) {
			printf("%u", z3);
		} else {
			printf("Bitte beachten Sie den gueltigen Wertebereich!");
		}

	} else if (strcmp(typ, "unsigned char") == 0) {
		unsigned char z1 = *(unsigned char *)zahl1;
		unsigned char z2 = *(unsigned char *)zahl2;
		unsigned char z3 = z1 - z2;
		if (z1 >= z3) {
			printf("%c", z3);
		} else {
			printf("Bitte beachten Sie den gueltigen Wertebereich!");
		}

	} else if (strcmp(typ, "unsigned long") == 0) {
		unsigned long z1 = *(unsigned long *)zahl1;
		unsigned long z2 = *(unsigned long *)zahl2;
		unsigned long z3 = z1 - z2;
		if (z1 >= z3) {
			printf("%lu", z3);
		} else {
			printf("Bitte beachten Sie den gueltigen Wertebereich!");
		}

	} else if (strcmp(typ, "unsigned long long") == 0) {
		unsigned long long z1 = *(unsigned long long *)zahl1;
		unsigned long long z2 = *(unsigned long long *)zahl2;
		unsigned long long z3 = z1 - z2;
		if (z1 >= z3) {
			printf("%llu", z3);
		} else {
			printf("Bitte beachten Sie den gueltigen Wertebereich!");
		}

	} else if (strcmp(typ, "unsigned short") == 0) {
		unsigned short z1 = *(unsigned short *)zahl1;
		unsigned short z2 = *(unsigned short *)zahl2;
		unsigned short z3 = z1 - z2;
		if (z1 >= z3) {
			printf("%hu", z3);
		} else {
			printf("Bitte beachten Sie den gueltigen Wertebereich!");
		}
	}

}
