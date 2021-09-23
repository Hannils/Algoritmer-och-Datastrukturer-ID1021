import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class MedianOfThree {

    public static String toString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append("[" + array[i] + "]");
            if (i < array.length - 1) sb.append(",");
        }
        return sb.toString();
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void quickSort(int[] a) {
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            swap(a, i, random.nextInt(a.length - 1));
        }
        sort(a, 0, a.length - 1);
    }

    // quicksort the subarray from a[lo] to a[hi]
    private static void sort(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        // cutoff to insertion sort
        int n = hi - lo + 1;

        int m = median3(a, lo, lo + n/2, hi);
        swap(a, m, lo);

        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int v = a[lo];
        while (true) {

            // find item on lo to swap
            while ((a[++i] < v)) {
                if (i == hi) break;
            }

            // find item on hi to swap
            while (v < a[--j]) {
                if (j == lo) break;      // redundant since a[lo] acts as sentinel
            }

            // check if pointers cross
            if (i >= j) break;

            swap(a, i, j);
        }

        // put partitioning item v at a[j]
        swap(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    private static int median3(int[] a, int i, int j, int k) {
        return ((a[i] < a[j]) ?
                ((a[j] < a[k]) ? j : (a[i] < a[k]) ? k : i) :
                ((a[k] < a[j]) ? j : (a[k] < a[i]) ? k : i));
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner;
        scanner = new Scanner(new File(args[0]));
        int[] array = new int[scanner.nextInt()];
        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }
        scanner.close();
        //System.out.println("'first', 'median'");
        scanner = new Scanner(System.in);
        long startTime = 100;
        startTime = System.nanoTime();
        quickSort(array);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        //System.out.println(toString(array));
        System.out.println("\nTotal time to complete:\n" + (totalTime / 1000000) + "ms");
        System.out.println((totalTime / 1000) + " MICROs");
        System.out.println((totalTime) + " ns");

    }
}

