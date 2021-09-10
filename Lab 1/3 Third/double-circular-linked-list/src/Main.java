import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Author - Hampus Nilsson
 * Generated: 8th of September 2021
 * Solved Problem: Implement a generic iterable FIFO-queue based on a double linked circular list
 * How it's used: A commands is entered when the program is started.
 * Enqueue adds an element to the queue
 * Dequeue removes an element from the queue
 * Size returns the size of the queue
 * isEmpty returns true or false depending on if the queue is empty
 * Exit exits the program.
 * When an element is added and it's the only one, it's next points to itself making it circular
 * and it gets a prev which makes it double, the prev also points to itself if the node is alone.
 * If an element is added and it's not the only one, it's marked as last, its next points to first and its previous points to first
 * Based on: An assignment from the KTH course ID1021
 */

/**
 * Main class
 */
public class Main {
    /**
     * Blueprint from which a queue is created
     * @param <Item> wrapper type of element in the queue
     */
    public static class Queue<Item> implements Iterable<Item> {
        private Node<Item> first;
        private Node<Item> last;
        private int magnitude;

        /**
         * Creates and gets an instance of iterator
         * @return an iterator
         */
        public Iterator<Item> iterator()  {
            return new LinkedIterator(first);
        }

        /**
         * Blueprint from which an iterator is created
         */
        private class LinkedIterator implements Iterator<Item> {
            private Node<Item> current;
            private int iterationCount;

            /**
             *  Defines a new instance of iterator
             * @param first the node to be current
             */
            public LinkedIterator(Node<Item> first) {
                current = first;
                iterationCount = 0;
            }

            /**
             * Checks if the current amount of iterations aren't equal to the size of the queue
             * @return true or false
             */
            public boolean hasNext() {
                return iterationCount != magnitude;
            }

            /**
             * Goes to next node in the queue
             * @return the item of the current queue
             */
            public Item next() {
                Item item = current.item;
                current = current.next;
                iterationCount++;
                return item;
            }
        }

        /**
         * Blueprint from which a node is created
         * @param <Item> the wrapper type of the node
         */
        private class Node<Item> {
            private Item item;
            private Node<Item> next;
            private Node<Item> prev;
        }

        /**
         * Defines a new instance of queue
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
         * @return the size of the queue
         */
        public int size() {
            return magnitude;
        }

        /**
         * Adds a node and element to the queue.
         * previous node of the new node is the oldlast node
         * next node of the new node is the first node
         * previous node of first is the new node
         * @param item the element of the node
         */
        public void enqueue(Item item) {
            Node<Item> oldlast = last;
            last = new Node<Item>();
            last.item = item;
            last.prev = oldlast;
            last.next = first;
            if (isEmpty()) {
                first = last;
                first.next = last;
                first.prev = last;
            }
            else {
                first.prev = last;
                oldlast.next = last;
            }
            magnitude++;
        }

        /**
         * Removes a node and element from the queue
         * first.next becomes first
         * first.next previous becomes last
         * last.next becomes first.next
         * @return the item of the dequeued node
         */
        public Item dequeue() {
            if (isEmpty()) throw new NoSuchElementException("Queue is empty");
            Item item = first.item;
            first = first.next;
            first.prev = last;
            last.next = first;
            magnitude--;
            if (isEmpty()) last = null;
            return item;
        }

        /**
         * Specifies how the queue should be printed
         * @return the queue as a string
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
                s.append("\nNext node: " + node.next);
                s.append("\nPrevous node: " + node.prev + ",\n\n");
                node = node.next;
            }
            return s.toString();
        }
    }

    /**
     * Function which runs the program. Also handles all input
     * @param args the input to the program
     */
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<Integer>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("'enqueue', 'dequeue', 'size', 'isEmpty', 'exit'");
            String input = scanner.nextLine();
            switch (input) {
                case ("enqueue"):
                    System.out.print("Number to add: ");
                    queue.enqueue(scanner.nextInt());
                    scanner.nextLine();
                    System.out.println("Queue: " + queue + "\n\n");
                    break;
                case ("dequeue"):
                    System.out.println("Dequeued number: " + queue.dequeue());
                    System.out.println("Queue: " + queue + "\n\n");
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
                    System.out.println("Unknown commands");
                    break;
            }
        }

    }
}
