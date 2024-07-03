/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stack;
import linkedList.Node;
import linkedList.SingleLL;
import java.util.EmptyStackException;

/**
 *
 * @author abhishekchopra
 */
public class LLStack<T> extends SingleLL<T> implements Stack<T>  {
    @Override
    public boolean swap(Node<T> n1, Node<T> n2) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void push(T item) {
        super.addInFront(item);
    }
    
    @Override
    public T pop() {
        return super.removeFromFront();
    }
    
    @Override
    public T top() {
        if (super.isEmpty()) {
            throw new EmptyStackException();
        }
        return super.getHead();
    }
}
