/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.interfaces;

/**
 *
 * @author abhishekchopra
 */
public class TestInterface1 {
    public static void main(String[] args) {
        Drawable d = new Circle();
        Circle c = new Circle();
        d.draw();
        Drawable.cube(3);
        d.dream();
        c.draw();
        c.dream();
        c.msg();
        Circle.show();
    }
}

