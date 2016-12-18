package test.Points;

import princeton.lib.In;
import princeton.lib.StdDraw;

public class Read {

    public void read() {
        Point lastPoint = null;
        In inputFile = new In("data/rs1423.txt");
        int n = inputFile.readInt();

        Point[] points = new Point[n];
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        for (int i = 0; !inputFile.isEmpty(); i++) {
            int x = inputFile.readInt();
            int y = inputFile.readInt();
            if (lastPoint == null)
                lastPoint = new Point(x, y);
            else if (lastPoint.y > y)
                lastPoint = new Point(x, y);
            points[i] = new Point(x, y);
        }
    }
} 
