
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import princeton.lib.In;
import princeton.lib.StdDraw;

public class Brute {
    private ArrayList<ArrayList<Point>> results;
    private Point[] points;

    public Brute(){

    }

    private Brute(Point[] points){
        this.points = points;
        collinear(points);
    }

    private void collinear(Point[] points){
        results = new ArrayList<ArrayList<Point>>();

        int l = points.length;
        if(l<4){
            return;
        }


        Arrays.sort(points);

        for(int a=0; a<l; a++)
            for(int b=a+1; b<l; b++)
                for(int c=b+1; c<l; c++)
                    for(int d=c+1; d<l; d++){
                        double ab = points[a].slopeTo(points[b]);
                        double ac = points[a].slopeTo(points[c]);
                        double ad = points[a].slopeTo(points[d]);
                        if(ab == ac && ac == ad){
                            ArrayList<Point> p = new ArrayList<Point>();
                            p.add(points[a]);
                            p.add(points[b]);
                            p.add(points[c]);
                            p.add(points[d]);
                            results.add(p);
                        }
                    }
    }

    private String toStr(){
        StringBuilder sb = new StringBuilder();
        for(ArrayList<Point> alp : results){
            boolean first = true;
            for(Point p : alp){
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
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        for(Point p: points){
            p.draw();
        }



        for(ArrayList<Point> alp : results){
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
        System.out.println(Arrays.toString(i));
        int j=0;
        for(int h=0; (h+2) < i.length; h+=2)
            ps[j++] = new Point(i[h+1],i[h+2]);

        Brute p = new Brute(ps);
        System.out.println(p.toStr());
        //p.draw();
    }

}