/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;
import stack.LLStack;

/**
 *
 * @author abhishekchopra
 */
public class TestLLStack {
    public static void main(String[] args) {
        LLStack<Integer> stack = new LLStack<>();
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        stack.forEach(item -> System.out.print(item));
        System.out.println("");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.top());
        stack.forEach(item -> System.out.print(item));
        System.out.println("");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.forEach(item -> System.out.print(item));
        System.out.println("");
        System.out.println(stack.isEmpty());
        
        LLStack<Integer> stack1 = new LLStack<>();
        stack1.push(5);
        stack1.push(4);
        stack1.push(3);
        stack1.push(2);
        stack1.push(1);
        stack1.forEach(item -> System.out.print(item));
        System.out.println("");
        System.out.println(stack1.pop());
        System.out.println(stack1.pop());
        System.out.println(stack1.top());
        stack1.forEach(item -> System.out.print(item));
        System.out.println("");
        System.out.println(stack1.pop());
        System.out.println(stack1.pop());
        System.out.println(stack1.pop());
        stack1.forEach(item -> System.out.print(item));
        System.out.println("");
        System.out.println(stack1.isEmpty());
    }
}
