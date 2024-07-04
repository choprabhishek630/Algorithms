/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BSTProblems;
import java.util.ArrayList;
import symbolTables.BST;
import util.Pair;
import java.util.Iterator;

// for revision
// https://www.interviewbit.com/problems/recover-binary-search-tree/


/*
    Recover Binary Search Tree

    Two elements of a binary search tree (BST) are swapped by mistake.

    Tell us the 2 values swapping which the tree will be restored.

    Note:

    A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

    Example :


    Input : 
             1
            / \
           2   3

    Output : 
           [1, 2]

    Explanation : Swapping 1 and 2 will change the BST to be 
             2
            / \
           1   3
    which is a valid BST
*/

/**
 *
 * @author abhishekchopra
 */
public class RecoverBST {
    public ArrayList<Integer> recoverTree(BST.Node<Integer, Integer> A) {
        Iterator<Pair<Integer, Integer>> a = new BST.BSTIterator<Integer, Integer>(A);
        Iterator<Pair<Integer, Integer>> b = new BST.BSTIterator<Integer, Integer>(A, true);
        int x = a.hasNext() ? a.next().first : Integer.MAX_VALUE;
        int y = b.hasNext() ? b.next().first : Integer.MIN_VALUE;
        while (a.hasNext()) {
            int next = a.next().first;
            
            if (x > next) {
                break;
            }
            
            x = next;
        }
        
        while (b.hasNext()) {
            int next = b.next().first;
            
            if (y < next) {
                break;
            }
            
            y = next;
        }
        
        ArrayList<Integer> res = new ArrayList<>();
        res.add(y);
        res.add(x);
        return res;
    }
}
