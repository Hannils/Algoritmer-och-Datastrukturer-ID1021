
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Author - Hampus Nilsson
 * Generated: 8th of September 2021
 * Solved Problem: Reverse string input iteratively
 * How it's used: Enter a string into the program. The program goes through the string character by character
 * and pushes them onto the stack. When all the characters have been pushed, the program iterates through the stack
 * and prints each character in reverse.
 * Based on: An assignment from the KTH course ID1021. Material from Princeton
 */

/**
 * Class which holds the entire program
 */
public class ReverseOnNewLineIterative {
    /**
     * Blueprint from which the stack is created
     * @param <Item> The wrapper type of the stack
     */
    public static class Stack<Item> implements Iterable<Item> {
        private Node<Item> first;
        private int magnitude;

        /**
         * Blueprint from which a node is created
         * @param <Item> The wrapper type of the node
         */
        private static class Node<Item> {
            private Item item;
            private Node<Item> next;
        }

        /**
         * Constructor for the stack
         */
        public Stack() {
            first = null;
            magnitude = 0;
        }

        /**
         * Checks if the stack is empty
         * @return true or false
         */
        public boolean isEmpty() {
            return size() == 0;
        }

        /**
         * @return size of stack
         */
        public int size() {
            return magnitude;
        }

        /**
         * Creates a new first and pushes the oldfirst forward
         * @param item of the pushed element
         */
        public void push(Item item) {
            Node<Item> oldfirst = first;
            first = new Node<Item>();
            first.item = item;
            first.next = oldfirst;
            magnitude++;
        }

        /**
         * Removes the first and sets first.next to first
         * @return the item of the removed node
         */
        public Item pop() {
            if (isEmpty()) throw new NoSuchElementException("Cannot pop empty stack");
            Item item = first.item;
            first = first.next;
            magnitude--;
            return item;
        }

        /**
         * Function which specifies how to print the stack
         * @return the stack as a string
         */
        public String toString() {
            StringBuilder s = new StringBuilder();
            Node<Item> node = first;
            for (int i = 0; i < magnitude; i++) {
                s.append(",[" + node.item + "]");
                node = node.next;
            }
            return s.toString();
        }

        /**
         * Creates and gets an instance of the iterator class
         * @return an iterator
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
             * Defines a new instance of an interator
             * @param first the node to be the current
             */
            public LinkedIterator(Node<Item> first) {
                current = first;
            }

            /**
             * Checks if the current node has a current.next
             * @return true or false
             */
            public boolean hasNext() {
                return current != null;
            }

            /**
             * Goes from the current node to the next node
             * @return the item of the current node
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
     * Function which runs the program and handles all of the input
     * @param args the input for the program
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> methodStack = new Stack<Integer>();
        while (true) {
            System.out.println("'push', 'pop', 'size', 'isEmpty', 'reverse', 'exit'");
            String method = scanner.nextLine();
            switch (method) {
                case "push":
                    System.out.print("Number to push: ");
                    methodStack.push(scanner.nextInt());
                    System.out.println("Stack: " + methodStack + "\n\n");
                    scanner.nextLine();
                    break;
                case "pop":
                    System.out.println("Popped number: " + methodStack.pop());
                    System.out.println("Stack: " + methodStack + "\n\n");
                    break;
                case "size":
                    System.out.println("Size: " + methodStack.size());
                    System.out.println("Stack: " + methodStack + "\n\n");
                    break;
                case "isEmpty":
                    System.out.println("isEmpty: " +  methodStack.isEmpty());
                    System.out.println("Stack: " +  methodStack + "\n\n");
                    break;
                case "reverse":
                    System.out.print("String to be reversed: ");
                    reverseOnNewlineIterative();
                    System.out.println("\n\n");
                    break;
                case "exit":
                    System.exit(0);
                default:
                    System.out.println("Unknown command");
                    break;
            }

        }

    }

    /**
     * Function which creates a new stack and pushes each character from the input string to that stack.
     * It iterates through the entire stack and prints the element, resulting in the entered string printed out in reverse
     */
    public static void reverseOnNewlineIterative() {
        Stack<Character> stack = new Stack<Character>();
        Scanner in = new Scanner(System.in);
        String input = in.next();
        for (int i = 0; i < input.length(); i++) {
            stack.push(input.charAt(i));
        }
        for (char c : stack) {
            System.out.print(c);
        }
    }
}
