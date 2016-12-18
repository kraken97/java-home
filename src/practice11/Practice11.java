package practice11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by kraken on 27.11.16.
 */

class  Point{
    Point(int x,int y){
        this.x=x;
        this.y=y;
    }
    int x;
    int y;
    Point prev;

    @Override
    public String toString() {
        return  x+"-"+y;
    }
}

public class Practice11 {
   static final int OPEN=0;
   static final  int WALL=1;
   static final  int EXIT=3;
   static   final  int VISITED=5;


    public static void main(String[] args) throws IOException {
        BufferedReader r=new BufferedReader(new FileReader("labyrinth.tst"));
        int[] dimensions=getIntArray(r.readLine().split(" +"));
        int[][] maze=new int[dimensions[1]][dimensions[0]];

        for (int i = 0; i < dimensions[1]; i++) {
            maze[i]=getIntArray(r.readLine().split(" +"));
        }


        int[][] exit=setExit(maze);
        printMaze(exit);
        Point res= getExitBFS(exit);
        printExitPath(res);


//        int[][] exit=setExit(maze);
//        printMaze(exit);
//        Point res= getExit(exit);
//        printExitPath(res);
    }


    public  static  void printExitPath(Point p){
        Point curr=p;
        while (curr!=null){
            System.out.println(curr);
            System.out.println('\u2191');
            curr=curr.prev;
        }
    }

    public  static  Point getExitBFS(int maze[][]){
        maze[1][1]=VISITED;
        Point currentPoint=new Point(1,1);
        Queue<Point> queue = new LinkedList();
        queue.add(currentPoint);
        int steps=0;

        while (!queue.isEmpty()){

            Point point=queue.remove();
            Point child;

            while ((child=getNeighbour(maze,point))!=null){
                steps+=1;
                child.prev=point;
                int res=maze[child.x][child.y];
                if (res==EXIT){
                    printMaze(maze);
                    System.out.println("steeps:"+steps);
                    System.out.println(child.x +" -- " +child.y);
                    return child;
                }

                maze[child.x][child.y]=VISITED;
                queue.add(child);
            }

        }
        System.out.println("there is no exit");
        return  null;

    }

//dfs
    public  static Point  getExit(int maze[][]){

        maze[1][1]=VISITED;
        Point currentPoint=new Point(1,1);
        Stack<Point> points =new Stack<>();
        int steps=0;
        while (maze[currentPoint.x][currentPoint.y]!=EXIT){
            steps+=1;
            Point n=getNeighbour(maze,currentPoint);
            if (n!=null){
                points.push(currentPoint);
                n.prev=currentPoint;
                currentPoint=n;
                if (maze[n.x][n.y]==EXIT){
                    break;
                }
                maze[n.x][n.y]=VISITED;
            }else if (!points.isEmpty()){
                currentPoint=points.pop();
            }else {
                System.out.println("there is no exit");
                return  null;
            }
        }
        printMaze(maze);
        System.out.println("steeps:"+steps);
        System.out.println(currentPoint.x +" -- " +currentPoint.y);
        return  currentPoint;

    }



    public  static  Point getNeighbour(int maze[][],Point p){
        boolean res;
        if (p.x+1<maze.length){
            res=maze[p.x+1][p.y]==OPEN||maze[p.x+1][p.y]==EXIT;
            if (res){
                return  new Point(p.x+1,p.y);
            }
        }
        if (p.y+1<maze[0].length){
            res=maze[p.x][p.y+1]==OPEN||maze[p.x][p.y+1]==EXIT;
            if (res){
                return  new Point(p.x,p.y+1);
            }
        }
        if (p.x>0){
            res=maze[p.x-1][p.y]==OPEN||maze[p.x-1][p.y]==EXIT;
            if (res){
                return  new Point(p.x-1,p.y);
            }
        }
        if (p.y>0){
            res=maze[p.x][p.y-1]==OPEN||maze[p.x][p.y-1]==EXIT;
            if (res){
                return  new Point(p.x,p.y-1);
            }
        }
        return  null;
    }


    public  static  int[][] setExit(int maze[][]){
        int res=1;
        int x=0;
        int y=0;
        Random random=new Random();
        while (res!=0){
             x=random.nextInt(maze.length);
             y=random.nextInt(maze[0].length);
            res=maze[x][y];
        }
        maze[x][y]=EXIT;
        return  maze;
    }


    public  static  void  printMaze(int arr[][] ){
        System.out.println("maze: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }

    public static int[] getIntArray(String[] ar) {
        return Arrays.stream(ar).mapToInt(Integer::parseInt).toArray();
    }

}
