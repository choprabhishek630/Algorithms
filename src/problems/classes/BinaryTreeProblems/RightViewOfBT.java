/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinaryTreeProblems;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import symbolTables.BST;

/**
 *
 * @author abhishekchopra
 */
public class RightViewOfBT {
    public ArrayList<Integer> solve(BST.Node<Integer, Integer> A) {
        ArrayList<Integer> res = new ArrayList<>();
        
        if (A == null) return res;
        
        Queue<BST.Node<Integer, Integer>> nodes = new LinkedList<>();
        
        nodes.add(A);
        
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            
            while (--size >= 0) {
                BST.Node<Integer, Integer> curr = nodes.remove();
                
                if (size == 0) {
                    res.add(curr.key);
                }
                
                if (curr.left != null) {
                    nodes.add(curr.left);
                }
                
                if (curr.right != null) {
                    nodes.add(curr.right);
                }
            }
        }
        
        return res;
    }
}
