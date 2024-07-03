/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package symbolTables;

import java.util.EmptyStackException;
import util.Pair;


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
}
