/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.LinkedList;
import linkedList.DoublyLL;
import java.util.Objects;
import linkedList.LinkedList;

/**
 *
 * @author abhishekchopra
 */
public class TestDoubleLL {
    public static void main(String[] args) {
        LinkedList<Integer> ll = new DoublyLL<>();
        ll.addInBack(1);
        ll.add(2);
        ll.add(3);
        ll.addInBack(4);
        ll.add(5);
        for (int item : ll) {
            System.out.print(item + ", ");
        }
        System.out.println("");
        System.out.println(ll.contains(3, (Integer i, Integer j) -> Objects.equals(i, j) ));
        ll.forEach(item -> System.out.print(item + ", "));
        System.out.println("");
        ll.forEachReverse(item -> System.out.print(item + ", "));
        System.out.println("");
        System.out.println(ll.remove());
        System.out.println(ll.removeFromBack());
        ll.forEachReverse(item -> System.out.print(item + ", "));
        System.out.println("");
        System.out.println(ll.remove());
        System.out.println(ll.remove());
        for (int item : ll) {
            System.out.print(item + ", ");
        }
        System.out.println("");
        ll.forEachReverse(item -> System.out.print(item + ", "));
        System.out.println("");
        System.out.println(ll.contains(3, (Integer i, Integer j) -> Objects.equals(i, j) ));
        System.out.println(ll.removeFromBack());
        System.out.println(ll.getHead());
        System.out.println(ll.size());
        
        
    }
}
