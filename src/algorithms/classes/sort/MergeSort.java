/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sort;
import java.util.Comparator;

/**
 *
 * @author abhishekchopra
 */
public class MergeSort {
    
    private static void copy(Object[] arr, Object[] aux, int start, int end) {
        for (int i = start ; i <= end ; i++) {
            aux[i] = arr[i];
        }
    }
    
    private static void merge(Object[] arr, Object[] aux, Comparator cmp, int lo, int mid, int hi, boolean copy) {
        if (copy)
            copy(arr, aux, lo, hi);
        
        int i = lo, j = mid + 1, k = lo;
        while (i <= mid && j <= hi) {
            if (cmp.compare(aux[i], aux[j]) <= 0)   arr[k++] = aux[i++];
            else                                    arr[k++] = aux[j++];
        }
        
        while (i <= mid)
            arr[k++] = aux[i++];
        
        while (j <= hi)
            arr[k++] = aux[j++];
    }
    
    private static void sort(Object[] arr, Object[] aux, Comparator cmp, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        
        // reverse the role of aux array and main array so that we don't need to copy items in merge process
        // and we copy them at the time of initialisation
        sort(aux, arr, cmp, lo, mid);
        sort(aux, arr, cmp, mid + 1, hi);
        
        // copy is sent false so because the roles of aux and main array were reversed in the recursive calls on subarrays
        merge(arr, aux, cmp, lo, mid, hi, false);
    }
    
    public static void sort(Object[] arr, Comparator cmp) {
        Object[] aux = new Object[arr.length];
        copy(arr, aux, 0, arr.length - 1);
        sort(arr, aux, cmp, 0, arr.length - 1);
    }
    
    public static void sortBottomsUp(Object[] arr, Comparator cmp) {
        int N = arr.length;
        Object[] aux = new Object[N];
        for (int size = 1 ; size < N ; size += size)
            for (int i = 0 ; i + size < N ; i += size * 2)
                merge(arr, aux, cmp, i, i + size - 1, Math.min(N - 1, i + size * 2 - 1), true);
    }
    
    public static void sortBottomsUp(Comparable[] arr) {
        Comparator<Comparable> cmp = Comparator.naturalOrder();
        sortBottomsUp(arr, cmp);
    }
    
    public static void sort(Comparable[] arr) {
        Comparator<Comparable> cmp = Comparator.naturalOrder();
        sort(arr, cmp);
    }
    
    public static void main(String[] args) {
        SortUtil.test(MergeSort::sort);
        SortUtil.testComparable(MergeSort::sort);
        SortUtil.test(MergeSort::sortBottomsUp);
        SortUtil.testComparable(MergeSort::sortBottomsUp);
    }
}
