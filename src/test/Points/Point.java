package test.Points;
import java.util.Comparator;
import princeton.lib.StdDraw;

public class Point implements Comparable<Point> {
    public int x;
    public int y;

    public final Comparator<Point> PolarOrder = new PolarOrder();

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static int ccw(Point a, Point b, Point c) {
        int an = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        if (an > 0)
            return 1;
        else if (an < 0)
            return -1;
        return 0;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    private class PolarOrder implements Comparator<Point> {
        @Override
        public int compare(Point q1, Point q2) {
            double angle1 = angleTo(q1);
            double angle2 = angleTo(q2);
            if (angle1 < angle2) return -1;
            else if (angle1 > angle2) return 1;
            else if (q1.y > q2.y) return 1;
            return -1;
        }
    }

    private double angleTo(Point that) {
        double dx = that.x - this.x;
        double dy = that.y - this.y;
        return Math.atan2(dy, dx);
    }

    @Override
    public int compareTo(Point p2) {
        int r = Integer.compare(this.x, p2.x);
        return r==0? Integer.compare(this.y, p2.y): r;
    }

}
