package practice12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Created by kraken on 04.12.16.
 */
class  Tuple<T>{
    T t1;
    T t2;
    Tuple(T t1,T t2){
        this.t1=t1;
        this.t2=t2;
    }
    public  Tuple<T>  swap(){
        return new Tuple<T>(this.t2,this.t1);
    }

    @Override
    public String toString() {
        return  this.t1+"-"+this.t2;
    }
}

public class practice12s1 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(reader.readLine());

        boolean[][] graph=new boolean[n][n];

        LinkedList<Tuple<Integer>> tuple=new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String str=reader.readLine();
            if (!str.isEmpty()){
                int[] v= Arrays.stream(str.split(" +")).mapToInt(Integer::parseInt).toArray();
                for (int j = 0; j < v.length; j++) {
                    graph[i][v[j]-1]=true;
                }
            }
        }
        System.out.println(n);
       print(reverse(graph));

    }
    public static void  print(boolean[][] arr){
        for (int i = 0; i < arr.length; i++) {
            int z=0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j]){
                    System.out.print(j+1+" ");
                }
            }
            System.out.println();;
            z=0;
        }
    }

    public static boolean[][] reverse(boolean[][] arr){
        int n=arr.length;
        boolean[][] res=new boolean[n][n];

        for (int i = 0; i < arr.length; i++) {
            for (int i1 = 0; i1 < arr[i].length; i1++) {
                if(arr[i][i1])
                    res[i1][i]=true;
            }
        }
        return  res;

    }
}
