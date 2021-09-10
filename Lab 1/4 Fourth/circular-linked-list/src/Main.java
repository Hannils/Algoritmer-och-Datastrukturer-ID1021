import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Author - Hampus Nilsson
 * Generated: 8th of September 2021
 * Solved Problem: Implement a generic iterable circular linked list which allows the user to insert and
 * remove elements to/from the front and back end of the queue.
 * How it's used: Enter one of prompted commands.
 * addStart adds element to start
 * rmStart removed element from start
 * addEnd adds element to end
 * rmEnd removed element from end
 * size returns size of stack
 * isEmpty returns true or false depending on if the stack is empty
 * exit exits the program
 * The program uses a stack and is circular meaning that there is never a node that points to null.
 * When there is a single element in the stack, it's .next points to itself.
 * Based on: An assignment from the KTH course ID1021. Material from Princeton
 */

/**
 * Main class
 */
public class Main {

    /**
     * Blueprint from which a stack is created
     * @param <Item> wrapper type of the elements in the stack
     */
    public static class Stack<Item> implements Iterable<Item> {
        private Node<Item> first;
        private Node<Item> last;
        private int magnitude;

        /**
         * Blueprint from which a node is created
         * @param <Item> wrapper type of the element in the node
         */
        private class Node<Item> {
            private Item item;
            private Node<Item> next;
        }

        /**
         * Defines an instance of the stack
         */
        public Stack() {
            first = null;
            last = null;
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
         * @return size of the stack
         */
        public int size() {
            return magnitude;
        }


        /**
         * Adds an item to the start of the stack
         * newfirst.next becomes oldfirst
         * if the stack is empty then newfirst.next becomes last
         * else if the stack is not empty then last.next becomes newfirst
         * @param item to be added to the stack
         */
        public void addToStart(Item item) {
            Node<Item> oldfirst = first;
            first = new Node<Item>();
            first.item = item;
            first.next = oldfirst;
            if (isEmpty()) {
                last = first;
                first.next = last;
            } else
                last.next = first;
            magnitude++;
        }


        /**
         * Removes an item from the start of the stack
         * first becomes first.next
         * last.next becomes first
         * @return the item removed from the stack
         */
        public Item removeFromStart() {
            if (isEmpty()) throw new NoSuchElementException("Stack already empty");
            Item item = first.item;
            first = first.next;
            last.next = first;
            magnitude--;
            return item;
        }

        /**
         * Add an item to the end of the stack
         * if the stack is empty then first = last
         * else if the stack is not empty then
         * oldlast.next = last
         * last.next = first;
         * @param item to be added to the stack
         */
        public void addToEnd(Item item) {
            Node<Item> oldLast = last;
            last = new Node<Item>();
            last.item = item;
            if (isEmpty()) {
                first = last;
            } else
                oldLast.next = last;
            last.next = first;
            magnitude++;
        }

        /**
         * Removes an item from the end of the stack
         * Starts from first and goes up to node = second to last
         * last becomes node
         * last.next becomes first
         * @return the item removed from the stack
         */
        public Item removeFromEnd() {
            if (isEmpty()) throw new NoSuchElementException("Stack already empty");
            Node<Item> node = first;
            while (node.next != last) node = node.next;
            Item item = last.item;
            last = node;
            last.next = first;
            magnitude--;
            return item;

        }

        /**
         * Defines how the stack should be printed
         * @return the stack as a string
         */
        public String toString() {
            StringBuilder s = new StringBuilder();
            Node<Item> node = first;
            for (Item i : this) {
                s.append("[" + i + "],");
            }
            for(int i = 0; i < magnitude; i++) {
                s.append("\n\nNode nr " + i + ": " + node);
                s.append("\nNode value: " + "[" + node.item + "]");
                s.append("\nNext node: " + node.next + "\n\n");
                node = node.next;
            }
            return s.toString();
        }


        /**
         * Creates and gets an instance of iterator
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
            private int iterationCount;

            /**
             * Defines an instance of iterator
             * @param first node from where iteration should start
             */
            public LinkedIterator(Node<Item> first) {
                current = first;
                iterationCount = 0;

            }

            /**
             * Checks if current node has a next
             * @return true or false
             */
            public boolean hasNext() {
                return iterationCount != magnitude;
            }

            /**
             * Goes to the next node current becomes current.next
             * @return
             */
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                Item item = current.item;
                current = current.next;
                iterationCount++;
                return item;
            }
        }
    }

    /**
     * Function that runs the program. Handles all input
     * @param args program input
     */
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("'addStart', 'rmStart', 'addEnd', 'rmEnd', 'size', 'isEmpty', 'exit'");
            String input = scanner.nextLine();
            switch (input) {
                case ("addStart"):
                    System.out.print("Number to add to start: ");
                    stack.addToStart(scanner.nextInt());
                    System.out.println("Stack: " + stack + "\n\n");
                    scanner.nextLine();
                    break;
                case ("rmStart"):
                    System.out.println("Removed number: " + stack.removeFromStart());
                    System.out.println("Stack: " + stack + "\n\n");
                    break;
                case ("addEnd"):
                    System.out.print("Number to add to end: ");
                    stack.addToEnd(scanner.nextInt());
                    System.out.println("Stack: " + stack + "\n\n");
                    scanner.nextLine();
                    break;
                case ("rmEnd"):
                    System.out.println("Removed number: " + stack.removeFromEnd());
                    System.out.println("Stack: " + stack + "\n\n");
                    break;
                case ("size"):
                    System.out.println("Size: " + stack.size() + "\n\n");
                    break;
                case ("isEmpty"):
                    System.out.println("isEmpty: " + stack.isEmpty() + "\n\n");
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
