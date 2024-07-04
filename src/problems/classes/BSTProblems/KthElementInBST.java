/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BSTProblems;

import util.Pair;
import symbolTables.BST;


// for revision
// https://www.interviewbit.com/problems/kth-smallest-element-in-tree/


/*
    Kth Smallest Element In Tree
    
    Problem Description
 
 

    Given a binary search tree, write a function to find the kth smallest element in the tree.
    NOTE: You may assume 1 <= k <= Total number of nodes in BST


    Input Format
    The first argument is the root node of the binary tree.
    The second argument B is an integer equal to the value of k.


    Output Format
    Return the value of the kth smallest node.

*/

/**
 *
 * @author abhishekchopra
 */
public class KthElementInBST {
    private Pair<Integer, Integer> solve(BST.Node<Integer, Integer> A, int B) {
        if (A == null) return new Pair<>(0, -1);
        
        Pair<Integer, Integer> left = solve(A.left, B);
        
        if (left.first >= B) {
            left.first = left.first + 1;
            return left;
        }
        
        if (left.first == B - 1) {
            left.first = left.first + 1;
            left.second = A.key;
            return left;
        }
        
        Pair<Integer, Integer> right = solve(A.right, B - left.first - 1);
        right.first += 1 + left.first;
        return right;
    } 
    
    public int kthsmallest(BST.Node<Integer, Integer> A, int B) {
        return solve(A, B).second;
    }
}
