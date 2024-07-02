/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sort;

import java.util.Comparator;
import geometry.Pair;


// For Revision
// https://www.coursera.org/learn/algorithms-part1/quiz/iH9u8/interview-questions-quicksort-ungraded/attempt

/**
 *
 * @author abhishekchopra
 */
public class QuickSort {
    
    
    // simple partition method as explained in
    // https://www.coursera.org/learn/algorithms-part1/lecture/vjvnC/quicksort
    private static int partition(Object[] arr, Comparator cmp, int lo, int hi) {
        int l = lo + 1, r = hi;
        while (true) {
            while (l <= hi && SortUtil.less(arr[l], arr[lo], cmp))
                l++;
            
            while (r > lo && SortUtil.less(arr[lo], arr[r], cmp))
                r--;
            
            if (l >= r) break;
            SortUtil.swap(arr, l++, r--);
            
        }
        SortUtil.swap(arr, lo, r);
        return r;
    }
    
    private static Pair<Integer, Integer> threeWayPartition(Object[] arr, Comparator cmp, int lo, int hi) {
        int lt = lo, rt = hi, i = lo + 1;
        Object comparable = arr[lo];
        
        while (i <= rt) {
            int c = cmp.compare(arr[i], comparable);
            
            if (c < 0)      SortUtil.swap(arr, i++, lt++);
            else if (c > 0) SortUtil.swap(arr, i, rt--);
            else            i++;
        }
        
        return new Pair(lt, rt);
    }
    
    private static void sort(Object[] arr, Comparator cmp, int lo, int hi) {
        if (hi <= lo) return;
        int k = partition(arr, cmp, lo, hi);
        sort(arr, cmp, lo, k - 1);
        sort(arr, cmp, k + 1, hi);
    }
    
    public static void sort(Object[] arr, Comparator cmp) {
        SortUtil.shuffle(arr);
        sort(arr, cmp, 0, arr.length - 1);
    }
    
    public static void sort(Comparable[] arr) {
        Comparator<Comparable> cmp = Comparator.naturalOrder();
        sort(arr, cmp);
    }
    
    
    // Quick select method to get the kth element in the sorted array
    // for revision https://www.coursera.org/learn/algorithms-part1/lecture/UQxFT/selection
    public static Object select(Object[] arr, int k, Comparator cmp) {
        SortUtil.shuffle(arr);
        int lo = 0, hi = arr.length - 1;
        while (hi > lo) {
            int pivot = partition(arr, cmp, lo, hi);
            if (pivot < k) lo = pivot + 1;
            else if (pivot > k) hi = pivot - 1;
            else return arr[pivot];
        }
        return arr[k];
    }
    
    // Quick select method to get the kth element in the sorted array
    // for revision https://www.coursera.org/learn/algorithms-part1/lecture/UQxFT/selection
    public static Pair<Object, Pair<Integer, Integer>> threeWayselect(Object[] arr, int k, Comparator cmp) {
        SortUtil.shuffle(arr);
        int lo = 0, hi = arr.length - 1;
        while (hi > lo) {
            Pair<Integer, Integer> bounds = threeWayPartition(arr, cmp, lo, hi);
            if (bounds.second < k) lo = bounds.second + 1;
            else if (bounds.first > k) hi = bounds.first - 1;
            else return new Pair(arr[k], bounds);
        }
        return new Pair(arr[k], new Pair(lo, hi));
    }
    
    // for revision https://www.coursera.org/learn/algorithms-part1/lecture/XvjPd/duplicate-keys
    private static void threeWaySort(Object[] arr, Comparator cmp, int lo, int hi) {
        if (lo >= hi) return;
        Pair<Integer, Integer> bounds = threeWayPartition(arr, cmp, lo, hi);
        
        sort(arr, cmp, lo, bounds.first - 1);
        sort(arr, cmp, bounds.second + 1, hi);
    }
    
    public static void threeWaySort(Object[] arr, Comparator cmp) {
        SortUtil.shuffle(arr);
        threeWaySort(arr, cmp, 0, arr.length - 1);
    }
    
    public static Comparable select(Comparable[] arr, int k) {
        Comparator<Comparable> cmp = Comparator.naturalOrder();
        return (Comparable) (select(arr, k, cmp));
    }
    
    public static Comparable threeWaySelect(Comparable[] arr, int k) {
        Comparator<Comparable> cmp = Comparator.naturalOrder();
        return (Comparable) (threeWayselect(arr, k, cmp));
    }
    
    public static void main(String[] args) {
        SortUtil.test(QuickSort::sort);
        SortUtil.testComparable(QuickSort::sort);
        SortUtil.test(QuickSort::threeWaySort);
    }
}
