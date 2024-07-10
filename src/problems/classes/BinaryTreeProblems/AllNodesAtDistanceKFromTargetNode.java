/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinaryTreeProblems;
import java.util.ArrayList;
import java.util.Stack;
import symbolTables.BST;

// for revision
// https://www.interviewbit.com/problems/nodes-at-distance-k/


/*
    Nodes at Distance K



    Problem Description



    Given the root of a binary tree A, the value of a target node B, and an integer C, return an array of the values of all nodes that have a distance C from the target node.

    You can return the answer in any order.


    Problem Constraints
    1 ≤ N ≤ 105 (N is the number of nodes in the binary tree)
    1 ≤ Ai ≤ N (Ai denotes the values of the nodes in the tree)
    All the values in the nodes are unique.
    1 ≤ C ≤ 104


    Input Format
    The first argument is the root node of the binary tree A.
    The second argument is an integer B denoting the label of the target node.
    The third argument is an integer C denoting the distance.


    Output Format
    Return an array of integers denoting the nodes which are at a distance C from the node B.


    Example Input
    Input 1:
    A =     1
           / \
          2   3
         / \
        4   5


    B = 2
    C = 1

    Input 2:

    A =     1
           / \
          2   3
         / \
        4   5


    B = 2
    C = 2



    Example Output
    Output 1:
    [1, 4, 5]
    Output 2:

    [3]


    Example Explanation
    Explanation 1: 

    For the given tree, we have target node as 2.

    All the nodes with are at distance 1, meaning the adjacent nodes are [1, 4, 5].

    Explanation 2:

    The given tree is same, and [3] is the only node with distance 2.

*/

/**
 *
 * @author abhishekchopra
 */
public class AllNodesAtDistanceKFromTargetNode {
    private boolean pathToNode(BST.Node<Integer, Integer> A, int B, Stack<BST.Node<Integer, Integer>> st) {
        if (A == null) return false;
        if (A.key == B) {
            st.push(A);
            return true;
        }
        
        boolean left = pathToNode(A.left, B, st);
        if (left) {
            st.push(A);
        }
        boolean right = pathToNode(A.right, B, st);
        if (right) {
            st.push(A);
        }
        return left || right;
    }
    
    private void findAllChildAtDistanceK(BST.Node<Integer, Integer> A, int k, ArrayList<Integer> res) {
        if (k < 0 || A == null) return;
        if (k == 0) {
            res.add(A.key);
            return;
        }
        
        findAllChildAtDistanceK(A.left, k - 1, res);
        findAllChildAtDistanceK(A.right, k - 1, res);
    }
    
    public ArrayList<Integer> distanceK(BST.Node<Integer, Integer> A, int B, int C) {
        ArrayList<Integer> res = new ArrayList<>();
        Stack<BST.Node<Integer, Integer>> st = new Stack<>();
        if (!pathToNode(A, B, st)) return res;
        while (st.size() > 1) {
            BST.Node<Integer, Integer> curr = st.pop();
            int newC = C - st.size();
            if (newC == 0) {
                res.add(curr.key);
            } else if (newC > 0 && curr.left == st.peek()) {
                findAllChildAtDistanceK(curr.right, newC - 1, res);
            } else if (newC > 0 && curr.right == st.peek()) {
                findAllChildAtDistanceK(curr.left, newC - 1, res);
            }
        }
        findAllChildAtDistanceK(st.pop(), C, res);
        return res;
    }
}
