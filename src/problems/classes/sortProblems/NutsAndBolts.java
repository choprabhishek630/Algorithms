/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sortProblems;

import sort.SortUtil;

/**
 *
 * @author abhishekchopra
 */
public class NutsAndBolts {
    private final static int[] testCases = {0, 1, 2, 3, 10, 20, 50, 100};
    
    public static class Bolt {
        private final int size;

        public Bolt(int size) {
            this.size = size;
        }

        public int getSize() {
            return this.size;
        }

        public int compare(Nut n) {
            return this.size - n.getSize();
        }
    }
    
    public static class Nut {
        private final int size;

        public Nut(int size) {
            this.size = size;
        }

        public int getSize() {
            return this.size;
        }

        public int compare(Bolt b) {
            return this.size - b.getSize();
        }
    }
    
    private static int partition(Nut[] nuts, Bolt[] bolts, int l, int r) {
        int lt = l, rt = r, i = l;
        
        Nut comparable = nuts[l];
        
        while (i <= rt) {
            int cmp = bolts[i].compare(comparable);
            
            if (cmp > 0)        SortUtil.swap(bolts, i, rt--);
            else if (cmp < 0)   SortUtil.swap(bolts, i++, lt++);
            else                i++;
        }
        
        assert comparable.compare(bolts[lt]) == 0;
        assert lt == rt;
        
        
        Bolt pivot = bolts[lt];
        lt = l;
        rt = r;
        i = l + 1;
        
        while (i <= rt) {
            int cmp = nuts[i].compare(pivot);
            
            if (cmp > 0)        SortUtil.swap(nuts, i, rt--);
            else if (cmp < 0)   SortUtil.swap(nuts, i++, lt++);
            else                i++;
        }
        
        assert nuts[lt].compare(bolts[lt]) == 0;
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
            if (nuts[i].compare(bolts[i]) != 0) return false;
            if (i > 0 && nuts[i - 1].compare(bolts[i]) > 0) return false;
        }
        
        return true;
    }
    
    public static boolean testCase(int N) {
        Integer[] arr = SortUtil.generateRandomArr(N);
        Nut[] nuts = new Nut[N];
        Bolt[] bolts = new Bolt[N];
        for (int i = 0 ; i < N ; i++) {
            nuts[i] = new Nut(arr[i]);
            bolts[i] = new Bolt(arr[i]);
        }
        
        SortUtil.shuffle(nuts);
        SortUtil.shuffle(bolts);
        
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
