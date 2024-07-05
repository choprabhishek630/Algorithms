/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinaryTreeProblems;

import java.util.ArrayList;
import java.util.Stack;
import symbolTables.BST;

/**
 *
 * @author abhishekchopra
 */
public class PostOrderTraversalUsingStack {
    private void pushItems(Stack<BST.Node<Integer, Integer>> nodes, BST.Node<Integer, Integer> curr) {
        while (curr != null) {
            nodes.push(curr);
            if (curr.left != null)
                curr = curr.left;
            else
                curr = curr.right;
        }
    }
    
    private BST.Node<Integer, Integer> popItems(Stack<BST.Node<Integer, Integer>> nodes) {
        if (nodes.isEmpty())   return null;
        BST.Node<Integer, Integer> child = nodes.pop();
        if (nodes.isEmpty())   return null;
        BST.Node<Integer, Integer> parent = nodes.peek();
        
        if (parent.right == child || parent.right == null)
            return null;
            
        return parent.right;
    }
    
    
    public ArrayList<Integer> postorderTraversal(BST.Node<Integer, Integer> A) {
        ArrayList<Integer> res = new ArrayList<>();
        
        Stack<BST.Node<Integer, Integer>> nodes = new Stack<>();
        
        this.pushItems(nodes, A);
        
        while (!nodes.isEmpty()) {
            res.add(nodes.peek().key);
            this.pushItems(nodes, this.popItems(nodes));
        }
        
        return res;
    }
}
