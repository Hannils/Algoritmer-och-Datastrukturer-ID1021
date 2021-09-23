import java.util.Scanner;

public class SortDescendingOrder {

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

    int[] insertionSort(int array[]) {
        int count = 0;
        for (int i = 1; i < array.length; i++) {
            for (int j = i - 1; j >= 0 && array[j] > array[j+1]; j--) {
                swap(array, j, j+1);
                count++;
            }
        }
        return array;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        SortDescendingOrder is = new SortDescendingOrder();
        while (true) {
            System.out.print("Size of array: ");
            int[] data = new int[scanner.nextInt()];
            for (int i = 0; i < data.length; i++) {
                System.out.println("Int at position " + i + " ");
                data[i] = -scanner.nextInt();
            }
            int[] array = is.insertionSort(data);
            for (int i = 0; i < array.length; i++) {
                if(array[i] < 0) array[i] = -array[i] ;
                else array[i] = -array[i];
            }
            System.out.println("Sorted Array in Descending Order: ");
            System.out.println(is.toString(array));
        }
    }
}

