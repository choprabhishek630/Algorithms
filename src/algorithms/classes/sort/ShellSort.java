/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sort;
import java.util.Comparator;
import util.Arrays;

// For revision
// https://www.coursera.org/learn/algorithms-part1/lecture/zPYhF/shellsort

/**
 *
 * @author abhishekchopra
 */
public class ShellSort {
    public static void sort(Object[] arr, Comparator cmp) {
        int h = 1, N = arr.length;
        while (h < N / 3) h = 3 * h + 1;
        while (h > 0) {
            for (int i = h ; i < N ; i++)
                for (int j = i - h ; j >= 0 && Arrays.less(arr[j + h], arr[j], cmp) ; j -= h)
                    Arrays.swap(arr, j, j + h);
            h = h / 3;
        }
    }
    
    public static void sort(Comparable[] arr) {
        Comparator<Comparable> cmp = Comparator.naturalOrder();
        sort(arr, cmp);
    }
    
    public static void main(String[] args) {
        SortUtil.test(ShellSort::sort);
        SortUtil.testComparable(ShellSort::sort);
    }
}
