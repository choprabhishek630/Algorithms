/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraphProblems;
import java.util.ArrayList;
import util.Pair;

// for revision
// https://www.interviewbit.com/problems/maximum-edge-removal/

/*
    Maximum Edge Removal



    Problem Description

    Given an undirected tree with an even number of nodes. Consider each connection between a parent and child node to be an edge.

    You need to remove maximum number of these edges, such that the disconnected subtrees that remain each have an even number of nodes.

    Return the maximum number of edges you can remove.



    Problem Constraints
    2 <= A <= 105

    1 <= B[i][0], B[i][1] <= A

    Integer A will be even.



    Input Format
    First argument is an integer A denoting the number of nodes in the tree.

    Second argument is a 2D array B of size (A-1) * 2, denoting the edge between nodes B[i][0] and B[i][1].



    Output Format
    Return an integer, denoting the maximum number of edges you can remove.



    Example Input
    Input 1:

     A = 6
     B = [
           [1, 2]
           [1, 3]
           [1, 4]
           [3, 5]
           [4, 6]
         ]
    Input 2:

     A = 2
     B = [
           [1, 2]
         ]


    Example Output
    Output 1:

     2
    Output 2:

     0


    Example Explanation
    Explanation 1:

          1
        / | \
       2  3  4
          |   \
          5    6
     Maximum number of edges we can remove is 2, i.e (1, 3) and (1, 4)
    Explanation 2:

     We can't remove any edges.

*/

/**
 *
 * @author abhishekchopra
 */
public class MaxEdgeRemoval {
    
    private Pair<Integer, Integer> solve(ArrayList<ArrayList<Integer>> nodes, int idx) {
        Pair<Integer, Integer> res = new Pair<>(0, 0);
        for (Integer node : nodes.get(idx)) {
            Pair<Integer, Integer> curr = solve(nodes, node);
            res.first += curr.first;
            if (curr.second > 0 && curr.second % 2 == 0) {
                res.first += 1;
            }
            
            res.second += curr.second;
        }
        res.second += 1;
        return res;
    }
    
    public int solve(int A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<ArrayList<Integer>> nodes = new ArrayList<>();
        for (int i = 1 ; i <= A ; i++) {
            nodes.add(new ArrayList<>());
        }
        for (ArrayList<Integer> edge : B) {
            nodes.get(edge.get(0) - 1).add(edge.get(1) - 1);
        }
        
        return solve(nodes, 0).first;
    }
}
