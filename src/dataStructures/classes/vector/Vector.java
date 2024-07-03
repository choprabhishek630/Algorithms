/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vector;
import java.util.Iterator;
import java.util.EmptyStackException;
import java.util.function.BiFunction;

/**
 *
 * @author abhishekchopra
 */
public class Vector<T> implements VectorInterface<T> {
    protected T[] items;
    private int N;
    public static final int INITIAL_SIZE = 4;
    
    public Vector() {
        this.N = 0;
        this.items = (T[]) new Object[INITIAL_SIZE];
    }
    
    @Override
    public void add(T item) {
        if (this.N == items.length) {
            this.resize(this.items.length * 2);
        }
        items[N++] = item;
    }
    
    private void checkEmptyStackException() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
    }
    
    @Override
    public T remove() {
        this.checkEmptyStackException();
        T item = items[--N];
        items[N] = null;
        if (N > 0 && N == this.items.length / 4) {
            this.resize(this.items.length / 2);
        }
        return item;
    }
    
    private void resize(int capacity) {
        T[] copy = (T[]) new Object[capacity];
        System.arraycopy(this.items, 0, copy, 0, this.N);
        this.items = copy;
    }
    
    @Override
    public int size() {
        return this.N;
    }
    
    @Override
    public boolean isEmpty() {
        return N == 0;
    }
    
    private boolean isValidIndex(int idx) {
        return idx >= 0 && idx < this.size();
    }
    
    private void checkOutOfBoundException(int idx) {
        if (!this.isValidIndex(idx)) {
            throw new IndexOutOfBoundsException("Index " + idx + " is out of bounds!");
        }
    }
    
    @Override
    public T at(int idx) {
        this.checkOutOfBoundException(idx);
        return items[idx];
    }
    
    @Override
    public boolean swap(int idx1, int idx2) {
        this.checkOutOfBoundException(idx1);
        this.checkOutOfBoundException(idx2);
        T copy = items[idx1];
        items[idx1] = items[idx2];
        items[idx2] = copy;
        return true;
    }
    
    @Override
    public Iterator<T> iterator() {
        return new VectorIterator<>(this);
    }
    
    @Override
    public void sort(BiFunction<? super T, ?super T, Integer> comparator) {
        // TODO
    }
    
    @Override
    public void replace(T item, int idx) {
        this.checkOutOfBoundException(idx);
        this.items[idx] = item;
    }
}
