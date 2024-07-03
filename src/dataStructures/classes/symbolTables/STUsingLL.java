/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package symbolTables;

import linkedList.LinkedList;
import linkedList.SingleLL;
import util.Pair;
import java.util.EmptyStackException;
import java.util.Iterator;
import util.Arrays;

/**
 *
 * @author abhishekchopra
 */
public class STUsingLL<Key, Value> implements SymbolTable<Key, Value> {
    private final LinkedList<Pair<Key, Value>> ll;
    
    public STUsingLL() {
        this.ll = new SingleLL<>();
    }
    
    @Override
    public int size() {
        return this.ll.size();
    }
    
    private Pair<Key, Value> getPair(Key key) {
        if (key == null)
            throw new NullPointerException("Key cannot be null (" + key + ")");
        
        for (Pair<Key, Value> kvNode : this) {
            if (kvNode.first.equals(key))
                return kvNode;
        }
        return null;
    }
    
    @Override
    public void put(Key key, Value value) {
        if (key == null || value == null)
            throw new NullPointerException("Key and Value cannot be null (" + key + ", " + value + ")");
        
        Pair<Key, Value> kv = this.getPair(key);
        
        if (kv != null) {
            kv.second = value;
            return;
        }
        
        kv = new Pair<>(key, value);
        
        this.ll.add(kv);
    }
    
    @Override
    public Value delete(Key key) {
        if (this.isEmpty())
            throw new EmptyStackException();
        if (key == null)
            throw new NullPointerException("Key cannot be null (" + key + ")");
        
        int idx = 0;
        for (Pair<Key, Value> kv : this.ll) {
            if (kv.first.equals(key))
                return this.ll.remove(idx).second;
            idx++;
        }
        
        return null;
    }
    
    @Override
    public Iterator<Pair<Key, Value>> iterator() {
        return this.ll.iterator();
    }
    
    public static void main(String[] args) {
        int N = 20;
        Integer[] keys = Arrays.generateRandomArr(N);
        Integer[] values1 = Arrays.generateRandomArr(N);
        Integer[] values2 = Arrays.generateRandomArr(N);
        
        SymbolTable<Integer, Integer> st = new STUsingLL<>();
        
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
