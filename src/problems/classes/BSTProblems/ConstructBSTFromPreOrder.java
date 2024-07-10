/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BSTProblems;
import symbolTables.BST;
import util.Pair;
import java.util.ArrayList;

// For revision
// https://www.interviewbit.com/problems/construct-bst-from-preorder/

/*
    Construct BST from Preorder



    Problem Description



    Given an integer array A with distinct elements, which represents the preorder traversal of a binary search tree,

    construct the tree and return its root.

    A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.

    A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left, then traverses Node.right.



    **Problem Constraints**
    1 <= |A| <= 105
    1 <= A.val <= 109
    The given array is a valid preorder traversal of a BST.


    **Input Format**
    The first argument is an integer array denoting the preorder traversal.


    **Output Format**
    Return the root of the Binary Search Tree.


    **Example Input**
    Input 1:
    A = [2, 1, 4, 3, 5]
    Input 2:

    A = [1, 2, 3]


    **Example Output**
    Output 1:
        2
       / \
      1   4
         / \
        3   5
    Output 2:

          1
         /
        2
       /
      3


    **Example Explanation**
    Explanation 1:
    We can see that is the tree created by the given pre order traversal.
    Explanation 2:

    We can see that is the tree created by the given pre order traversal.
*/

/**
 *
 * @author abhishekchopra
 */
public class ConstructBSTFromPreOrder {
    public Pair<BST.Node<Integer, Integer>, Integer> constructBST(ArrayList<Integer> A, int idx, int min, int max) {
        if (idx >= A.size() || A.get(idx) > max || A.get(idx) < min) return new Pair(null, idx);
        BST.Node<Integer, Integer> root = new BST.Node<>(A.get(idx), A.get(idx));
        idx++;
        if (idx < A.size()) {
            Pair<BST.Node<Integer, Integer>, Integer> left = constructBST(A, idx, min, root.key);
            root.left = left.first;
            idx = left.second;
        }
        if (idx < A.size()) {
            Pair<BST.Node<Integer, Integer>, Integer> right = constructBST(A, idx, root.key, max);
            root.right = right.first;
            idx = right.second;
        }
        return new Pair<>(root, idx);
    }
    
    public BST.Node<Integer, Integer> constructBST(ArrayList<Integer> A) {
        return constructBST(A, 0, Integer.MIN_VALUE, Integer.MAX_VALUE).first;
    }
}
