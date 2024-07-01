/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sort;
import java.util.Comparator;

// for revision
// https://www.coursera.org/learn/algorithms-part1/lecture/1hYlN/insertion-sort

/**
 *
 * @author abhishekchopra
 */
public class InsertionSort {
    
    public static void sort(Object[] arr, Comparator cmp) {
        int N = arr.length;
        for (int i = 1 ; i < N ; i++)
            for (int j = i - 1 ; j >= 0 && SortUtil.less(arr[j + 1], arr[j], cmp) ; j--)
                SortUtil.swap(arr, j, j + 1);
    }
    
    public static void sort(Comparable[] arr) {
        Comparator<Comparable> cmp = Comparator.naturalOrder();
        sort(arr, cmp);
    }
    
    public static void main(String[] args) {
        SortUtil.test(InsertionSort::sort);
        SortUtil.testComparable(InsertionSort::sort);
    }
}
