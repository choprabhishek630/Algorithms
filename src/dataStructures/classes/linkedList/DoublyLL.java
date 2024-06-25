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
    
    @Override
    public void addInFront(T item) {
        DoubleNode<T> oldHead = (DoubleNode<T>) this.head;
        this.head = new DoubleNode<>(item);
        if (this.isEmpty()) this.tail = this.head;
        else {
            this.head.next = oldHead;
            oldHead.prev = (DoubleNode<T>) this.head;
        }
        this.N++;
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
        else ((DoubleNode<T>) this.head).prev = null;
        return item;
    }
    
    @Override
    public boolean swap(Node<T> d1, Node<T> d2) {
        // TODO
        return false;
    }
    
    @Override
    public void addInBack(T item) {
        DoubleNode<T> oldTail = (DoubleNode<T>) this.tail;
        this.tail = new DoubleNode<>(item);
        if (this.isEmpty()) this.head = this.tail;
        else {
            ((DoubleNode<T>) this.tail).prev = oldTail;
            oldTail.next = this.tail;
        }
        this.N++;
    }
    
    @Override
    public T removeFromBack() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        T item = this.tail.item;
        this.tail = ((DoubleNode<T>) this.tail).prev;
        this.N--;
        if (this.isEmpty()) this.head = null;
        else ((DoubleNode<T>) this.tail).next = null;
        return item;
    }
    
    @Override
    public void forEachReverse(Consumer<? super T> c) {
        DoubleNode<T> current = (DoubleNode<T>) this.tail;
        while (current != null) {
            c.accept(current.item);
            current = current.prev;
        }
    }
    
}
