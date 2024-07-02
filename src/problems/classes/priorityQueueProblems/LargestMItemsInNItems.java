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

// for revision
// https://www.coursera.org/learn/algorithms-part1/lecture/A3kA3/apis-and-elementary-implementations at 12:52

/*
    Problem statement
    You will receive a stream of N elements and at any point of time you need to provide a list of M largest items.
    Challenge => N is a huge number and N items cannot be stored in this program.
*/

/**
 *
 * @author abhishekchopra
 */
public class LargestMItemsInNItems<T> implements MyCollection<T> {
    private final int M;
    private final PriorityQueue<T> minPQ;
    
    public LargestMItemsInNItems(int m, Comparator<T> cmp) {
        this.M = Math.max(1, m);
        this.minPQ = new BinaryHeap<>(cmp.reversed());
    }
    
    @Override
    public int size() {
        return this.minPQ.size();
    }
    
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
    @Override
    public void add(T item) {
        this.minPQ.add(item);
        if (this.size() > this.M)
            this.minPQ.remove();
    }
    
    
    @Override
    public T remove() {
        return this.minPQ.remove();
    }
    
    @Override
    public Iterator<T> iterator() {
        return this.minPQ.iterator();
    }
}
