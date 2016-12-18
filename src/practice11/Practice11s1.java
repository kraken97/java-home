package practice11;

import princeton.lib.In;

import java.io.File;

/**
 * Created by kraken on 27.11.16.
 */
public class Practice11s1 {

    public static void main(String[] args) {
        In stream = new In(new File("D:\\matrix.txt"));
        int n = stream.readInt();
        int[][] mat = new int[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
            {
                mat[i][j] = stream.readInt();
            }
        boolean flag = true;
        for(int i = 0; i < n;i++)
            for(int j = i; j < n; j++){
                if(mat[i][j] != mat[j][i])
                {
                    flag = false;
                    break;
                }
            }

        if(flag){
            System.out.println("YES");
        }
        else{
            System.out.println("NO");
        }
    }
}
