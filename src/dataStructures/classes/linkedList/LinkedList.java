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
    Node<T> getHead();
    
    boolean swap(Node<T> n1, Node<T> n2);
    
    T removeFromFront();
    
    void addInFront(T item);
    
    default void addInBack(T item) {
        throw new UnsupportedOperationException();
    }
    
    default Node<T> getTail() {
        throw new UnsupportedOperationException();
    }
    
    default T removeFromBack() {
        throw new UnsupportedOperationException();
    }
    
    default void forEachReverse(Consumer<? super T> c) {
        throw new UnsupportedOperationException();
    }
}
