/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BSTProblems;
import symbolTables.BST;

// for revision
// https://www.interviewbit.com/problems/2sum-binary-tree/

/*
    2-Sum Binary Tree
    
    
    Problem Description

    Given a binary search tree A, where each node contains a positive integer, and an integer B, you have to find whether or not there exist two different nodes X and Y such that X.value + Y.value = B.

    Return 1 to denote that two such nodes exist. Return 0, otherwise.



    Problem Constraints
    1 <= size of tree <= 100000

    1 <= B <= 109



    Input Format
    First argument is the head of the tree A.

    Second argument is the integer B.



    Output Format
    Return 1 if such a pair can be found, 0 otherwise.
*/

/**
 *
 * @author abhishekchopra
 */
public class TwoSumBST {
    public int t2Sum(BST.Node<Integer, Integer> A, int B) {
        BST.BSTIterator<Integer, Integer> a = new BST.BSTIterator<>(A);
        BST.BSTIterator<Integer, Integer> b = new BST.BSTIterator<>(A, true);
        
        if (!a.hasNext() || !b.hasNext()) {
            return 0;
        }
        
        int x = a.next().first, y = b.next().first;
        
        while (x < y) {
            
            if (x + y < B) {
                x = a.next().first;
            } else if (x + y > B) {
                y = b.next().first;
            } else {
                return 1;
            }
        }
        return 0;
    }
}
