/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package priorityQueueProblems;

import priorityQueues.BinaryHeap;
import priorityQueues.PriorityQueue;
import java.util.Comparator;
import collection.MyCollection;
import java.util.Iterator;
import sort.SortUtil;

// for revision
// https://www.coursera.org/learn/algorithms-part1/quiz/rOobS/interview-questions-priority-queues-ungraded/view-attempt

/**
 *
 * @author abhishekchopra
 */
public class DynamicMedian<T> implements MyCollection<T> {
    private final PriorityQueue<T> minPQ;
    private final PriorityQueue<T> maxPQ;
    private final Comparator<T> cmp;
    
    private final class DynamicMedianIterator<T> implements Iterator<T> {
        Iterator<T> minIterator;
        Iterator<T> maxIterator;
        
        public DynamicMedianIterator(DynamicMedian<T> dm) {
            this.minIterator = dm.minPQ.iterator();
            this.maxIterator = dm.maxPQ.iterator();
        }
        
        @Override
        public boolean hasNext() {
            return 
                    this.maxIterator.hasNext() ||
                    this.minIterator.hasNext();
        }
        
        @Override
        public T next() {
            if (this.maxIterator.hasNext())
                return this.maxIterator.next();
            else
                return this.minIterator.next();
        }
    }
    
    public DynamicMedian(Comparator<T> cmp) {
        this.cmp = cmp;
        this.minPQ = new BinaryHeap(cmp.reversed());
        this.maxPQ = new BinaryHeap(cmp);
    }
    
    @Override
    public int size() {
        return this.minPQ.size() + this.maxPQ.size();
    }
    
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
    private void addToMaxQueue(T item) {
        T minPQItem = null;
        
        if (!this.minPQ.isEmpty())
            minPQItem = this.minPQ.peek();
        
        int c = -1;
        
        if (minPQItem != null)
            c = this.cmp.compare(item, minPQItem);
        
        if (c > 0) {
            this.minPQ.remove();
            this.maxPQ.add(minPQItem);
            this.minPQ.add(item);
        } else {
            this.maxPQ.add(item);
        }
    }
    
    private void addToMinQueue(T item) {
        T maxPQItem = null;
        
        if (!this.maxPQ.isEmpty())
            maxPQItem = this.maxPQ.peek();
        
        int c = 1;
        
        if (maxPQItem != null)
            c = this.cmp.compare(item, maxPQItem);
        
        if (c < 0) {
            this.maxPQ.remove();
            this.minPQ.add(maxPQItem);
            this.maxPQ.add(item);
        } else {
            this.minPQ.add(item);
        }
    }
    
    @Override
    public void add(T item) {
        if (this.size() % 2 == 0)   this.addToMaxQueue(item);
        else                        this.addToMinQueue(item);
    }
    
    @Override
    public T remove() {
        T item = this.maxPQ.remove();
        if (this.size() % 2 == 1) {
            T minPQItem = this.minPQ.remove();
            this.maxPQ.add(minPQItem);
        }
        return item;
    }
    
    public T median() {
        return this.maxPQ.peek();
    }
    
    @Override
    public Iterator<T> iterator() {
        return new DynamicMedianIterator<>(this);
    }
    
    public static void main(String[] args) {
        Integer[] arr = SortUtil.generateRandomArr(20);
        
        Comparator<Integer> cmp = Comparator.naturalOrder();
        
        DynamicMedian<Integer> dm = new DynamicMedian<>(cmp);
        
        for (Integer arr1 : arr) {
            dm.add(arr1);
            System.out.println(arr1 + ", " + dm.median());
        }
        
        for (Integer item : dm.maxPQ)
            System.out.print(item + ", ");
        
        System.out.println();
        
        for (Integer item : dm.minPQ)
            System.out.print(item + ", ");
        
        System.out.println();
        
        for (Integer item : dm)
            System.out.print(item + ", ");
        
        System.out.println();
        
        while (dm.size() > 0)
            System.out.print(dm.remove() + ", ");
        
        System.out.println();
    }
}
