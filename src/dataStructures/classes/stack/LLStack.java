/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stack;
import linkedList.Node;

/**
 *
 * @author abhishekchopra
 */
public class LLStack<T> extends Stack<T> {
    private Node<T> first;
    int N;
    
    public LLStack() {
        this.first = null;
        this.N = 0;
    }
    
    @Override
    public void push(T item) {
        if (item == null) {
            return;
        }
        Node<T> oldFirst = this.first;
        this.first = new Node<>(item);
        this.first.next = oldFirst;
        N++;
    }
    
    @Override
    public T pop() {
        if (this.isEmpty()) {
            return null;
        }
        T item = this.first.item;
        this.first = this.first.next;
        N--;
        return item;
    }
    
    @Override
    public T top() {
        if (this.isEmpty()) {
            return null;
        }
        return this.first.item;
    }
    
    @Override
    public int size() {
        return this.N;
    }
    
    @Override
    public boolean isEmpty() {
        return this.N == 0;
    }
}
