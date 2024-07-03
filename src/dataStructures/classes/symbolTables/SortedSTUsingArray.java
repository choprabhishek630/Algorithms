/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package symbolTables;

import java.util.Comparator;
import java.util.EmptyStackException;
import vector.Vector;
import java.util.Iterator;
import util.Arrays;
import util.Pair;

/**
 *
 * @author abhishekchopra
 */
public class SortedSTUsingArray<Key extends Comparable<Key>, Value> implements OrderedST<Key, Value> {
    private final Comparator<Key> cmp;
    private final Vector<Key> keys = new Vector<>();
    private final Vector<Value> values = new Vector<>();
    
    private final class STIterator<Key, Value> implements Iterator<Pair<Key, Value>> {
        private final Iterator<Key> keysItr;
        private final Iterator<Value> valuesItr;
        
        public STIterator(Iterator<Key> keysItr, Iterator<Value> valuesItr) {
            this.keysItr = keysItr;
            this.valuesItr = valuesItr;
        }
        
        @Override
        public boolean hasNext() {
            return this.keysItr.hasNext();
        }
        
        @Override
        public Pair<Key, Value> next() {
            return new Pair<Key, Value>(this.keysItr.next(), this.valuesItr.next());
        }
    }
    
    public SortedSTUsingArray() {
        this.cmp = Comparator.naturalOrder();
    }
    
    public SortedSTUsingArray(Comparator<Key> cmp) {
        this.cmp = cmp == null ? Comparator.naturalOrder() : cmp;
    }
    
    @Override
    public int size() {
        return this.keys.size();
    }
    
    @Override
    public int rank(Key key) {
        if (key == null)
            throw new NullPointerException("Key cannot be null (" + key + ")");
        
        int N = this.size();
        int lo = 0, hi = N - 1;
        
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int c = this.cmp.compare(key, keys.at(mid));
            
            if (c < 0)      hi = mid - 1;
            else if (c > 0) lo = mid + 1;
            else            {
                return mid;
            }
        }
        
        return -1;
    }
    
    @Override
    public Value get(Key key) {
        if (this.isEmpty())
            throw new EmptyStackException();
        
        int rank = this.rank(key);
        
        if (rank < 0) return null;
        
        return values.at(rank);
    }
    
    @Override
    public void put(Key key, Value value) {
        if (key == null || value == null)
            throw new NullPointerException("Key and Value cannot be null (" + key + ", " + value + ")");
        
        int rank = this.rank(key);
        
        if (rank >= 0) {
            this.values.replace(value, rank);
            return;
        }
            
        
        this.keys.add(key);
        this.values.add(value);
        
        int idx = this.size() - 1;
        
        while (idx > 0 && this.cmp.compare(key, keys.at(idx - 1)) < 0) {
            this.keys.swap(idx, idx - 1);
            this.values.swap(idx, --idx);
        }
    }
    
    @Override
    public Value delete(Key key) {
        if (this.isEmpty())
            throw new EmptyStackException();
        
        int rank = this.rank(key), N = this.size();
        
        if (rank < 0)
            return null;
        
        Value v = this.values.at(rank);
        
        this.keys.replace(this.keys.at(N - 1), rank);
        this.values.replace(this.values.at(N - 1), rank);
        
        this.keys.remove();
        this.values.remove();
        
        N = this.size();
        
        while (rank + 1 < N && this.cmp.compare(keys.at(rank), keys.at(rank + 1)) > 0) {
            this.keys.swap(rank, rank + 1);
            this.values.swap(rank, ++rank);
        }
        
        return v;
    }
    
    @Override
    public Iterator<Pair<Key, Value>> iterator() {
        return new STIterator<>(this.keys.iterator(), this.values.iterator());
    }
    
    public static void main(String[] args) {
        int N = 20;
        Integer[] keys = Arrays.generateRandomArr(N);
        Integer[] values1 = Arrays.generateRandomArr(N);
        Integer[] values2 = Arrays.generateRandomArr(N);
        
        SymbolTable<Integer, Integer> st = new SortedSTUsingArray<>();
        
        for (int i = 0 ; i < N ; i++) {
            st.put(keys[i], values1[i]);
        }
        
        for (int i = 1 ; i < N ; i += 2) {
            st.put(keys[i], values2[i]);
        }
        
        Arrays.print(keys);
        Arrays.print(values1);
        Arrays.print(values2);
        
        Arrays.print(st.iterator());
        
        System.out.println(st.contains(10));
        
        System.out.println(st.size());
        System.out.println(st.isEmpty());
        
        System.out.println(st.get(10));
        
        System.out.println(st.delete(10));
        
        System.out.println(st.contains(10));
        
        System.out.println(st.size());
        System.out.println(st.isEmpty());
        
        System.out.println(st.get(10));
        
        Arrays.print(st.iterator());
        
        for (int i = 1 ; i <= 20 ; i++) {
            System.out.print(st.delete(i) + ", ");
        }
        System.out.println();
        
        System.out.println(st.size());
        System.out.println(st.isEmpty());
        
        System.out.println(st.get(10));
        
        Arrays.print(st.iterator());
    }
}
