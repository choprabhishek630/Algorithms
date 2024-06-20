/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linkedList;

/**
 *
 * @author abhishekchopra
 */
public class DoubleNode<T> extends Node<T> {
    public DoubleNode<T> prev;
    public DoubleNode(T item) {
        super(item);
        this.prev = null;
    }
}
