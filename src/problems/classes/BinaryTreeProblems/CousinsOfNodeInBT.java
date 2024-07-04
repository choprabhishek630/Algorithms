/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinaryTreeProblems;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import symbolTables.BST;


// for revision
// https://www.interviewbit.com/problems/cousins-in-binary-tree/

/*
    Cousins in Binary Tree


    Problem Description

    Given a Binary Tree A consisting of N nodes.

    You need to find all the cousins of node B.

    NOTE:

    Siblings should not be considered as cousins.
    Try to do it in single traversal.
    You can assume that Node B is there in the tree A.
    Order doesn't matter in the output.


    Problem Constraints
     1 <= N <= 105 

     1 <= B <= N



    Input Format
    First Argument represents the root of binary tree A.

    Second Argument is an integer B denoting the node number.



    Output Format
    Return an integer array denoting the cousins of node B.

    NOTE: Order doesn't matter.



    Example Input
    Input 1:

     A =

               1
             /   \
            2     3
           / \   / \
          4   5 6   7 


    B = 5

    Input 2:

     A = 
                1
              /   \
             2     3
            / \ .   \
           4   5 .   6


    B = 1




    Example Output
    Output 1:

     [6, 7]
    Output 2:

     []


    Example Explanation
    Explanation 1:

     Cousins of Node 5 are Node 6 and 7 so we will return [6, 7]
     Remember Node 4 is sibling of Node 5 and do not need to return this.
    Explanation 2:

     Since Node 1 is the root so it doesn't have any cousin so we will return an empty array.
*/

/**
 *
 * @author abhishekchopra
 */
public class CousinsOfNodeInBT {
    public ArrayList<Integer> solve(BST.Node<Integer, Integer> A, int B) {
        ArrayList<Integer> res = new ArrayList<>();
        
        if (A == null) return res;
        
        Queue<BST.Node<Integer, Integer>> nodes = new LinkedList<>();
        
        nodes.add(A);
        
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            boolean isFound = false;
            while (--size >= 0) {
                BST.Node<Integer, Integer> curr = nodes.remove();
                if ((curr.left != null && curr.left.key == B) || (curr.right != null && curr.right.key == B)) {
                    isFound = true;
                    continue;
                }
                
                if (curr.left != null) {
                    nodes.add(curr.left);
                }
                
                if (curr.right != null) {
                    nodes.add(curr.right);
                }
            }
            
            if (isFound) {
                while (!nodes.isEmpty()) {
                    res.add(nodes.remove().key);
                }
            }
        }
        
        return res;
    }
}
