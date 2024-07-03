/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package priorityQueues;

import vector.Vector;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Random;

// for revision
// https://www.coursera.org/learn/algorithms-part1/lecture/Uzwy6/binary-heaps

/**
 *
 * @author abhishekchopra
 */
public class BinaryHeap<T> implements PriorityQueue<T> {
    private final Vector<T> pq;
    private final Comparator<T> cmp;
    
    
    // TODO
//    private final class TreeIterator<T> implements Iterator<T> {
//        private final Vector<T> pq;
//        private int id;
//        
//        public TreeIterator(Vector<T> pq) {
//            this.pq = pq;
//            this.id = 1;
//        }
//        
//        @Override
//        public boolean hasNext() {
//            return this.id < this.pq.size() && this.id > 0;
//        }
//        
//        @Override
//        public T next() {
//            T item = this.pq.at(this.id);
//                
//            int copy = this.id;
//            while (this.id > 0) {
//                if (this.id * 2 < this.pq.size() && this.id * 2 != copy) {
//                    this.id *= 2;
//                    return item;
//                } else if () {
//                
//                }
//            }
//            
//            return item;
//        }
//    }
    
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
    
    private boolean isValidIndex(int idx) {
        return idx <= this.size() || idx > 0;
    }
    
    @Override
    public T remove(int idx) {
        if (this.isEmpty())
            throw new EmptyStackException();
        if (!isValidIndex(idx))
            throw new IndexOutOfBoundsException("Index " + idx + " is out of bound!");
        this.pq.swap(idx, this.size());
        T max = this.pq.remove();
        this.sink(idx);
        return max;
    }
    
    @Override
    public int sample() {
        if (this.isEmpty()) return 0;
        Random rand = new Random();
        return rand.nextInt(this.size()) + 1;
    }
    
    @Override
    public T remove() {
        return this.remove(1);
    }
    
    @Override
    public T delRandom() {
        return this.remove(this.sample());
    }
    
    @Override
    public Iterator<T> iterator() {
        Iterator<T> itr = this.pq.iterator();
        if (itr.hasNext()) itr.next();
        return itr;
    }
    
    // TODO
//    public Iterator<T> treeIterator() {
//        return new TreeIterator<>(this.pq);
//    }
}

/*
                    10
          9                     8
     7         6          1          2
   4   5     3 


*/
