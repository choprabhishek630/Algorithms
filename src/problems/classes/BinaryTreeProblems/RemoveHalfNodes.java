/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinaryTreeProblems;
import symbolTables.BST;

// For revision
// https://www.interviewbit.com/problems/remove-half-nodes/

/*
    Remove Half Nodes



    Problem Description

    Given a binary tree A with N nodes.

    You have to remove all the half nodes and return the final binary tree.

    NOTE:

    Half nodes are nodes which have only one child.
    Leaves should not be touched as they have both children as NULL.


    Problem Constraints
     1 <= N <= 105



    Input Format
    First and only argument is an pointer to the root of binary tree A.



    Output Format
    Return a pointer to the root of the new binary tree.



    Example Input
    Input 1:

               1
             /   \
            2     3
           / 
          4

    Input 2:

                1
              /   \
             2     3
            / \     \
           4   5     6


    Example Output
    Output 1:

               1
             /   \
            4     3
    Output 2:

                1
              /   \
             2     6
            / \

           4   5



    Example Explanation
    Explanation 1:

     The only half node present in the tree is 2 so we will remove this node.
    Explanation 2:

     The only half node present in the tree is 3 so we will remove this node.
*/

/**
 *
 * @author abhishekchopra
 */
public class RemoveHalfNodes {
    private BST.Node findSuccessor(BST.Node A) {
        while (A != null) {
            if (A.left == null && A.right != null) {
                A = A.right;
            } else if (A.left != null && A.right == null) {
                A = A.left;
            } else {
                break;
            }
        }
        return A;
    }
    public BST.Node solve(BST.Node A) {
        BST.Node root = null;
        if (A == null) return root;
        if ((A.left == null && A.right == null) || (A.left != null && A.right != null)) {
            root = A;
        } else {
            root = findSuccessor(A);
        }
        if (root != null) {
            root.left = solve(root.left);
            root.right = solve(root.right);
        }
        return root;
    }
}
