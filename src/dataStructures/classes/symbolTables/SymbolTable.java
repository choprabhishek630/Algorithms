/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package symbolTables;

import java.util.EmptyStackException;
import util.Arrays;
import util.Pair;
import java.util.ArrayList;
import java.util.List;


// for revision
// https://www.coursera.org/learn/algorithms-part1/lecture/7WFvG/symbol-table-api

/**
 *
 * @author abhishekchopra
 */
public interface SymbolTable<Key, Value> extends Iterable<Pair<Key, Value>> {
    void put(Key key, Value value);
    Value delete(Key key);
    int size();
    
    default boolean contains(Key key) {
        return this.get(key) != null;
    }
    
    default boolean isEmpty() {
        return this.size() <= 0;
    }
    
    default Value get(Key key) {
        if (this.isEmpty())
            throw new EmptyStackException();
        
        if (key == null)
            throw new NullPointerException("Key cannot be null (" + key + ")");
        
        for (Pair<Key, Value> kVPair : this) {
            if (kVPair.first.equals(key))
                return kVPair.second;
        }
        return null;
    }
    
    default boolean isValidIndex(int idx) {
        return idx >= 0 && idx < this.size();
    }
    
    default Iterable<Key> keys() {
        List<Key> list = new ArrayList<>(this.size());
        for (Pair<Key, Value> kv : this)
            list.add(kv.first);
        return list;
    }
    
    static void test(SymbolTable<Integer, Integer> st) {
        int N = 20;
        Integer[] keys = Arrays.generateRandomArr(N);
        Integer[] values1 = Arrays.generateRandomArr(N);
        Integer[] values2 = Arrays.generateRandomArr(N);
        
        for (int i = 0 ; i < N ; i++) {
            st.put(keys[i], values1[i]);
        }
        
        for (int i = 1 ; i < N ; i += 2) {
            st.put(keys[i], values2[i]);
        }
        
        assert st.size() == N : "Incorrect size" + st.size();
        assert !st.isEmpty() : "Stack is not empty";
        
        for (int i = 0 ; i < N ; i++) {
            Integer v = st.get(keys[i]);
            assert v != null : "(Key = " + keys[i] + ", I = " + i + ")";
            if (i % 2 == 1)
                assert v.equals(values2[i]) : "(V = " + v + ", V1 = " + values1[i] + ", V2 = " + values1[i] + ", I = " + i + ")";
            else
                assert v.equals(values1[i]) : "(V = " + v + ", V1 = " + values1[i] + ", V2 = " + values1[i] + ", I = " + i + ")";;
        }
        
        Arrays.print(st.iterator());
        
        assert st.contains(10);
        
        assert st.size() == N;
        assert !st.isEmpty();
        
        assert st.get(10) != null;
        
        assert st.delete(10) != null;
        
        assert !st.contains(10);
        
        assert st.size() == N - 1;
        
        assert !st.isEmpty();
        
        assert st.get(10) == null;
        
        for (int i = 1 ; i <= 20 ; i++) {
            if (i != 10)
                assert st.delete(i) != null;
            else
                assert st.delete(i) == null;
        }
        
        assert st.size() == 0;
        
        assert st.isEmpty();
        
        System.out.println("Test cases passed for Generic symbol table");
    }
}
