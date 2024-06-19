/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.interfaces;

/**
 *
 * @author abhishekchopra
 */
public class Circle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing circle");
    }
    public static void show() {
        System.out.println("Showing circle");
    }
    public void msg() {
        System.out.println("This is a message from circle");
    }
}
