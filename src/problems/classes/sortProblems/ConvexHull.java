/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sortProblems;

import stack.LLStack;
import stack.Stack;
import geometry.Point2D;
import sort.SortUtil;
import sort.ShellSort;

// for revision
// https://www.coursera.org/learn/algorithms-part1/lecture/KHJ1t/convex-hull

/**
 *
 * @author abhishekchopra
 */
public class ConvexHull {
    
    private static int findLowestYPoint(Point2D[] points) {
        int N = points.length;
        int idx = 0;
        for (int i = 1 ; i < N ; i++) {
            if (points[i].getY() < points[idx].getY())                                                  idx = i;
            else if (points[i].getY() == points[idx].getY() && points[i].getX() > points[idx].getX())   idx = i;
        }
        return idx;
    }
    
    public static Point2D[] findHull(Point2D[] points) {
        int N = points.length;
        Stack<Point2D> hull = new LLStack<>();
        SortUtil.swap(points, 0, findLowestYPoint(points));
        ShellSort.sort(points, points[0].BiPolarComparator());
        for (Point2D p : points) System.out.print("(" + p.getX() + ", " + p.getY() + "), ");
        System.out.println();
        
        hull.push(points[0]);
        hull.push(points[1]);
        
        for (int i = 2 ; i < N ; i++) {
            Point2D b = hull.pop();
            while (Point2D.ccw(hull.top(), b, points[i]) <= 0)
                b = hull.pop();
            
            hull.push(b);
            hull.push(points[i]);
        }
        
        return hull.toArray();
    }
    
    public static void main(String[] args) {
        Point2D[] points = {new Point2D(0, 0), new Point2D(2, 1), new Point2D(3, 2), new Point2D(2, 2), new Point2D(1, 1.5), new Point2D(1, 3), new Point2D(-1, 1), new Point2D(-2, 1.5), new Point2D(-2, 2.5)};
        SortUtil.shuffle(points);
        
        Point2D[] hull = findHull(points);
        
        for (Point2D p : hull) System.out.print("(" + p.getX() + ", " + p.getY() + "), ");
        
        System.out.println();
    }
}
