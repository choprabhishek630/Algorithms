/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package symbolTables;

import java.util.Comparator;
import java.util.EmptyStackException;
import vector.Vector;
import java.util.Iterator;
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
            return new Pair<>(this.keysItr.next(), this.valuesItr.next());
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
    public Key select(int rank) {
        if (!this.isValidIndex(rank - 1))
            throw new IndexOutOfBoundsException("Rank " + rank + " is out of bounds!");
        
        return this.keys.at(rank - 1);
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
                return mid + 1;
            }
        }
        
        return (lo + 1) * -1;
    }
    
    @Override
    public Value get(Key key) {
        if (this.isEmpty())
            throw new EmptyStackException();
        
        int rank = this.rank(key);
        
        if (rank < 0) return null;
        
        return values.at(rank - 1);
    }
    
    @Override
    public void put(Key key, Value value) {
        if (key == null || value == null)
            throw new NullPointerException("Key and Value cannot be null (" + key + ", " + value + ")");
        
        int rank = this.rank(key);
        
        if (rank > 0) {
            this.values.replace(value, rank - 1);
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
    
    public Value delete(int rank) {
        if (this.isEmpty())
            throw new EmptyStackException();
        
        if (rank < 0) return null;
        
        if (!this.isValidIndex(rank - 1))
            throw new IndexOutOfBoundsException("Rank " + rank + " is out of bounds!");
        
        int idx = rank - 1, N = this.size();
        
        while (idx + 1 < N) {
            this.keys.swap(idx, idx + 1);
            this.values.swap(idx, ++idx);
        }
        
        this.keys.remove();
        return this.values.remove();
        
    }
    
    @Override
    public Iterator<Pair<Key, Value>> iterator() {
        return new STIterator<>(this.keys.iterator(), this.values.iterator());
    }
    
    public static void main(String[] args) {
        OrderedST.test(new SortedSTUsingArray<>());
    }
}
