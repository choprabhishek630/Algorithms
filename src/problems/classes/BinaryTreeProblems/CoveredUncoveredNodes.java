/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinaryTreeProblems;
import java.util.Queue;
import java.util.LinkedList;
import symbolTables.BST;


// for revision
// https://www.interviewbit.com/problems/covered-uncovered-nodes/

/*
    Covered / Uncovered Nodes


    Problem Description
 
 

    You are given the root of a binary tree A, you need to return the absolute difference between sum of all covered elements and the sum of all uncovered elements.

    In a binary tree, a node is called Uncovered if it appears either on left boundary or right boundary. Rest of the nodes are called covered.



    Problem Constraints
    1 <= Number of nodes in the binary tree <= 105


    Input Format
    The first argument is the root of the binary tree A.


    Output Format
    Return a single integer denoting the absolute difference of the sum of covered and uncovered nodes.


    Example Input
    Input 1:
        2
       / \
      1   4
     /   / \
    6  10   5
    Input 2:

          1
         /
        2
       /
      3


    Example Output
    Output 1:
    8
    Output 2:

    6


    Example Explanation
    Explanation 1:
    The node with value 10 is the only covered node. All other nodes are uncovered.
    Therefore, the absolute difference is |(10) - (2 + 1 + 4 + 6 + 5)| = 8
    Explanation 2:

    All the given nodes are uncovered. Hence, the answer is sum of given nodes - 6.
*/

/**
 *
 * @author abhishekchopra
 */
public class CoveredUncoveredNodes {
    public Long coveredNodes(BST.Node<Integer, Integer> A) {
        Queue<BST.Node<Integer, Integer>> nodes = new LinkedList<>();
        long res = 0;
        
        if (A == null) {
            return res;
        }
        
        nodes.add(A);
        
        while (!nodes.isEmpty()) {
            int N = nodes.size();
            int i = N - 1;
            while (i >= 0) {
                BST.Node<Integer, Integer> curr = nodes.remove();
                if (i == 0 || i == N - 1) {
                    res += (long) (curr.key);
                } else {
                    res -= (long) (curr.key);
                }
                if (curr.left != null) {
                    nodes.add(curr.left);
                }
                    
                if (curr.right != null) {
                    nodes.add(curr.right);
                }
                i--;   
            }
        }
        
        return Math.abs(res);
    }
}
