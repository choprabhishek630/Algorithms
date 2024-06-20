/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.vector;
import java.util.Objects;
import vector.Vector;

/**
 *
 * @author abhishekchopra
 */
public class TestVector {
    public static void main(String[] args) {
        Vector<Integer> v = new Vector<>();
        v.add(1);
        v.add(2);
        v.add(3);
        v.add(4);
        v.add(5);
        for (int item : v) {
            System.out.print(item + ", ");
        }
        System.out.println("");
        System.out.println(v.contains(3, (Integer i, Integer j) -> Objects.equals(i, j) ));
        v.forEach(item -> System.out.print(item + ", "));
        System.out.println("");
        System.out.println(v.remove());
        System.out.println(v.remove());
        System.out.println(v.remove());
        System.out.println(v.remove());
        for (int item : v) {
            System.out.print(item + ", ");
        }
        System.out.println("");
        System.out.println(v.contains(3, (Integer i, Integer j) -> Objects.equals(i, j) ));
        System.out.println(v.remove());
    }
}
