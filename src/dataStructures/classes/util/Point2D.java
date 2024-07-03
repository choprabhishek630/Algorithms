/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Comparator;

/**
 *
 * @author abhishekchopra
 */
public class Point2D {
    private final double x;
    private final double y;
    public static final Point2D ORIGIN = new Point2D(0, 0);

    private class BiPolarComparator implements Comparator<Point2D> {
        private final Point2D bipolarPoint;
        public BiPolarComparator(Point2D p) {
            this.bipolarPoint = p;
        }

        @Override
        public int compare(Point2D a, Point2D b) {
            int quadrandA = bipolarPoint.whichQuadrantRelative(a);
            int quadrandB = bipolarPoint.whichQuadrantRelative(b);
            if (quadrandA < quadrandB)                              return -1;
            else if (quadrandA > quadrandB)                         return 1;
            else if (bipolarPoint.slope(a) > bipolarPoint.slope(b)) return 1;
            else if (bipolarPoint.slope(a) < bipolarPoint.slope(b)) return -1;
            else                                                    return 0;
        }
    }

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Point2D other = (Point2D) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        return Double.doubleToLongBits(this.y) == Double.doubleToLongBits(other.y);
    }

    public static int ccw(Point2D a, Point2D b, Point2D c) {
        double area2 = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        if (area2 > 0) return 1;
        else if (area2 < 0) return -1;
        else return 0;
    }

    public Comparator<Point2D> BiPolarComparator() {
        return new BiPolarComparator(this);
    }


    /*
      * returns the quadrant with respect to origin
      * return 0 for 1st quadrant, 1 for second quadrant, 2 for 3rd quadrant and 3 for fourth quadrand
      * return -1 for points to be same
    */
    public static int whichQuadrant(Point2D p) {
        if (p.equals(ORIGIN))           return -1;
        else if (p.x > 0 && p.y >= 0)   return 0;
        else if (p.x <= 0 && p.y > 0)   return 1;
        else if (p.x < 0 && p.y <= 0)   return 2;
        else if (p.x >= 0 && p.y < 0)   return 3;
        else                            return -1;
    }

    /*
      * returns the quadrant with respect to itself
      * return 0 for 1st quadrant, 1 for second quadrant, 2 for 3rd quadrant and 3 for fourth quadrand
      * return -1 for points to be same
    */
    public int whichQuadrantRelative(Point2D p) {
        return whichQuadrant(new Point2D(p.x - this.x, p.y - this.y));
    }

    public double slope(Point2D p) {
        if (this.equals(p)) return Double.NEGATIVE_INFINITY;
        return (p.y - this.y) / (p.x - this.x);
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
}
