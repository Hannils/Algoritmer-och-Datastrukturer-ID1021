#include <stdio.h>

/**
 * Author - Hampus Nilsson
 * Generated: 8th of September 2021
 * Solved Problem: Reverse string input recursively
 * How it's used: Enter a string into the program. The program takes one character at a time and expects to print it.
 * Before the program prints it calls itself and does the same to the next character until a newline is identified.
 * By calling the function before it gets to print it creates a debt to itself and when the newline is identified it returns, which unfolds the debt 
 * and prints the input in reverse.
 * Based on: An assignment from the KTH course ID1021
 */


/**
 * Function which gets the input char by char, checks if the char is a newline and the calls itself before printing the char.
 */
void reverseOnNewLineRecursive() {
    char input = getchar();
    if (input == '\n') return;
    reverseOnNewLineRecursive();
    printf("%c", input);
}

/**
 * Function which the program starts in, calls reverseOnNewLineRecursive
 */
int main()
{
    reverseOnNewLineRecursive();
    return 0;
}




