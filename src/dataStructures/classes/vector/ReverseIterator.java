/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vector;

import java.util.Iterator;

/**
 *
 * @author abhishekchopra
 */
public class ReverseIterator<T> implements Iterator<T> {
    private int current;
    private final Vector<T> v;
    public ReverseIterator(Vector<T> v) {
        this.current = v.size() - 1;
        this.v = v;
    }
    
    @Override
    public boolean hasNext() {
        return this.current >= 0;
    }
    
    @Override
    public T next() {
        return this.v.items[current--];
    }
}
