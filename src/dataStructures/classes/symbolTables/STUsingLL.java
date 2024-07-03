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
        SymbolTable.test(new STUsingLL<>());
    }
}
