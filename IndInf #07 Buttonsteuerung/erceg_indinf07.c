/**
Name of file	: erceg_toggleLEDs.c
Author			: Stefan Erceg <stefan.erceg@student.tgm.ac.at>
Version			: 20141211
Description		: When you start the program, every second LED on the microcontrollerboard, where the LED° %90° is equal to 0, 
				  will shine. If you press the button, the other LEDs, where the LED° %90 is not equal to 0, will shine. 
*/

// Includes
#include <stdint.h>
#include <stdbool.h>
#include <stm32f30x.h>

// Prototypes
void toggle_LEDs(int,GPIO_TypeDef *);
void toggle_allLEDs(GPIO_TypeDef *);

/**
 * Stub required by newlibc.
 * 
 * E.g. for malloc()
 */
void
_sbrk(void)
{
}

/**
 * Stub required by newlibc.
 *
 * Used for static constructors in C++
 */
void
_init(void)
{
}

/**
 * The LED of port e, which pin is set in the parameter, will be switched on with this function.
 */
void toggle_LEDs (int bit, GPIO_TypeDef *PE) {
	PE->ODR ^= (1 << bit);
}

/**
 * All LEDs of port e will be switched on with this function.
 */
void toggle_allLEDs(GPIO_TypeDef *PE) {

	int i;

	for (i = 8; i <= 15; i++) {
		PE->ODR ^= (1 << i);
	}

}

int main (void) {
	
	// Struct of PIO Port E
	GPIO_TypeDef *PE = GPIOE;

	// Struct of PIO Port A
	GPIO_TypeDef *PA = GPIOA;

	// Pin 0 of port A is set to pull down
	PA->PUPDR = (1 << 1);

	int i;

	// Pin 8 to 15 of PORT E, which are the LEDs, are set as output mode
	for (i = 8; i <= 15; i++) {
		PE->MODER |= (1 << (i*2));
	}

	// the current button state is saved
	uint16_t buttonState = PA->IDR & (1 << 0);

	// the LEDs on pin 9, 11, 13 and 15 are switched on
	toggle_LEDs(9, PE);
	toggle_LEDs(11, PE);
	toggle_LEDs(13, PE);
	toggle_LEDs(15, PE);


	while(1) {
		// the LEDs will toggle, when the button is pressed
		if((PA->IDR & (1 << 0)) != buttonState) {
			/* because 4 LEDs are already switched on, the use of the function toggle_allLEDs will deactivate this 4 LEDs
			   and switch on the other 4 LEDs */
			toggle_allLEDs(PE);
			// the new state of the button is saved
			buttonState = PA->IDR & (1 << 0);
		}

	}

}
