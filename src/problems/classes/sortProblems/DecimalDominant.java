/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sortProblems;

import geometry.Pair;
import sort.QuickSort;
import java.util.Comparator;

// for revision
// https://www.coursera.org/learn/algorithms-part1/quiz/iH9u8/interview-questions-quicksort-ungraded/attempt
// for revision https://www.coursera.org/learn/algorithms-part1/lecture/UQxFT/selection

/**
 *
 * @author abhishekchopra
 */
public class DecimalDominant {
    public static Integer[] findAll(Integer[] arr) {
        int N = arr.length;
        
        if (N < 10) return arr;
        
        Integer[] result = new Integer[10];
        int dominantsFound = 0;
        
        int indexToSearch = N / 10;
        
        while (indexToSearch < N) {
            Comparator<Integer> cmp = Comparator.naturalOrder();
            Pair<Object, Pair<Integer, Integer>> item = QuickSort.threeWayselect(arr, indexToSearch, cmp);
            
            Pair<Integer, Integer> bounds = item.second;
            
            if ((bounds.second - bounds.first + 1) > N / 10)
                result[dominantsFound++] = (Integer) item.first;
        }
        return result;
    }
}
