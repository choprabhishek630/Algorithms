/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinaryTreeProblems;
import util.Pair;
import symbolTables.BST;

// for revision
// https://www.interviewbit.com/problems/flatten-binary-tree-to-linked-list/


/*
    Flatten Binary Tree to Linked List


    
    Problem Description



    Given a binary tree A, flatten it to a linked list in-place.

    The left child of all nodes should be NULL.



    Problem Constraints
    1 <= size of tree <= 100000



    Input Format
    First and only argument is the head of tree A.



    Output Format
    Return the linked-list after flattening.



    Example Input
    Input 1:

         1
        / \
       2   3
    Input 2:

             1
            / \
           2   5
          / \   \
         3   4   6


    Example Output
    Output 1:

    1
     \
      2
       \
        3
    Output 2:

    1
     \
      2
       \
        3
         \
          4
           \
            5
             \
              6


    Example Explanation
    Explanation 1:

     Tree flattening looks like this.
    Explanation 2:

     Tree flattening looks like this.

*/

/**
 *
 * @author abhishekchopra
 */
public class FlattenTree {
    private Pair<BST.Node<Integer, Integer>, BST.Node<Integer, Integer>> flattenHelper(BST.Node<Integer, Integer> A) {
        if (A == null) return new Pair<>(null, null);
        Pair<BST.Node<Integer, Integer>, BST.Node<Integer, Integer>> left = flattenHelper(A.left);
        Pair<BST.Node<Integer, Integer>, BST.Node<Integer, Integer>> right = flattenHelper(A.right);
        BST.Node<Integer, Integer> end = A;
        if (left.first == null) {
            A.right = right.first;
        } else {
            A.right = left.first;
            left.second.right = right.first;
        }
        if (right.first != null) {
            end = right.second;
        } else if (left.first != null) {
            end = left.second;
        }
        
        A.left = null;
        return new Pair<>(A, end);
    }
    public BST.Node<Integer, Integer> flatten(BST.Node<Integer, Integer> a) {
        return flattenHelper(a).first;
    }
}
