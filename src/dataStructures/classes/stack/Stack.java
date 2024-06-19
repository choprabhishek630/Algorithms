/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stack;

/**
 *
 * @author abhishekchopra
 */
public abstract class Stack<T> {
    
    // pushes an item of type t
    public abstract void push(T item);
    
    /*
        Removes the item from top of the stack and returns the same item.
        If the stack is empty, it return null
    */
    public abstract T pop();
    
    /*
        It returns the item from top of the stack
        If the stack is empty, it return null
    */
    public abstract T top();
    
    // returns the size of the stack
    public abstract int size();
    
    // return if stack is empty or not
    public abstract boolean isEmpty();
}
