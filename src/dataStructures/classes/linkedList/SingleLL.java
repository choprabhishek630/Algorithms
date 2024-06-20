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
    protected int N;
    
    public SingleLL() {
        this.head = null;
        this.N = 0;
    }
    
    @Override
    public void add(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.next = this.head;
        this.head = newNode;
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
    public Node<T> getHead() {
        return this.head;
    }
    
    @Override
    public T remove() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        T item = this.head.item;
        this.head = this.head.next;
        this.N--;
        return item;
    }
    
    @Override
    public T removeFromFront() {
        return this.remove();
    }
    
    @Override
    public void addInFront(T item) {
        this.add(item);
    }
    
    @Override
    public void swap(Node<T> n1, Node<T> n2) {
        // TODO
    }
    
    @Override
    public Iterator<T> iterator() {
        return new SingleLLIterator(this.head);
    }
}
