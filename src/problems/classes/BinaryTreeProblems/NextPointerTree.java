/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinaryTreeProblems;

import java.util.Queue;
import java.util.LinkedList;

// for revision
// https://www.interviewbit.com/problems/populate-next-right-pointers-tree/

/*
    Populate Next Right Pointers Tree

    

    Problem Description

    Given a binary tree
        struct TreeLinkNode {
          TreeLinkNode *left;
          TreeLinkNode *right;
          TreeLinkNode *next;
        } 
    Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
    Initially, all next pointers are set to NULL.

    NOTE:
    You may only use constant extra space.
    Recursion has memory overhead and does not qualify for constant space.
    The tree need not be a perfect binary tree.


    Input Format
    The first argument is TreeNode A, pointing to the root of the tree.


    Output Format
    Update the given TreeNode A.


    Example Input
             1
           /  \
          2    3
         / \  / \
        4  5  6  7


    Example Output
             1 -> NULL
           /  \
          2 -> 3 -> NULL
         / \  / \
        4->5->6->7 -> NULL
*/

/**
 *
 * @author abhishekchopra
 */
public class NextPointerTree {
    public static final class TreeLinkNode {
        public int val;
        public TreeLinkNode left, right, next;
        public TreeLinkNode(int x) {
            this.val = x;
        }
    }
    
    public void connect(NextPointerTree.TreeLinkNode root) {
        if (root == null)
            return;
        
        Queue<NextPointerTree.TreeLinkNode> nodes = new LinkedList<>();
        
        nodes.add(root);
        
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            
            while (--size >= 0) {
                NextPointerTree.TreeLinkNode node = nodes.remove();
                
                if (size > 0) {
                    node.next = nodes.peek();
                }
                
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
            }
        }
    }
}
