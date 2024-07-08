/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package symbolTables;

import java.util.List;
import java.util.ArrayList;
import util.Pair;

/**
 *
 * @author abhishekchopra
 */
public interface OrderedST<Key extends Comparable<Key>, Value> extends SymbolTable<Key, Value> {
    int rank(Key key);
    
    @Override
    Value get(Key key);
    
    Key select(int k);
    
    Value delete(int rank);
    
    default Key min() {
        return this.select(1);
    }
    
    default Key max() {
        return this.select(this.size());
    }
    
    default Key floor(Key key) {
        int rank = this.rank(key);
        
        if (rank > 0) return this.select(rank);
        
        rank *= -1;
        
        if (rank > 1) return this.select(rank - 1);
        
        return null;
    }
    
    default Key ceiling(Key key) {
        int rank = this.rank(key);
        
        if (rank > 0) return this.select(rank);
        
        rank *= -1;
        
        if (rank <= this.size()) return this.select(rank);
        
        return null;
    }
    
    default Pair<Integer, Integer> ranks(Key lo, Key hi) {
        assert lo.compareTo(hi) <= 0;
        if (this.contains(hi)) return new Pair<>(Math.abs(this.rank(lo)), Math.abs(this.rank(hi)));
        else return new Pair<>(Math.abs(this.rank(lo)), Math.abs(this.rank(hi)) - 1);
    }
    
    default int size(Key lo, Key hi) {
        
        Pair<Integer, Integer> ranks = this.ranks(lo, hi);
        return Math.max(0, ranks.second - ranks.first + 1);
    }
    
    default Value deleteMin() {
        return this.delete(1);
    }
    
    default Value deleteMax() {
        return this.delete(this.size());
    }
    
    @Override
    default Value delete(Key key) {
        return this.delete(this.rank(key));
    }
    
    default Iterable<Key> keys(Key lo, Key hi) {
        Pair<Integer, Integer> ranks = this.ranks(lo, hi);
        int size = Math.max(0, ranks.second - ranks.first + 1);
        
        List<Key> list = new ArrayList<>(size);
        
        int idx = 0;
        
        while (idx < size)
            list.add(this.select(ranks.first + idx++));
        
        return list;
    }
    
    static void test(OrderedST<Integer, Integer> st) {
        SymbolTable.test(st);
        while (!st.isEmpty()) {
            st.deleteMin();
        }
        
        
        for (int i = 1 ; i <= 10 ; i++) {
            st.put(i * 2, i * 2);
            assert st.size() == i;
            assert !st.isEmpty();
        }
        
        assert st.min() == 2;
        assert st.max() == 20;
        
        for (int i = 1 ; i <= 21 ; i++) {
            
            if (i < st.min())
                assert st.floor(i) == null;
            else
                assert st.floor(i).equals(i - i % 2);
            
            if (i > st.max())
                assert st.ceiling(i) == null;
            else
                assert st.ceiling(i).equals(i + i % 2) : st.floor(i) + ", " + i;
            
            assert Math.abs(st.rank(i)) == i / 2 + i % 2 : "For idx = " + i + ", Rank = " + st.rank(i);
        }
        
        assert st.size(1, 21) == 10;
        
        assert st.size(1, 11) == 5;
        
        assert st.size(2, 10) == 5;
        
        assert st.size(2, 22) == 10;
        
        assert st.size(2, 7) == 3;
        
        assert st.size(3, 6) == 2;
        
        assert st.deleteMin() == 2;
        assert st.deleteMax() == 20;
        
        assert st.deleteMin() == 4;
        
        assert st.min() == 6 && st.max() == 18;
        
        assert st.size() == 7;
        
        Iterable<Integer> keys = st.keys(3, 8);
        
        int idx = 1;
        for (Integer k : keys) {
            assert k.equals(st.select(idx));
            idx++;
        }
        
        keys = st.keys();
        idx = 1;
        for (Integer k : keys) {
            assert k.equals(st.select(idx));
            idx++;
        }   
        
        System.out.println("Test cases passed for Ordered symbol table");
    }
}

// 1 2 4 7 8
