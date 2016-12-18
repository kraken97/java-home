package practice9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by kraken on 12.11.16.
 */
public class Practice9s1 {

    public static int[] getIntArray(String[] ar) {
        return Arrays.stream(ar).mapToInt(Integer::parseInt).toArray();
    }


    public static void main(String[] args) throws IOException {

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        int[] count = getIntArray(r.readLine().split(" +"));

        for (int i = 0; i < count[0]; i++) {
            int[] numbersCount = getIntArray(r.readLine().split(" +"));
                String[] array=new String[numbersCount[0]];
                for (int l = 0; l < numbersCount[0]; l++) {
                    array[l] = r.readLine().split(" +")[0];
                }
                calculate(array);
        }

    }

    public  static   void calculate(final String[] arr){
        Spliterator<String> sp=Arrays.stream(arr).spliterator();
            sp=sp.trySplit().trySplit();

        try {
            sp.forEachRemaining(r->{
                for (int i = 0; i < arr.length; i++) {
                    if(!r.equals(arr[i])&&arr[i].startsWith(r)){
                        throw  new Error("NO");
                    }
                }
            });
            System.out.println("YES");
        }catch (Error e){
            System.out.println(e.getMessage());
        }
    }
}
