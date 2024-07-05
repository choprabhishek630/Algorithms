/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinaryTreeProblems;
import java.util.ArrayList;
import symbolTables.BST;

/**
 *
 * @author abhishekchopra
 */
public class MorrisTrversal {
    private BST.Node<Integer, Integer> updatePredecessor(BST.Node<Integer, Integer> A) {
        if (A == null || A.left == null) return null;
        
        BST.Node<Integer, Integer> B = A.left;
        while (B.right != null && B.right != A) {
            B = B.right;
        }
        
        if (B.right == null) {
            B.right = A;
            return B;
        }
        B.right = null;
        return A;
    }
    
    public ArrayList<Integer> inorderTraversal(BST.Node<Integer, Integer> A) {
        ArrayList<Integer> res = new ArrayList<>();
        
        while (A != null) {
            BST.Node<Integer, Integer> curr = updatePredecessor(A);
            if (curr == null || curr == A) {
                res.add(A.key);
                A = A.right;
            } else {
                A = A.left;
            }
        }
        
        return res;
    }
    
    public ArrayList<Integer> preorderTraversal(BST.Node<Integer, Integer> A) {
        ArrayList<Integer> res = new ArrayList<>();
        while (A != null) {
            BST.Node<Integer, Integer> curr = updatePredecessor(A);
            
            if (curr == null) {
                res.add(A.key);
                A = A.right;
            } else if (curr == A) {
                A = A.right;
            } else {
                res.add(A.key);
                A = A.left;
            }
        }
        
        return res;
    }
}
