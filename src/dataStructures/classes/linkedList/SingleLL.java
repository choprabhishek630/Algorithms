/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linkedList;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 *
 * @author abhishekchopra
 */
public class SingleLL<T> implements LinkedList<T> {
    protected Node<T> head;
    protected Node<T> tail;
    protected int N;
    
    public SingleLL() {
        this.head = null;
        this.tail = null;
        this.N = 0;
    }
    
    @Override
    public void addInBack(T item) {
        Node<T> oldTail = this.tail;
        this.tail = new Node<>(item);
        if (this.isEmpty()) this.head = this.tail;
        else                oldTail.next = this.tail;
        this.N++;
    }
    
    @Override
    public int size() {
        return this.N;
    }
    
    @Override
    public boolean isEmpty() {
        return this.N == 0;
    }
    
    @Override
    public T getHead() {
        if (this.head == null) return null;
        return this.head.item;
    }
    
    @Override
    public T getTail() {
        if (this.tail == null) return null;
        return this.tail.item;
    }
    
    @Override
    public T removeFromFront() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        T item = this.head.item;
        this.head = this.head.next;
        this.N--;
        if (this.isEmpty()) this.tail = null;
        return item;
    }
    
    @Override
    public T remove() {
        return this.removeFromFront();
    }
    
    @Override
    public void add(T item) {
        this.addInBack(item);
    }
    
    @Override
    public void addInFront(T item) {
        Node<T> oldHead = this.head;
        this.head = new Node<>(item);
        if (this.isEmpty()) this.tail = this.head;
        else                            this.head.next = oldHead;
        this.N++;
    }
    
    @Override
    public boolean swap(Node<T> n1, Node<T> n2) {
        // TODO
        return false;
    }
    
    @Override
    public Iterator<T> iterator() {
        return new SingleLLIterator(this.head);
    }
    
    protected boolean isValidIndex(int idx) {
        return idx >= 0 && idx < this.size();
    }
    
    @Override
    public T remove(int idx) {
        if (this.isEmpty())
            throw new EmptyStackException();
        
        if (!this.isValidIndex(idx))
            throw new IndexOutOfBoundsException("Index " + idx + " is out of bounds!");
        
        if (idx == 0)
            return this.removeFromFront();
        
        int i = 1;
        Node<T> curr = head;
        while (curr.next != null) {
            if (i == idx) {
                Node<T> next = curr.next;
                curr.next = next.next;
                next.next = null;
                this.N--;
                if (curr.next == null) this.tail = curr;
                return next.item;
            }
            curr = curr.next;
            i++;
        }
        
        return null;
    }
    
    @Override
    public T removeFromBack() {
        return this.remove(this.size() - 1);
    }
}
