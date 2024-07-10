/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinaryTreeProblems;
import symbolTables.BST;

// For revision
// https://www.interviewbit.com/problems/last-node-in-a-complete-binary-tree/hints/

/*
    Last Node in a Complete Binary Tree
    



    Problem Description



    You are given the root of a complete binary tree A.

    You have to return the value of the rightmost node in the last level of the binary tree.

    Try to find a solution with a better time complexity than O(N).



    Problem Constraints
    1 <= Number of nodes in the binary tree <= 105


    Input Format
    The first argument is the root of a binary tree A.


    Output Format
    Return a single integer denoting the value of the rightmost node in the last level of the binary tree.


    Example Input
    Input 1:
    A = 
        1
       /
      2
    Input 2:

    A = 
        1
       / \
      2   3


    Example Output
    Output 1:
    2
    Output 2:

    3


    Example Explanation
    Explanation 1:
    There is only a single node in the last level of the binary tree.
    Therefore, the answer is 2.
    Explanation 2:

    There a two nodes in the last level of the tree.
    The rightmost nodes is 3.
*/

/*
    Solution Approach
    
    So, the idea here is to compare the height of the two subtrees.
    If the height of the left subtree is higher, we go left, else we go right.
    We can find the height of a subtree in O(log N), and this will be repeated for O(log N) steps.

    Therefore, we can find the answer in O(log N * log N).
*/

/**
 *
 * @author abhishekchopra
 */
public class LastNodeInCompleteBinaryTree {
    public int height(BST.Node<Integer, Integer> A) {
        if (A == null) return 0;
        return height(A.left) + 1;
    }
    public int lastNode(BST.Node<Integer, Integer> A) {
        if (A.left == null) return A.key;
        int hL = height(A.left);
        int hR = height(A.right);
        if (hL > hR) return lastNode(A.left);
        return lastNode(A.right);
    }
}
