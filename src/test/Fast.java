package test;

import princeton.lib.In;
import princeton.lib.StdDraw;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Fast {
    private Set<Set<Point>> results;
    private Point[] points;

    public Fast(){

    }

    private Fast(Point[] points){
        this.points = points;
        collinear(points);
    }

    private void collinear(Point[] points){
        results = new HashSet<Set<Point>>();

        for(Point p: points){
            Arrays.sort(points, p.SLOPE_ORDER);

            Set<Point> collinear = new HashSet<Point>();
            Double prevSlope = null;
            Point prevPoint = p;
            boolean foundEquals = false;

            collinear.add(p);

            for(Point x: points){

                if(x == p) continue;

                double slope = p.slopeTo(x);
                if(foundEquals && slope != prevSlope)
                    break;
                else {
                    if(prevSlope!=null && slope == prevSlope){
                        System.out.println(x.toString());
                        collinear.add(x);
                        // we add this  to have full chain of all points
                        // we dont have duplicates because we add points in collinear set
                        collinear.add(prevPoint);
                        foundEquals = true;
                    }
                    prevSlope = slope;
                    prevPoint = x;
                }
            }

            if(collinear.size() >= 4){
                //in result we  will have only one  chain of points  but we add multiple times   to result set collinear set
                //it is caused  that the result is the set structure
                results.add(collinear);
            }
        }
    }

    private String toStr(){
        StringBuilder sb = new StringBuilder();
        for(Set<Point> alp : results){
            boolean first = true;
            Point[] ps = alp.toArray(new Point[0]);
            Arrays.sort(ps);
            for(Point p : ps){
                if(!first)
                    sb.append(" -> ");
                sb.append(p);
                first = false;
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void draw(){
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);


        for(Point p: points){
            p.draw();
        }
        // display to screen all at once


//    Set<String> h = new HashSet<String>();

        // draw segments
        for(Set<Point> alp : results){
            Point[] ps = alp.toArray(new Point[0]);
            Arrays.sort(ps);
            ps[0].drawTo(ps[ps.length-1]);
        }
        StdDraw.show(0);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        int i[] = In.readInts("test.txt");
        Point ps[] = new Point[i[0]];

        int j=0;
        for(int h=0; (h+2) < i.length; h+=2)
            ps[j++] = new Point(i[h+1],i[h+2]);

        Fast f = new Fast(ps);
        System.out.println(f.toStr());
        f.draw();
    }

}