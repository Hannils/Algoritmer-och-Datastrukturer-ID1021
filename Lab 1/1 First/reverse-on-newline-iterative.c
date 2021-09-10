#include <stdio.h>

/**
 * Author - Hampus Nilsson
 * Generated: 8th of September 2021
 * Solved Problem: Reverse string input iteratively
 * How it's used: Enter a string. By filling an array of size 10 with the chars and then printing the array backwards, the string is reversed.
 * Based on: An assignment from the KTH course ID1021
 */

/**
* Function to accept input and go through the entire string to store it into the array.
* It then prints the array backwards.
*/
void reverseOnNewLineIterative() {
    int size = 10;
    int i;
    char arr[size];
    for (; i < size; i++) {
        char input = getchar();
        if (input == '\n') break;
        arr[i] = input;
    }

    for(; i >= 0; i--) {
        printf("%c", arr[i-1]);
    }
}

/**
 * The main function which calls the reversOnNewLineIterative function
 */
int main() {
    reverseOnNewLineIterative();
}