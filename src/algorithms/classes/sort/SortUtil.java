/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sort;
import java.util.Random;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.BiConsumer;

/**
 *
 * @author abhishekchopra
 */
public class SortUtil {
    
    private final static int[] testCases = {0, 1, 2, 3, 10, 20, 50, 100};
    
    public static void swap(final Object[] arr, int i, int j) {
        Object swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }
    
    public static boolean less(final Comparable a, final Comparable b) {
        return a.compareTo(b) < 0;
    }
    
    public static  boolean less(final Object a, final Object b, final Comparator cmp) {
        return cmp.compare(a, b) < 0;
    }
    
    // for revision
    // https://www.coursera.org/learn/algorithms-part1/lecture/12vcF/shuffling
    public static void shuffle(final Object[] arr) {
        final int N = arr.length;
        final Random rand = new Random();
        for (int i = 1; i < N; i++) {
            final int r = rand.nextInt(i + 1);
            swap(arr, i, r);
        }
    }
    
    protected static boolean isSorted(final Object[] arr, Comparator cmp) {
        final int N = arr.length;
        for (int i = 0 ; i < N - 1 ; i++) {
            if (cmp.compare(arr[i], arr[i + 1]) > 0) return false;
        }
        return true;
    }
    
    protected static boolean isSorted(final Comparable[] arr) {
        Comparator<Comparable> cmp = Comparator.<Comparable>naturalOrder();
        return isSorted(arr, cmp);
    }
    
    public static Integer[] generateRandomArr(int n) {
        final Integer[] arr = new Integer[n];
        if (n <= 0) return arr;
        while (n > 0) arr[n - 1] = n--;
        shuffle(arr);
        return arr;
    }
    
    protected static boolean testCase(Consumer<Comparable[]> sort, int n) {
        Integer[] arr = generateRandomArr(n);
        sort.accept(arr);
        return isSorted(arr);
    }
    
    protected static boolean testCase(BiConsumer<Integer[], Comparator<Integer>> sort, int n) {
        Integer[] arr = generateRandomArr(n);
        sort.accept(arr, Comparator.<Integer>naturalOrder());
        return isSorted(arr);
    }
    
    protected static void testComparable(Consumer<Comparable[]> sort) {
        final int N = testCases.length;
        int testCasesPassed = 0;
        for (int i = 0 ; i < N ; i++) {
            if (testCase(sort, testCases[i])) {
                testCasesPassed++;
                System.out.println("Tets case " + (i + 1) + " passed where N = " + testCases[i]);
            } else {
                System.out.println("Tets case " + (i + 1) + " failed where N = " + testCases[i]);
            }
        }
        System.out.println("===================================");
        System.out.println(testCasesPassed + " out of " + N + " test cases passed");
        System.out.println("===================================");
    }
    
    protected static void test(BiConsumer<Integer[], Comparator<Integer>> sort) {
        final int N = testCases.length;
        int testCasesPassed = 0;
        for (int i = 0 ; i < N ; i++) {
            if (testCase(sort, testCases[i])) {
                testCasesPassed++;
                System.out.println("Tets case " + (i + 1) + " passed where N = " + testCases[i]);
            } else {
                System.out.println("Tets case " + (i + 1) + " failed where N = " + testCases[i]);
            }
        }
        System.out.println("===================================");
        System.out.println(testCasesPassed + " out of " + N + " test cases passed");
        System.out.println("===================================");
    }
}
