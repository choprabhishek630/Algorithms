/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BSTProblems;

// for reision
// https://www.interviewbit.com/problems/valid-bst-from-preorder/

/*
    Valid BST from Preorder

    Problem Description
    You are given a preorder traversal A, of a Binary Search Tree.

    Find if it is a valid preorder traversal of a BST.

    Note: Binary Search Tree by definition has distinct keys and duplicates in binary search tree are not allowed.

    Input Format
    First and only argument is an integer array A denoting the pre-order traversal.

    Output Format
    Return an integer:
    0 : Impossible preorder traversal of a BST
    1 : Possible preorder traversal of a BST
*/

/**
 *
 * @author abhishekchopra
 */
public class ValidBSTFromPreOrder {
    private int solve(int[] A, int lo, int hi) {
        if (lo >= hi) {
            return 1;
        }
        
        int leftTreeEnd = lo;
        while (leftTreeEnd + 1 <= hi && A[leftTreeEnd + 1] < A[lo])
            leftTreeEnd++;
        
        int rightTreeEnd = leftTreeEnd;
        
        while (rightTreeEnd + 1 <= hi && A[rightTreeEnd + 1] > A[lo])
            rightTreeEnd++;
            
        if (rightTreeEnd < hi)
            return 0;
        
        int left = 1, right = 1;
        if (leftTreeEnd > lo) {
            left = solve(A, lo + 1, leftTreeEnd);
        }
        
        if (rightTreeEnd > leftTreeEnd) {
            right = solve(A, leftTreeEnd + 1, rightTreeEnd);
        }
        return left * right;
    }
    
    public int solve(int[] A) {
        return solve(A, 0, A.length - 1);
    }
}
