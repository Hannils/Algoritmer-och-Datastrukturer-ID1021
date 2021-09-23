import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class MergeCutoff {
    private static int CUTOFF = 2;

    public static String toString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append("[" + array[i] + "]");
            if (i < array.length - 1) sb.append(",");
        }
        return sb.toString();
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void mergeSort(int[] a) {
        int[] aux = new int[a.length];
        sort(a, aux, 0, a.length-1);
    }

    // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if ((aux[j] < aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(int[] a, int[] aux, int lo, int hi) {
        int n = hi - lo + 1;
        if (n <= CUTOFF) {
            insertionSort(a, lo, hi);
            return;
        }
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void insertionSort(int array[], int lo, int high) {
        //System.out.println("Sorting from " + lo + " to " + high);
        for (int i = lo; i < high; i++) {
            for (int j = i - 1; j >= 0 && array[j] > array[j+1]; j--)
                swap(array, j, j+1);
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }



    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner;
        scanner = new Scanner(new File(args[0]));
        int[] array = new int[scanner.nextInt()];
        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }
        scanner.close();
        System.out.println("What cutoff?\n");
        scanner = new Scanner(System.in);
        long startTime = 100;
        CUTOFF = scanner.nextInt();
        startTime = System.nanoTime();
        mergeSort(array);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        //System.out.println(toString(array));
        System.out.println("\nTotal time to complete:\n" + (totalTime / 1000000) + "ms");
        System.out.println((totalTime / 1000) + " MICROs");
        System.out.println((totalTime) + " ns");

    }
}

