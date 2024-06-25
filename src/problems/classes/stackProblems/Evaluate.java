/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stackProblems;

/**
 *
 * @author abhishekchopra
 */
public class Evaluate {
    public static double evaluateExpression(String expr) {
        int n = expr.length(), current = 0;
        while (current < n) {
            System.out.print(expr.charAt(current));
            current++;
        }
        return 1.0;
    }
    
    public static void main(String[] args) {
        evaluateExpression("asas");
    }
}
