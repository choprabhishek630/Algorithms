/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sort;
import java.util.Comparator;
import priorityQueues.BinaryHeap;
import priorityQueues.PriorityQueue;

/**
 *
 * @author abhishekchopra
 */
public class HeapSort {
    public static void sort(Object[] arr, Comparator cmp) {
        int N = arr.length;
        PriorityQueue<Object> pq = new BinaryHeap<>(cmp.reversed());
        for (Object item : arr) {
            pq.add(item);
        }
        
        for (int i = 0 ; i < N ; i++) {
            arr[i] = pq.remove();
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
