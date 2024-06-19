/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package test.interfaces;

/**
 *
 * @author abhishekchopra
 */
public interface Drawable {
    void draw();
    static int cube(int x) {
        return x * x * x;
    }
    default void dream() {
        System.out.println("Dreaming");
    }
}
