/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stack;

import collection.MyCollection;

/**
 *
 * @author abhishekchopra
 */
public interface Stack<T> extends MyCollection<T> {
    
    // pushes an item of type t
    void push(T item);
    
    /*
        Removes the item from top of the stack and returns the same item.
        If the stack is empty, it return null
    */
    T pop();
    
    /*
        It returns the item from top of the stack
        If the stack is empty, it return null
    */
    T top();
}
