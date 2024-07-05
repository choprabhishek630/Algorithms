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
public class PreOrderTraversalUsingStack {
    private void pushItems(Stack<BST.Node<Integer, Integer>> nodes, BST.Node<Integer, Integer> root) {
        if (root != null)
            nodes.push(root);
    }
    
    private BST.Node<Integer, Integer> popItems(Stack<BST.Node<Integer, Integer>> nodes) {
        BST.Node<Integer, Integer> curr = null;
        while (!nodes.isEmpty() && curr == null)
            curr = nodes.pop().right;
        return curr;
    }
    
    public ArrayList<Integer> preorderTraversal(BST.Node<Integer, Integer> A) {
        ArrayList<Integer> res = new ArrayList<>();
        Stack<BST.Node<Integer, Integer>> nodes = new Stack<>();
        
        this.pushItems(nodes, A);
        
        while (!nodes.isEmpty()) {
            BST.Node<Integer, Integer> curr = nodes.peek();
            res.add(curr.key);
            curr = curr.left;
            if (curr == null) {
                curr = this.popItems(nodes);
            }
            this.pushItems(nodes, curr);
        }
        
        return res;
    }
}
