import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * Author - Hampus Nilsson
 * Generated: 8th of September 2021
 * Solved Problem: Check if string of parentheses is balanced
 * How it's used: Enter a prompted command.
 * push pushed an item to the stack
 * pop pops an item from the stack
 * size return the size of the stack
 * isEmpty returns true or false depending on the size of the stack
 * checkBalance prompts for an input or parentheses. Each open parentheses will be pushed to the stack,
 * when a closed parentheses is identified a pop command is called and the program checks if
 * the last entered open parentheses matches with the closing parentheses.
 * Based on: The stack code has been based on examples given from Princeton. URL: https://algs4.cs.princeton.edu/10fundamentals/
 */

/**
 * Main class
 */
public class Main {
    /**
     * Blueprint for which the stack is created
     * @param <Item> wrapper type of element to be stored in stack
     */
    public static class Stack<Item> implements Iterable<Item> {
        private Node<Item> first;
        private int magnitude;

        /**
         * Blueprint from which a node is created
         * @param <Item> wrapper type of element to be stored in node
         */
        private class Node<Item> {
            private Item item;
            private Node<Item> next;
        }

        /**
         * Define instance of stack
         */
        public Stack() {
            first = null;
            magnitude = 0;
        }

        /**
         * Check if stack is empty
         * @return true or false
         */
        public boolean isEmpty() {
            return size() == 0;
        }

        /**
         * Check the size/magnitude of the stack
         * @return the magnitude
         */
        public int size() {
            return magnitude;
        }

        /**
         * Push an item into the stack. Adds a node to the front.
         * @param item the item to be pushed to the stack
         */
        public void push(Item item) {
            Node<Item> oldfirst = first;
            first = new Node<Item>();
            first.item = item;
            first.next = oldfirst;
            magnitude++;
        }

        /**
         * Pop an item from the stack. Removed from the front.
         * @return the item popped from the stack
         */
        public Item pop() {
            if (isEmpty()) throw new NoSuchElementException("Cannot remove from empty stack");
            Item item = first.item;
            first = first.next;
            magnitude--;
            return item;
        }

        /**
         * Specifies the way the stack should be converted to a string.
         * @return the string of the stack
         */
        public String toString() {
            StringBuilder s = new StringBuilder();
            for (Item item : this) {
                s.append("[" + item + "],");
                s.append(' ');
            }
            return s.toString();
        }

        /**
         *  Creates and gets an instance of iterator
         * @return an instance of iterator
         */
        public Iterator<Item> iterator() {
            return new LinkedIterator(first);
        }

        /**
         * Blueprint from which an iterator is created
         */
        private class LinkedIterator implements Iterator<Item> {
            private Node<Item> current;

            /**
             * Define an instance of iterator
             * @param first Which node from where iteration should start
             */
            public LinkedIterator(Node<Item> first) {
                current = first;
            }

            /**
             * Checks if current node isn't null
             * @return true or false
             */
            public boolean hasNext() {
                return current != null;
            }

            /**
             * Iterators function to go to change current node to the next node in the sequence
             * @return the item of the current node before the change to the next
             */
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }

    /**
     *  Function to check if parameter matches one either '(', '[' or '{'
     * @param c the char to be checked
     * @return true of false
     */
    private static boolean isParenthesesOpen(char c) {
        return c == '(' || c == '[' || c == '{';
    }


    /**
     * Function to check if parameter matches the popped character from the stack
     * @param c the char to be checked
     * @param popped the char from the stack
     * @return true or false
     */
    private static boolean checkMatch(char c, char popped) {
        return (popped == '(' && c == ')') ||
                (popped == '[' && c == ']') ||
                (popped == '{' && c == '}');
    }


    /**
     * Function to check if the string parameter consisting of parentheses is balanced
     * @param s the string of check the balance of
     * @return true or false
     */
    public static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (!isParenthesesOpen(s.charAt(i)) && stack.isEmpty()) return false;
            if (isParenthesesOpen(s.charAt(i))) stack.push(s.charAt(i));
            else if(checkMatch(s.charAt(i), stack.pop())) continue;
            else return false;
        }
        if (stack.isEmpty()) return true;
        return false;
    }


    /**
     * Function which runs the program. Handles all input.
     * @param args program input
     */
    public static void main(String[] args) {
       Stack<Character> testingStack = new Stack<Character>();
       Scanner scanner = new Scanner(System.in);
       while (true) {
           System.out.println("'push', 'pop', 'size', 'isEmpty', 'checkBalance', 'exit'");
           String input = scanner.nextLine();
           switch (input) {
               case ("push"):
                   System.out.print("Character to add: ");
                   testingStack.push((scanner.nextLine()).charAt(0));
                   System.out.println("Stack: " + testingStack + "\n\n");
                   break;
               case ("pop"):
                   System.out.println("Popped: " + testingStack.pop() + "\n\n");
                   break;
               case ("size"):
                   System.out.println("Size: " + testingStack.size() + "\n\n");
                   break;
               case ("isEmpty"):
                   System.out.println("isEmpty: " + testingStack.isEmpty() + "\n\n");
                   break;
               case ("checkBalance"):
                   System.out.print("Enter parentheses: ");
                   System.out.println("Balanced: " + isBalanced(scanner.nextLine()) + "\n\n");
                   break;
               case ("exit"):
                   System.exit(0);
                   break;
               default:
                   System.out.println("Unknown command");
                   break;
           }
       }
    }
}
