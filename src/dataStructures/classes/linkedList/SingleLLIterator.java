/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linkedList;

import java.util.Iterator;

/**
 *
 * @author abhishekchopra
 */
public class SingleLLIterator<T> implements Iterator<T> {
    private Node<T> head;
    
    public SingleLLIterator(Node<T> head) {
        this.head = head;
    }
    
    @Override
    public boolean hasNext() {
        return this.head != null;
    }
    
    @Override
    public T next() {
        T item = this.head.item;
        this.head = this.head.next;
        return item;
    }
}
