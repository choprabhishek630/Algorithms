/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sort;
import java.util.Comparator;
import util.Arrays;

// for revision
// https://www.coursera.org/learn/algorithms-part1/lecture/ZjoSM/heapsort

/**
 *
 * @author abhishekchopra
 */
public class HeapSort {
    private static void sink(Object[] arr, int N, int k, Comparator cmp) {
        while (2 * (k + 1) - 1 < N) {
            int j = 2 * (k + 1) - 1;
            
            if (j < N - 1 && Arrays.less(arr[j], arr[j + 1], cmp)) j++;
            
            if (!Arrays.less(arr[k], arr[j], cmp))
                break;
            
            Arrays.swap(arr, k, j);
            
            k = j;
        }
    }
    
    public static void sort(Object[] arr, Comparator cmp) {
        int N = arr.length;
        if (N <= 1) return;
        
        for (int i = N / 2 - 1 ; i >= 0 ; i--)
            sink(arr, N, i, cmp);
        
        while (N >= 1) {
            Arrays.swap(arr, 0, --N);
            sink(arr, N, 0, cmp);
        }
    }
    
    public static void sort(Comparable[] arr) {
        Comparator<Comparable> cmp = Comparator.naturalOrder();
        sort(arr, cmp);
    }
    
    public static void main(String[] args) {
        SortUtil.test(HeapSort::sort);
        SortUtil.testComparable(HeapSort::sort);
    }
}
