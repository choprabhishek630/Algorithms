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
class VectorIterator<T> implements Iterator<T> {
    private int current;
    private final Vector<T> v;
    public VectorIterator(Vector<T> v) {
        this.current = 0;
        this.v = v;
    }
    
    @Override
    public boolean hasNext() {
        return this.current < this.v.size();
    }
    
    @Override
    public T next() {
        return this.v.items[current++];
    }
}
