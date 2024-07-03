/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queue;

import linkedList.Node;
import linkedList.SingleLL;
import java.util.EmptyStackException;


/**
 *
 * @author abhishekchopra
 */
public class LLQueue<T> extends SingleLL<T> implements Queue<T> {
    @Override
    public boolean swap(Node<T> n1, Node<T> n2) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void enqueue(T item) {
        super.addInBack(item);
    }
    
    @Override
    public T dequeue() {
        return super.removeFromFront();
    }
    
    @Override
    public T peek() {
        if (super.isEmpty()) {
            throw new EmptyStackException();
        }
        return super.getHead();
    }
}
