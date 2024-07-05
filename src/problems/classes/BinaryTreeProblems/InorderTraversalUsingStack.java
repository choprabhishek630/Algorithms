/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinaryTreeProblems;

import java.util.Stack;
import java.util.ArrayList;
import symbolTables.BST;

/**
 *
 * @author abhishekchopra
 */
public class InorderTraversalUsingStack {
    private void pushItems(Stack<BST.Node<Integer, Integer>> nodes, BST.Node<Integer, Integer> curr) {
        while (curr != null) {
            nodes.push(curr);
            curr = curr.left;
        }
    }
    
    private BST.Node<Integer, Integer> popItems(Stack<BST.Node<Integer, Integer>> nodes) {
        return nodes.pop();
    }
    
    public ArrayList<Integer> inorderTraversal(BST.Node<Integer, Integer> A) {
        ArrayList<Integer> res = new ArrayList<>();
        
        Stack<BST.Node<Integer, Integer>> nodes = new Stack<>();
        
        this.pushItems(nodes, A);
        
        while (!nodes.isEmpty()) {
            BST.Node<Integer, Integer> x = this.popItems(nodes);
            res.add(x.key);
            this.pushItems(nodes, x.right);
        }
        
        return res;
    }
    
}
