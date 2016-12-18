import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kraken on 02.10.16.
 */
public class Min {



    public static  int calc(int[] ar) {
        int min = 0;
        int prev = 0;
        for (int i = 0; i < ar.length; i++) {
            if (ar[i] - prev > 1) {
                if (prev + 1 < min || min == 0)
                    min = prev + 1;
            }
            prev = ar[i];
        }
        return min;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String r[] = bf.readLine().split(" ");
        int[] ar = Arrays.stream(r).skip(1).mapToInt(Integer::parseInt).sorted().toArray();
        int min=calc(ar);
        System.out.println(min);


    }
}
