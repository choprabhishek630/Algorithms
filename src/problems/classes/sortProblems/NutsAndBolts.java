/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sortProblems;

import util.Arrays;

/**
 *
 * @author abhishekchopra
 */
public class NutsAndBolts {
    private final static int[] testCases = {0, 1, 2, 3, 10, 20, 50, 100};
    
    public static class Bolt implements Comparable<Nut> {
        private final int size;

        public Bolt(int size) {
            this.size = size;
        }

        public int getSize() {
            return this.size;
        }
        
        @Override
        public int compareTo(Nut n) {
            return this.size - n.getSize();
        }
    }
    
    public static class Nut implements Comparable<Bolt> {
        private final int size;

        public Nut(int size) {
            this.size = size;
        }

        public int getSize() {
            return this.size;
        }
        
        @Override
        public int compareTo(Bolt b) {
            return this.size - b.getSize();
        }
    }
    
    private static int partition(Nut[] nuts, Bolt[] bolts, int l, int r) {
        int lt = l, rt = r, i = l;
        
        Nut comparable = nuts[l];
        
        while (i <= rt) {
            int cmp = bolts[i].compareTo(comparable);
            
            if (cmp > 0)        Arrays.swap(bolts, i, rt--);
            else if (cmp < 0)   Arrays.swap(bolts, i++, lt++);
            else                i++;
        }
        
        assert comparable.compareTo(bolts[lt]) == 0;
        assert lt == rt;
        
        
        Bolt pivot = bolts[lt];
        lt = l;
        rt = r;
        i = l + 1;
        
        while (i <= rt) {
            int cmp = nuts[i].compareTo(pivot);
            
            if (cmp > 0)        Arrays.swap(nuts, i, rt--);
            else if (cmp < 0)   Arrays.swap(nuts, i++, lt++);
            else                i++;
        }
        
        assert nuts[lt].compareTo(bolts[lt]) == 0;
        assert lt == rt;
        
        return lt;
    }
    
    private static void sort(Nut[] nuts, Bolt[] bolts, int l, int r) {
        if (l >= r) return;
        int pivot = partition(nuts, bolts, l, r);
        
        sort(nuts, bolts, l, pivot - 1);
        sort(nuts, bolts, pivot + 1, r);
    }
    
    public static void sort(Nut[] nuts, Bolt[] bolts) {
        assert nuts.length == bolts.length;
        sort(nuts, bolts, 0, nuts.length - 1);
    }
    
    public static boolean isSorted(Nut[] nuts, Bolt[] bolts) {
        int N = nuts.length;
        assert nuts.length == bolts.length;
        
        for (int i = 0 ; i < N ; i++) {
            if (nuts[i].compareTo(bolts[i]) != 0) return false;
            if (i > 0 && nuts[i - 1].compareTo(bolts[i]) > 0) return false;
        }
        
        return true;
    }
    
    public static boolean testCase(int N) {
        Integer[] arr = Arrays.generateRandomArr(N);
        Nut[] nuts = new Nut[N];
        Bolt[] bolts = new Bolt[N];
        for (int i = 0 ; i < N ; i++) {
            nuts[i] = new Nut(arr[i]);
            bolts[i] = new Bolt(arr[i]);
        }
        
        Arrays.shuffle(nuts);
        Arrays.shuffle(bolts);
        
        sort(nuts, bolts);
        
        return isSorted(nuts, bolts);
    }
    
    public static void test() {
        int N = testCases.length;
        int testCasesPassed = 0;
        for (int i = 0 ; i < N ; i++) {
            if (testCase(testCases[i])) {
                testCasesPassed++;
                System.out.println("Test Case " + (i + 1) + " => passed");
            } else {
                System.out.println("Test Case " + (i + 1) + " => failed");
            }
        }
        
        System.out.println(testCasesPassed + " out of " + N + " test cases passed");
    }
    
    public static void main(String[] args) {
        test();
    }
}
