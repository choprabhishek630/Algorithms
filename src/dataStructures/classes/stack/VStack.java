/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stack;

import vector.Vector;
import java.util.EmptyStackException;
import java.util.function.BiFunction;
import vector.ReverseIterator;
import java.util.Iterator;

/**
 *
 * @author abhishekchopra
 */
public class VStack<T> extends Vector<T> implements Stack<T> {
    @Override
    public boolean swap(int idx1, int idx2) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void sort(BiFunction<? super T, ?super T, Integer> comparator) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void push(T item) {
        super.add(item);
    }
    
    @Override
    public T pop() {
        return super.remove();
    }
    
    @Override
    public T top() {
        if (super.isEmpty()) {
            throw new EmptyStackException();
        }
        return super.at(0);
    }
    
    @Override
    public Iterator<T> iterator() {
        return new ReverseIterator<>(this);
    }
}
