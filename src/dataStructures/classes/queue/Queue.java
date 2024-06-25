/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package queue;
import collection.MyCollection;

/**
 *
 * @author abhishekchopra
 */
public interface Queue<T> extends MyCollection<T> {
    // queue an item of type t
    void enqueue(T item);
    
    /*
        Removes the item from top of the stack and returns the same item.
        If the stack is empty, it return null
    */
    T dequeue();
    
    /*
        It returns the item from top of the stack
        If the stack is empty, it return null
    */
    T peek();
}
