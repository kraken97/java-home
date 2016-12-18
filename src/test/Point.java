package test;

import princeton.lib.StdDraw;

import java.util.Comparator;

public  class Point implements Comparable<Point> {



    private final int x;
    private final int y;

    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();

    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    // slope between this point and that point
    public double slopeTo(Point that) {
        if (this.x == that.x && this.y == that.y)
            return Double.NEGATIVE_INFINITY;
        else if (this.x == that.x)
            return Double.POSITIVE_INFINITY;
        else if (this.y == that.y)
            return 0.0;
        else
            return ((double) (that.y - this.y)) / (that.x - this.x);
    }

    public int compareTo(Point that) {
        int xCoordCompareTo;
        int yCoordCompareTo = this.y - that.y;
        if (yCoordCompareTo < 0)
            return -1;
        else if (yCoordCompareTo > 0)
            return 1;
        else
            xCoordCompareTo = this.x - that.x;
        if (xCoordCompareTo < 0)
            return -1;
        else if (xCoordCompareTo > 0)
            return 1;
        else
            return 0;
    }
    // comparator (used in sorting other Points by slope relative to the current point)
    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            double cmp = p1.slopeTo(Point.this) - p2.slopeTo(Point.this);
            if (cmp < 0)
                return -1;
            else if (cmp > 0)
                return 1;
            else
                return 0;
        }
    }
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
}
