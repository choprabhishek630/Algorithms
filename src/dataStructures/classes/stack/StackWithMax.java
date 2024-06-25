/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stack;

/**
 *
 * @author abhishekchopra
 */
public class StackWithMax<T extends Comparable<T>> extends LLStack<T> {
    private final Stack<T> maxStack;
    public StackWithMax() {
        super();
        this.maxStack = new LLStack<>();
    }
    @Override
    public void push(T item) {
        super.push(item);
        if (item.compareTo(this.max()) >= 0) this.maxStack.push(item);
    }
    
    public T max() {
        return this.maxStack.top();
    }
    
    @Override
    public T pop() {
        T item = super.pop();
        if (item.compareTo(this.max()) == 0) this.maxStack.pop();
        return item;
    }
}
