/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linkedList;

import java.util.EmptyStackException;
import java.util.function.Consumer;

/**
 *
 * @author abhishekchopra
 */
public class DoublyLL<T> extends SingleLL<T> {
    private DoubleNode<T> tail;
    public DoublyLL() {
        super();
        this.tail = null;
    }
    
    @Override
    public DoubleNode<T> getTail() {
        return this.tail;
    }
    
    @Override
    public void add(T item) {
        DoubleNode<T> newNode = new DoubleNode<>(item);
        newNode.next = this.head;
        if (this.head != null) {
            ((DoubleNode<T>) this.head).prev = newNode;
        } else {
            this.tail = newNode;
        }
        this.head = newNode;
        this.N++;
    }
    
    @Override
    public T remove() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        T item = this.head.item;
        this.head = this.head.next;
        if (this.head != null) {
            ((DoubleNode<T>) this.head).prev = null;
        } else {
            this.tail = null;
        }
        this.N--;
        return item;
    }
    
    @Override
    public void swap(Node<T> d1, Node<T> d2) {
        // TODO
    }
    
    @Override
    public void addInBack(T item) {
        DoubleNode<T> newNode = new DoubleNode<>(item);
        newNode.prev = this.tail;
        if (this.tail != null) {
            ((DoubleNode<T>) this.tail).next = newNode;
        } else {
            this.head = newNode;
        }
        this.tail = newNode;
        this.N++;
    }
    
    @Override
    public T removeFromBack() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        T item = this.tail.item;
        this.tail = this.tail.prev;
        if (this.tail != null) {
            ((DoubleNode<T>) this.tail).next = null;
        } else {
            this.head = null;
        }
        this.N--;
        return item;
    }
    
    @Override
    public void forEachReverse(Consumer<? super T> c) {
        DoubleNode<T> current = this.tail;
        while (current != null) {
            c.accept(current.item);
            current = current.prev;
        }
    }
    
}
