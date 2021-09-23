import java.util.Scanner;

public class BasisForAssignments {

    int[] insertionSort(int array[]) {
        System.out.println(toString(array));
        int size = array.length;
        for (int i = 1; i < size; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && key < array[j]) {
                array[j + 1] = array[j];
                --j;
                System.out.println(toString(array));
            }
            array[j + 1] = key;
        }

        return array;
    }

    public String toString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i : array) {
            sb.append("[" + i + "]");
        }
        return sb.toString();
    }
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        BasisForAssignments is = new BasisForAssignments();
        while (true) {
            System.out.print("Size of array: ");
            int[] data = new int[scanner.nextInt()];
            for (int i = 0; i < data.length; i++) {
                System.out.println("Int at position " + i + " ");
                data[i] = scanner.nextInt();
            }
            int[] array = is.insertionSort(data);
            System.out.println("Sorted Array in Ascending Order: ");
            System.out.println(is.toString(array));
        }
    }
}
