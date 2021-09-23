#include <stdio.h>

/**
 * Swaps the current number with the next number
 */
void swapper(int *numbers, int i, int j) {
    int temp = numbers[i];
    numbers[i] = numbers[j];
    numbers[j] = temp;
}

/**
 * Orders/splits an array by negative and positive numbers
 */ 
void splitNumbers(int *numbers, int len) {
    for (int i = 0, j = 0; i < len; i++)
        if(numbers[i] < 0) {
            swapper(numbers, i, j++);
        }
}


/**
 * Prints the array
 */ 
void printArray(int numbers[], int size) {
    printf("Array: ");
    printf("[");
        for ( int j = 0; j < size; j++) {
            printf("%d",numbers[j]);
            if (j < size - 1) printf(", ");
         }
    printf("]\n");
}

/**
 * Gives user options to test the program
 */ 
static void test() {
    while (1) {
        char option;
        printf("'e' - exit, 'o' - order \n");
        scanf("%c", &option);
        if (option == 'o') {
            printf("Enter size of array: ");
            int size;
            scanf("%d", &size);
            int numbers[size];
            int number;
            for (int i = 0; i < size; i++) {
                printf("Enter number to add: ");
                scanf("%d", &number);
                numbers[i] = number;
            }
            splitNumbers(numbers, size);
            printArray(numbers, size);
            getchar();
        } else if (option == 'e') {
            break;
        } else {
            printf("Unknown command\n");
        }
    }
}

/**
 * Runs the entire program
 */ 
int main() {
    test();
}