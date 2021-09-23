import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Author - Hampus Nilsson
 * Generated: 8th of September 2021
 * Solved Problem: Implement an ordered queue based on one of the implementations above. The elements stored in the queue should be integer values.
 * How it's used: Use a prompted command.
 * Enqueue adds element to queue
 * Size returns size of queue
 * isEmpty return true or false depending on if the queue is empty
 * exit exist the program.
 * The program sorts the elements at insertion.
 * Based on: An assignment from the KTH course ID1021.
 */

/**
 * Main class
 */
public class SortedOrderedQueue {

    /**
     * Blueprint from which a queue is created
     * @param <Integer> wrapper type of the elements stored in the queue
     */
    public static class Queue<Integer> implements Iterable<Integer> {
        private Node<Integer> first;
        private int magnitude;

        /**
         * Blueprint from which a node is created
         * @param <Integer> wrapper type of the elements stored in the queue
         */
        private static class Node<Integer> {
            private Integer item;
            private Node<Integer> next;
        }

        /**
         * Defines and instance of a queue
         */
        public Queue() {
            first = null;
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
         * Adds an item to the queue and sorts it into ascending order
         * Checks the elements in the queue and stores the new element accordingly
         * @param item to be added to the queue
         */
        public void enqueue(Integer item) {
            Node<Integer> target = first;
            Node<Integer> newNode = new Node<Integer>();
            newNode.item = item;
            if(isEmpty()) first = newNode;
            else if (((int) first.item > (int) item)) {
                newNode.next = first;
                first = newNode;
            }
            else {
                while (target.next != null && (int) target.next.item < (int) item) {
                    target = target.next;
                }
                newNode.next = target.next;
                target.next = newNode;
            }
            magnitude++;
        }

        /**
         * Removes an item from the queue
         * @return the removed item
         */
        public Integer dequeue() {
            if (isEmpty()) throw new NoSuchElementException("Queue underflow");
            Integer item = first.item;
            first = first.next;
            magnitude--;
            return item;
        }

        /**
         * Defines how the queue should be printed
         * @return the queue as a string
         */
        public String toString() {
            StringBuilder s = new StringBuilder();
            Node<Integer> node = first;
            for (int i = 0; i < magnitude; i++) {
                s.append("\n\nNode nr " + i + ": " + node);
                s.append("\nNode value: " + "[" + node.item + "]");
                s.append("\nNext node: " + node.next + "\n\n");
                node = node.next;
            }

            for (Integer i : this) {
                s.append ("[" + i + "],");
            }


            return s.toString();
        }


        /**
         * Creates and gets an instance of iterator
         * @return
         */
        public Iterator<Integer> iterator() {
            return new LinkedIterator(first);
        }

        /**
         * Blueprint for which an iterator is created
         */
        private class LinkedIterator implements Iterator<Integer> {
            private Node<Integer> current;

            /**
             * Defines an instance of iterator
             * @param first
             */
            public LinkedIterator(Node<Integer> first) {
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
             * Goes to the next node from current
             * @return the item of the passed node
             */
            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException();
                Integer item = current.item;
                current = current.next;
                return item;
            }
        }
    }

    /**
     * Fuction which runs the program. Handles all input
     * @param args
     */
    public static void main (String[] args) {
        Queue queue = new Queue();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("'enqueue', 'dequeue', 'size', 'isEmpty', 'exit'");
            String input = scanner.nextLine();
            switch (input) {
                case "enqueue":
                    System.out.print("Number to add: ");
                    queue.enqueue(scanner.nextInt());
                    System.out.println("Queue: " + queue + "\n\n");
                    scanner.nextLine();
                    break;
                case "dequeue":
                    System.out.println("Dequeued number: " + queue.dequeue() + "\n");
                    System.out.println("Queue: " + queue + "\n\n");
                    break;
                case "size":
                    System.out.println("Size: " + queue.size() + "\n\n");
                    break;
                case "isEmpty":
                    System.out.println("isEmpty: " + queue.isEmpty() + "\n\n");
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default: {
                        System.out.println("Unknown command");
                        break;
                }
            }
        }
    }
}
