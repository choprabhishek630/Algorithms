/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queue;
import java.util.EmptyStackException;
import stack.LLStack;
import stack.Stack;
import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author abhishekchopra
 */
public class QueueWithTwoStacks<T> implements Queue<T> {
    private final Stack<T> eStack; // enqueue stack
    private final Stack<T> dStack; // dequeue stack
    
    public QueueWithTwoStacks() {
        this.eStack = new LLStack<>();
        this.dStack = new LLStack<>();
    }
    
    private class MyIterator<T> implements Iterator<T> {
        private final Iterator<T> dStackI;
        private final T[] eArr;
        private int curr;
        
        public MyIterator(Stack<T> eStack, Stack<T> dStack) {
            this.dStackI = dStack.iterator();
            this.eArr = (T[]) new Object[eStack.size()];
            this.curr = eStack.size() - 1;
            int idx = 0;
            for (T item : eStack) {
                this.eArr[idx++] = item;
            }
        }
        
        @Override
        public boolean hasNext() {
            return this.dStackI.hasNext() || this.curr >= 0;
        }
        
        @Override
        public T next() {
            if (this.dStackI.hasNext()) return this.dStackI.next();
            return this.eArr[this.curr--];
        }
    }
    
    @Override
    public int size() {
        return this.eStack.size() + this.dStack.size();
    }
    
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
    @Override
    public void enqueue(T item) {
        this.eStack.push(item);
    }
    
    private void transfer() {
        if (!this.dStack.isEmpty()) {
            return;
        }
        while (!this.eStack.isEmpty()) {
            this.dStack.push(this.eStack.pop());
        }
    }
    
    @Override
    public T peek() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        this.transfer();
        return this.dStack.top();
    }
    
    @Override
    public T dequeue() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        this.transfer();
        return this.dStack.pop();
    }
    
    @Override
    public T remove() {
        return this.dequeue();
    }
    
    @Override
    public void add(T item) {
        this.enqueue(item);
    }
    
    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>(this.eStack, this.dStack);
    }
    
    public static void main(String[] args) {
        Queue<Integer> q = new QueueWithTwoStacks<>();
        System.out.println(q.size());
        System.out.println(q.isEmpty());
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        System.out.println(q.size());
        System.out.println(q.isEmpty());
        for (int a : q) {
            System.out.print(a + ", ");
        }
        System.out.println();
        while (!q.isEmpty()) {
            System.out.print(q.peek() + " " + q.dequeue() + ", ");
        }
        System.out.println();
        System.out.println(q.size());
    }
}
