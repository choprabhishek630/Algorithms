/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sortProblems;
import util.Arrays;
import java.util.Random;

// for revision
// https://www.coursera.org/learn/algorithms-part1/quiz/cjwBY/interview-questions-elementary-sorts-ungraded/attempt

/**
 *
 * @author abhishekchopra
 */
public class DutchNatinalFlag {
    private static enum Pebbles {
        RED,
        BLUE,
        WHITE
    }
    
    private static final int[] testcases = { 0, 1, 2, 10, 50, 100 };
    
    public static void sort(Pebbles[] arr) {
        int l = 0, r = arr.length - 1, i = 0;
        while (i <= r) {
            switch (arr[i]) {
                case RED -> {
                    Arrays.swap(arr, i++, l++);
                    break;
                }
                case BLUE -> {
                    Arrays.swap(arr, i, r--);
                    break;
                }
                case WHITE -> {
                    i++;
                    break;
                }
            }
        }
    }
    
    private static Pebbles convertToPebble(int a) {
        int finalValue = Math.abs(a) % Pebbles.values().length;
        return switch (finalValue) {
            case 0 -> Pebbles.RED;
            case 1 -> Pebbles.WHITE;
            default -> Pebbles.BLUE;
        };
    }
    
    private static int convertToInt(Pebbles a) {
        return switch (a) {
            case Pebbles.RED -> 0;
            case Pebbles.WHITE -> 1;
            default -> 2;
        };
    }
    
    private static boolean isSorted(Pebbles[] arr) {
        for (int i = 1 ; i < arr.length ; i++)
            if (convertToInt(arr[i - 1]) > convertToInt(arr[i])) return false;
        return true;
    }
    
    private static Pebbles[] generateRandomArr(int N) {
        Pebbles[] arr = new Pebbles[N];
        Random r = new Random();
        while (N > 0) {
            int curr = r.nextInt(Pebbles.values().length);
            arr[N - 1] = convertToPebble(curr);
            N--;
        }
        Arrays.shuffle(arr);
        return arr;
    }
    
    private static void print(Pebbles[] arr) {
        for (Pebbles p : arr)
            System.out.print(p + ", ");
        System.out.println();
    }
    
    private static boolean testEachTestCase(int N) {
        Pebbles[] arr = generateRandomArr(N);
        print(arr);
        sort(arr);
        print(arr);
        if (isSorted(arr)) {
            System.out.println("Test case passed => N = " + N);
            return true;
        } else {
            System.out.println("Test case failed => N = " + N);
            return false;
        }
    }
    
    private static void test() {
        int testCasesPassed = 0;
        for (int testcase : testcases)
            if (testEachTestCase(testcase)) testCasesPassed++;
        
        System.out.println("=====================================");
        System.out.println(testCasesPassed + " out of " + testcases.length + " passed");
        System.out.println("=====================================");
    }
    
    public static void main(String[] args) {
        test();
    }
}
