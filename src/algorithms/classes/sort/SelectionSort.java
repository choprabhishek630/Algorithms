/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sort;
import java.util.Comparator;
import util.Arrays;

// for revision
// https://www.coursera.org/learn/algorithms-part1/lecture/VE0sv/selection-sort

/**
 *
 * @author abhishekchopra
 */
public class SelectionSort {
    
    public static void sort(Object[] arr, Comparator cmp) {
        int N = arr.length;
        for (int i = 0 ; i < N - 1 ; i++) {
            int minIdx = i;
            for (int j = i + 1 ; j < N ; j++)
                if (Arrays.less(arr[j], arr[minIdx], cmp)) minIdx = j;
            Arrays.swap(arr, i, minIdx);
        }
    }
    
    public static void sort(Comparable[] arr) {
        Comparator<Comparable> cmp = Comparator.naturalOrder();
        sort(arr, cmp);
    }
    
    public static void main(String[] args) {
        SortUtil.test(SelectionSort::sort);
        SortUtil.testComparable(SelectionSort::sort);
    }
}
