/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinaryTreeProblems;
import symbolTables.BST;
import java.util.ArrayList;


// For revision
// https://www.interviewbit.com/problems/burn-a-tree/


/*
    Burn a Tree



    Problem Description

    Given a binary tree denoted by root node A and a leaf node B from this tree.

     It is known that all nodes connected to a given node (left child, right child and parent) get burned in 1 second. Then all the nodes which are connected through one intermediate get burned in 2 seconds, and so on.

    You need to find the minimum time required to burn the complete binary tree.



    Problem Constraints
    2 <= number of nodes <= 105

    1 <= node value, B <= 105

    node value will be distinct



    Input Format
    First argument is a root node of the binary tree, A.

    Second argument is an integer B denoting the node value of leaf node.



    Output Format
    Return an integer denoting the minimum time required to burn the complete binary tree.



    Example Input
    Input 1:

     Tree :      1 
                / \ 
               2   3 
              /   / \
             4   5   6
     B = 4
    Input 2:

     Tree :      1
                / \
               2   3
              /     \
             4       5 
     B = 5 


    Example Output
    Output 1:

     4
    Output 2:

     4


    Example Explanation
    Explanation 1:

     After 1 sec: Node 4 and 2 will be burnt. 
     After 2 sec: Node 4, 2, 1 will be burnt.
     After 3 sec: Node 4, 2, 1, 3 will be burnt.
     After 4 sec: Node 4, 2, 1, 3, 5, 6(whole tree) will be burnt.

    Explanation 2:

     After 1 sec: Node 5 and 3 will be burnt. 
     After 2 sec: Node 5, 3, 1 will be burnt.
     After 3 sec: Node 5, 3, 1, 2 will be burnt.
     After 4 sec: Node 5, 3, 1, 2, 4(whole tree) will be burnt.

*/

/**
 *
 * @author abhishekchopra
 */
public class BurnATree {
    private int getHeight(BST.Node<Integer, Integer> A) {
        if (A == null) return 0;
        return 1 + Math.max(getHeight(A.left), getHeight(A.right));
    }
    
    private boolean getPath(BST.Node<Integer, Integer> A, int B, ArrayList<BST.Node<Integer, Integer>> st) {
        if (A == null) return false;
        if (A.key == B) {
            st.add(A);
            return true;
        }
        boolean left = getPath(A.left, B, st);
        if (left) {
            st.add(A);
            return true;
        }
        boolean right = getPath(A.right, B, st);
        if (right) {
            st.add(A);
            return true;
        }
        return left || right;
    }
    
    public int solve(BST.Node<Integer, Integer> A, int B) {
        ArrayList<BST.Node<Integer, Integer>> st = new ArrayList<>();
        getPath(A, B, st);
        
        int timeToBurn = 0;
        while (st.size() > 1) {
            BST.Node<Integer, Integer> curr = st.remove(st.size() - 1);
            int currTimeToBurn = st.size();
            BST.Node<Integer, Integer> peek = st.get(st.size() - 1);
            if (curr.left == peek) {
                currTimeToBurn += getHeight(curr.right);
            } else {
                currTimeToBurn += getHeight(curr.left);
            }
            timeToBurn = Math.max(currTimeToBurn, timeToBurn);
        }
        BST.Node<Integer, Integer> targetNode = st.remove(st.size() - 1);
        timeToBurn = Math.max(getHeight(targetNode) - 1, timeToBurn);
        return timeToBurn;
    }
}
