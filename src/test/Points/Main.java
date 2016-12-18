package test.Points;

import java.util.Arrays;
import java.util.Stack;

import princeton.lib.In;
import princeton.lib.StdDraw;

public class Main {

    public static void main(String[] args) {
        Point lastPoint = null;
        In inputFile = new In("data/rs1423.txt");
        int n = inputFile.readInt();
        Point[] points = new Point[n];
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (int i = 0; i < n; i++) {
            int x = inputFile.readInt();
            int y = inputFile.readInt();
            if (lastPoint == null) {
                lastPoint = new Point(x, y);
            } else if (lastPoint.y > y) {
                lastPoint = new Point(x, y);
            } else if (lastPoint.y == y) {
                if (lastPoint.x < x) {
                    lastPoint = new Point(x, y);
                }
            }
            points[i] = new Point(x, y);
        }
        Arrays.sort(points, 0, n, lastPoint.PolarOrder);
        Stack<Point> stack = new Stack<Point>();
        stack.push(points[0]);
        stack.push(points[1]);

        for (int i = 0; i < n; i++) {
            points[i].draw();
            Point head = points[i];
            Point middle = stack.pop();
            Point tail = stack.peek();
            if (Point.ccw(tail, middle, head) > 0) {
                stack.push(middle);
                stack.push(head);
            } else if (Point.ccw(tail, middle, head) == 0) {
                stack.push(head);
            } else i--;
        }

        Point p = points[0];
        while (!stack.empty()) {
            Point s = stack.pop();
            s.drawTo(p);
            p = s;
        }
    }
}