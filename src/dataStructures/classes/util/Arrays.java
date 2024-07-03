/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Comparator;
import java.util.Random;
import java.util.Iterator;

/**
 *
 * @author abhishekchopra
 */
public class Arrays {
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
    
    public static boolean isSorted(final Object[] arr, Comparator cmp) {
        final int N = arr.length;
        for (int i = 0 ; i < N - 1 ; i++) {
            if (cmp.compare(arr[i], arr[i + 1]) > 0) return false;
        }
        return true;
    }
    
    public static boolean isSorted(final Comparable[] arr) {
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
    
    public static void print(Object[] arr) {
        for (Object item : arr)
            System.out.print(item + ", ");
        System.out.println();
    }
    
    public static void print(Iterator<? extends Object> itr) {
        while (itr.hasNext())
            System.out.print(itr.next() + ", ");
        
        System.out.println();
    }
}
