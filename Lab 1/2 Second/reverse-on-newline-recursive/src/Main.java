import java.util.Scanner;

/**
 * Author - Hampus Nilsson
 * Generated: 8th of September 2021
 * Solved Problem: Reverse a string recursively
 * How it's used: Enter a string to be reversed. The program takes each character from the string as a substring and calls itself.
 * This is done until the string is empty. When the string is empty the function returns to the previous recursive call and prints the last character first.
 * This is done for the entire string, therefore the entire string is printed in reverse.
 * Based on: An assignment from the KTH course ID1021
 */


/**
 * The main class
 */
public class Main {

    /**
     * Function which runs the entire program. Keeps prompting for input.
     * @param args the input of the program
     */
    public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            while (true) {
                System.out.println("Enter 'exit' to exit or a string to be reversed.");
                String input = in.next();
                if (input.equals("exit")) System.exit(0);
                else reverseOnNewlineRecursive(input);
                System.out.println();
            }

    }

    /**
     * Recursive function which prints the entered string in reverse.
     * @param str the string to be reversed.
     */
    public static void reverseOnNewlineRecursive(String str)
    {
        if(str.isEmpty()) return ;
        reverseOnNewlineRecursive(str.substring(1));
        System.out.print(str.charAt(0));
    }
}
