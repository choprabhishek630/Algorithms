/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package symbolTables;

import java.util.EmptyStackException;
import java.util.Iterator;
import linkedList.Node;
import util.Pair;

// Hash Tables using separate chaining technique
// for revision https://www.coursera.org/learn/algorithms-part1/lecture/qFJf7/separate-chaining
// https://www.coursera.org/learn/algorithms-part1/lecture/CMLqa/hash-tables

/**
 *
 * @author abhishekchopra
 */
public class HashMapSC<Key, Value> implements SymbolTable<Key, Value> {
    private static final int INITIAL_CAPACITY = 8;
    private int M;
    private int N;
    private Node<Pair<Key, Value>>[] arr;
    
    private static final class HashMapIterator<K, V> implements Iterator<Pair<K, V>> {
        private final Node<Pair<K, V>>[] arr;
        private int idx;
        private Node<Pair<K, V>> curr;
        
        public HashMapIterator(Node<Pair<K, V>>[] arr) {
            this.arr = arr;
            this.idx = -1;
            this.curr = null;
            this.findNext();
        }
        
        public void findNext() {
            if (this.curr != null)
                this.curr = this.curr.next;
            if (this.curr != null) return;
            while (++this.idx < this.arr.length && this.curr == null) {
                this.curr = this.arr[this.idx];
            }
        }
        
        @Override
        public boolean hasNext() {
            return this.curr != null;
        }
        
        @Override
        public Pair<K, V> next() {
            return new Pair<>(this.curr.item.first, this.curr.item.second);
        }
    }
    
    public HashMapSC() {
        this.M = HashMapSC.INITIAL_CAPACITY;
        this.N = 0;
        this.arr = (Node<Pair<Key, Value>>[])(new Node[HashMapSC.INITIAL_CAPACITY]);
    }
    
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % this.M;
    }
    
    private Node<Pair<Key, Value>> addInFront(Node<Pair<Key, Value>> head, Key key, Value value) {
        Node<Pair<Key, Value>> newNode = new Node<>(new Pair<>(key, value));
        newNode.next = head;
        return newNode;
    }
    
    private void resize(int capacity) {
        if (capacity < HashMapSC.INITIAL_CAPACITY) return;
        Node<Pair<Key, Value>>[] copy = this.arr;
        this.arr = (Node<Pair<Key, Value>>[])(new Object[capacity]);
        this.M = capacity;
        
        for (Pair<Key, Value> item : this)
            this.put(item.first, item.second);
    }
    
    @Override
    public int size() {
        return this.N;
    }
    
    @Override
    public Value get(Key key) {
        if (this.isEmpty())
            throw new EmptyStackException();
        
        if (key == null)
            throw new NullPointerException("Key cannot be null (" + key + ")");
        
        int idx = this.hash(key);
        Node<Pair<Key, Value>> curr = this.arr[idx];
        
        while (curr != null) {
            if (curr.item.first.equals(key)) return curr.item.second;
            curr = curr.next;
        }
        return null;
    }
    
    @Override
    public void put(Key key, Value value) {
        if (key == null || value == null)
            throw new NullPointerException("Key or Value cannot be null (" + key + ", " + value + ")");
        int idx = this.hash(key);
        Node<Pair<Key, Value>> curr = this.arr[idx];
        
        while (curr != null) {
            if (curr.item.first.equals(key)) {
                curr.item.second = value;
                return;
            }
            curr = curr.next;
        }
        
        this.arr[idx] = this.addInFront(this.arr[idx], key, value);
        this.N++;
        if (this.M * 8 == this.N)
            this.resize(2 * this.M);
    }
    
    @Override
    public Value delete(Key key) {
        if (this.isEmpty())
            throw new EmptyStackException();
        
        if (key == null)
            throw new NullPointerException("Key cannot be null (" + key + ")");
        
        int idx = this.hash(key);
        Node<Pair<Key, Value>> curr = this.arr[idx];
        Node<Pair<Key, Value>> prev = null;
        while (curr != null) {
            if (curr.item.first.equals(key)) {
                if (prev != null) {
                    prev.next = curr.next;
                    curr.next = null;
                } else {
                    this.arr[idx] = curr.next;
                    curr.next = null;
                }
                this.N--;
                if (this.M * 2 == this.N)
                    this.resize(this.M / 2);
                return curr.item.second;
            }
            prev = curr;
            curr = curr.next;
        }
        
        return null;
    }
    
    @Override
    public Iterator<Pair<Key, Value>> iterator() {
        return new HashMapIterator<>(this.arr);
    }
    
    public static void main(String[] args) {
        SymbolTable.test(new HashMapSC<>());
    }
}
