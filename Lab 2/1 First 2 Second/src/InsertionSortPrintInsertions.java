import java.util.Scanner;

public class InsertionSortPrintInsertions {

    public String toString(int[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int countInversions(int[] array) {
        int inversions = 0;
        for (int i = 1; i < array.length; i++) {
            for (int j = i - 1; j >= 0 && array[j] > array[i]; j--) {
                inversions++;
            }
        }
        return inversions;
    }

        int[] insertionSort(int array[]) {
            System.out.println(toString(array));
            int count = 0;
            for (int i = 1; i < array.length; i++) {
                for (int j = i - 1; j >= 0 && array[j] > array[j+1]; j--) {
                    swap(array, j, j+1);
                    count++;
                    System.out.println(toString(array));
                }
            }
            System.out.println("Number of swaps: " + count);
            return array;
        }

        public static void main(String args[]) {
            Scanner scanner = new Scanner(System.in);
            InsertionSortPrintInsertions is = new InsertionSortPrintInsertions();
            while (true) {
                System.out.print("Size of array: ");
                int[] data = new int[scanner.nextInt()];
                for (int i = 0; i < data.length; i++) {
                    System.out.println("Int at position " + i + " ");
                    data[i] = scanner.nextInt();
                }
                System.out.println("Inversions: " + countInversions(data));
                int[] array = is.insertionSort(data);
                System.out.println("Sorted Array in Ascending Order: ");
                System.out.println(is.toString(array));
            }
        }
    }

