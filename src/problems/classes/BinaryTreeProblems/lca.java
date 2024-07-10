/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinaryTreeProblems;
import util.Pair;
import symbolTables.BST;

// for revision
// https://www.interviewbit.com/problems/least-common-ancestor/


/*
    Least Common Ancestor


    
    Problem Description



    Find the lowest common ancestor in an unordered binary tree given two values in the tree.

    Lowest common ancestor: the lowest common ancestor (LCA) of two nodes v and w in a tree or directed acyclic graph (DAG) is the lowest (i.e. deepest) node that has both v and w as descendants.

    Note:
    You are given 2 values. Find the lowest common ancestor of the two nodes represented by val1 and val2
    No guarantee that val1 and val2 exist in the tree. If one value doesn't exist in the tree then return -1.
    There are no duplicate values.
    You can use extra memory, and helper functions, and can modify the node struct but, you can't add a parent pointer.


    Input Format
    The first argument is a TreeNode A, pointing to the root of the binary tree.
    The second argument is an integer B.
    The third argument is an integer C.


    Output Format
    Return an integer, equal to the value of the LCA of B and C.


    Example Input


            _______3______
           /              \
        ___5__          ___1__
       /      \        /      \
       6      _2_     0        8
             /   \
             7    4


    B = 5
    C = 1



    Example Output
    3


    Example Explanation


            _______3______
           /              \
        ___5__          ___1__
       /      \        /      \
       6      _2_     0        8
             /   \
             7    4

    For the above tree, the LCA of nodes 5 and 1 is 3.
    Please note that LCA for nodes 5 and 4 is 5.
*/

/**
 *
 * @author abhishekchopra
 */
public class lca {
    
    /*
                2
               /
              1
    */
    public Pair<BST.Node<Integer, Integer>, Boolean> lcaHelper(BST.Node<Integer, Integer> A, int B, int C) {
        if (A == null) return new Pair<>(null, false);
        // System.out.print(A.val);
        Pair<BST.Node<Integer, Integer>, Boolean> left = lcaHelper(A.left, B, C);
        if (left.first != null) return left;
        Pair<BST.Node<Integer, Integer>, Boolean> right = lcaHelper(A.right, B, C);
        if (right.first != null) return right;
        BST.Node<Integer, Integer> curr = null;
        boolean exists = false;
        if (A.key == B || A.key == C) {
            if (B == C) {
                curr = A;
            } else if (left.second || right.second) {
                curr = A;
            }
            exists = true;
        } else {
            if (left.second && right.second) {
                curr = A;
            }
            exists = left.second || right.second;
        }
        // System.out.println(A.val + " " + (curr == null) + " " + exists);
        return new Pair<>(curr, exists);
    }
    
    public int lca(BST.Node<Integer, Integer> A, int B, int C) {
        BST.Node<Integer, Integer> res = lcaHelper(A, B, C).first;
        return res == null ? -1 : res.key;
    }
}
