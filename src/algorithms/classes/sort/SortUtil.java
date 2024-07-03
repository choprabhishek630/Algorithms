/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sort;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.BiConsumer;
import util.Arrays;

/**
 *
 * @author abhishekchopra
 */
public class SortUtil {
    
    private final static int[] testCases = {0, 1, 2, 3, 10, 20, 50, 100};
    
    protected static boolean testCase(Consumer<Comparable[]> sort, int n) {
        Integer[] arr = Arrays.generateRandomArr(n);
        sort.accept(arr);
        return Arrays.isSorted(arr);
    }
    
    protected static boolean testCase(BiConsumer<Integer[], Comparator<Integer>> sort, int n) {
        Integer[] arr = Arrays.generateRandomArr(n);
        sort.accept(arr, Comparator.<Integer>naturalOrder());
        return Arrays.isSorted(arr);
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