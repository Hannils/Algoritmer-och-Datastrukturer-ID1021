import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Author - Hampus Nilsson
 * Generated: 8th of September 2021
 * Solved Problem: Implement a generalized queue which allows the user to remove the kth element from the queue. Assume the most recently added element has index 1.
 * How it's used: Enter one of the commands prompted at startup.
 * enqueue adds a specified element to the queue
 * remove removes a specified index from the queue
 * size returns the size of the queue
 * isEmpty returns true or false depending on if the queue is empty
 * Based on: An assignment from the KTH course ID1021
 */

/**
 * Main class
 */
public class Main {
    /**
     * Blueprint from which a queue is created
     * @param <Item> wrapper type of elements stored in queue
     */
    public static class Queue<Item> implements Iterable<Item> {
        private Node<Item> first;
        private Node<Item> last;
        private int magnitude;

        /**
         * Blueprint from which a node is created
         * @param <Item> wrapper type of element stored in node
         */
        private static class Node<Item> {
            private Item item;
            private Node<Item> next;
        }

        /**
         * Defines instance of queue
         */
        public Queue() {
            first = null;
            last = null;
            magnitude = 0;
        }

        /**
         * Checks if the queue is empty
         * @return true or false
         */
        public boolean isEmpty() {
            return size() == 0;
        }

        /**
         * @return size/magnitude of queue
         */
        public int size() {
            return magnitude;
        }

        /**
         * Adds an item to the queue
         * last becomes oldlast
         * last becomes new node
         * last.next becomes null
         * if the queue is empty then
         * first becomes last
         * else oldlast.next becomes last
         * @param item to be added to the queue
         */
        public void enqueue(Item item) {
            Node<Item> oldlast = last;
            last = new Node<Item>();
            last.item = item;
            last.next = null;
            if (isEmpty()) first = last;
            else
                oldlast.next = last;
            magnitude++;
        }

        /**
         * Removes an item from the queue depending on the input
         * iterates and counts from first until the index matches size - nr of iterations
         * Stops before the one to be removed and says that node.next becomes node.next.next
         * @param index specifying which element to remove
         * @return the removed item
         */
        public Item remove(int index) {
            if (isEmpty()) throw new NoSuchElementException("Can't remove from an empty queue");
            if (index > size()) throw new IndexOutOfBoundsException("Index: " + index + " does not exist");
            Item item;
            int target = magnitude - index;
            if (target == 0) {
                item = first.item;
                first = first.next;
            } else {
                Node<Item> node = first;
                for (int i = 1; i < target; i++) {
                    node = node.next;
                }
                item = node.next.item;
                node.next = node.next.next;
            }

            magnitude--;
            return item;

        }

        /**
         * Defines how the queue should be printed
         * @return the queue as a string
         */
        public String toString() {
            StringBuilder s = new StringBuilder();
            Node<Item> node = first;
            for (Item i : this) {
                s.append("[" + i + "]");
            }
            for (int i = 0; i < magnitude; i++) {
                s.append("\n\nNode nr " + i + ": " + node);
                s.append("\nNode value: " + "[" + node.item + "]");
                s.append("\nNext node: " + node.next + "\n\n");
                node = node.next;
            }
            return s.toString();
        }

        /**
         * Creates and gets an instance of iterator
         * @return an iterator
         */
        public Iterator<Item> iterator() {
            return new LinkedIterator(first);
        }


        /**
         * Bleuprint for which the iterator is created
         */
        private class LinkedIterator implements Iterator<Item> {
            private Node<Item> current;

            /**
             * Defines an instance of an iterator
             * @param first which node to start the iterator from
             */
            public LinkedIterator(Node<Item> first) {
                current = first;
            }

            /**
             * Checks if the current node has a next node
             * @return true or false
             */
            public boolean hasNext() {
                return current != null;
            }

            /**
             * Goes to the next node
             * @return the item of the passed node
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
     * Function which runs the program. Handles all input
     * @param args input to the program
     */
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<Integer>();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("'enqueue', 'remove', 'size', 'isEmpty', 'exit'");
            String input = scanner.nextLine();
            switch (input) {
                case ("enqueue"):
                    System.out.print("Number to add: ");
                    queue.enqueue(scanner.nextInt());
                    System.out.println("Stack: " + queue + "\n\n");
                    scanner.nextLine();
                    break;
                case ("remove"):
                    System.out.print("Index to remove from: ");
                    System.out.println("Removed: " + queue.remove(scanner.nextInt()) + "\n");
                    System.out.println("Stack: " + queue + "\n\n");
                    scanner.nextLine();
                    break;
                case ("size"):
                    System.out.println("Size: " + queue.size() + "\n\n");
                    break;
                case ("isEmpty"):
                    System.out.println("isEmpty: " + queue.isEmpty() + "\n\n");
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
