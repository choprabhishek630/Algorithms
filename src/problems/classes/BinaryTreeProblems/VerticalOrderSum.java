/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinaryTreeProblems;

import java.util.ArrayList;
import symbolTables.BST;

// for revision
// https://www.interviewbit.com/problems/vertical-sum-of-a-binary-tree/


/*
    Vertical Sum of a Binary Tree

    

    Problem Description
 
 

    You are given the root of a binary tree A.

    You have to find the vertical sum of the tree.

    A vertical sum denotes an array of sum of the different verticals of a binary tree,

    where the leftmost vertical sum is the first element of the array and rightmost vertical is the last.



    Problem Constraints
    1 <= Number of nodes in the binary tree <= 105
    1 <= Ai <= 103


    Input Format
    The first argument is the root of a binary tree A.


    Output Format
    Return an array denoting the vertical sum of the binary tree.


    Example Input
    Input 1:
    A =     1
          /   \
         2     3
        / \   / \
       4   5 6   7
    Input 2:

    A =     1
           /
          2
         /
        3


    Example Output
    Output 1:
    [4, 2, 12, 3, 7]
    Output 2:

    [3, 2, 1]


    Example Explanation
    Explanation 1:
    The element 4 is present in the leftmost vertical. 
    The middle vertical consists of 3 elements 1, 5, 6.
    The resultant array is [4, 2, 12, 3, 7].
    Explanation 2:

    The leftmost vertical is the element 3. The next verticals are 2 and 1.
    Hence, the resultant array is [3, 2, 1].
*/

/**
 *
 * @author abhishekchopra
 */
public class VerticalOrderSum {
    private int minVerticalDistanceFromRoot(BST.Node<Integer, Integer> A, int dist) {
        if (A == null) return Integer.MAX_VALUE;
        int left = minVerticalDistanceFromRoot(A.left, dist - 1);
        int right = minVerticalDistanceFromRoot(A.right, dist + 1);
        return Math.min(dist, Math.min(left, right));
    }
    
    private int maxVerticalDistanceFromRoot(BST.Node<Integer, Integer> A, int dist) {
        if (A == null) return Integer.MIN_VALUE;
        int left = maxVerticalDistanceFromRoot(A.left, dist - 1);
        int right = maxVerticalDistanceFromRoot(A.right, dist + 1);
        return Math.max(dist, Math.max(left, right));
    }
    
    private void verticalSum(BST.Node<Integer, Integer> A, int dist, int[] resArr) {
        if (A == null) return;
        resArr[dist] += A.key;
        verticalSum(A.left, dist - 1, resArr);
        verticalSum(A.right, dist + 1, resArr);
    }
    
    public ArrayList<Integer> verticalSum(BST.Node<Integer, Integer> A) {
        int minDistance = minVerticalDistanceFromRoot(A, 0);
        int maxDistance = maxVerticalDistanceFromRoot(A, 0);
        int size = Math.abs(maxDistance - minDistance + 1);
        int[] resArr = new int[size];
        ArrayList<Integer> res = new ArrayList<>(size);
        verticalSum(A, minDistance * -1, resArr);
        for (int a : resArr) {
            res.add(a);
        }
        return res;
    }
}
