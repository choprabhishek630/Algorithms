/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package linkedList;
import collection.MyCollection;
import java.util.function.Consumer;

/**
 *
 * @author abhishekchopra
 */
public interface LinkedList<T> extends MyCollection<T> {
    T getHead();
    
    boolean swap(Node<T> n1, Node<T> n2);
    
    T removeFromFront();
    
    void addInFront(T item);
    
    T getTail();
    
    T removeFromBack();
    
    void addInBack(T item);
    
    default void forEachReverse(Consumer<? super T> c) {
        throw new UnsupportedOperationException();
    }
    
    T remove(int idx);
}
