/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package priorityQueues;

import vector.Vector;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.Iterator;

// for revision
// https://www.coursera.org/learn/algorithms-part1/lecture/Uzwy6/binary-heaps

/**
 *
 * @author abhishekchopra
 */
public class BinaryHeap<T> implements PriorityQueue<T> {
    private final Vector<T> pq;
    private final Comparator<T> cmp;
    
    public BinaryHeap(Comparator<T> cmp) {
        this.cmp = cmp;
        this.pq = new Vector<>();
        this.pq.add(null);
    }
    
    @Override
    public int size() {
        return this.pq.size() - 1;
    }
    
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
    private boolean less(int i, int j) {
        return this.cmp.compare(this.pq.at(i), this.pq.at(j)) < 0;
    }
    
    private void swim(int k) {
        while (k > 1 && this.less(k / 2, k)) {
            this.pq.swap(k, k / 2);
            k = k / 2;
        }
    }
    
    private void sink(int k) {
        while (2 * k <= this.size()) {
            int j = k * 2;
            if (j < this.size() && this.less(j, j + 1)) j++;
            if (!this.less(k, j)) break;
            this.pq.swap(j, k);
            k = j;
        }
    }
    
    @Override
    public void add(T item) {
        this.pq.add(item);
        this.swim(this.size());
    }
    
    @Override
    public T peek() {
        if (this.isEmpty())
            throw new EmptyStackException();
        return this.pq.at(1);
    }
    
    @Override
    public T remove() {
        if (this.isEmpty())
            throw new EmptyStackException();
        this.pq.swap(1, this.size());
        T max = this.pq.remove();
        this.sink(1);
        return max;
    }
    
    @Override
    public Iterator<T> iterator() {
        return this.pq.iterator();
    }
    
}
