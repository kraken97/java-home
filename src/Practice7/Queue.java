package Practice7;

import princeton.lib.In;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Created by kraken on 29.10.16.
 */
public class Queue {


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int firstLine[]=getIntArray(bf.readLine().split(" "));
        int secondLine[]=getIntArray(bf.readLine().split(" "));


        System.out.println(calculate(firstLine[1],secondLine));
    }
    public static int[] getIntArray(String[] ar) {
        return Arrays.stream(ar).mapToInt(Integer::parseInt).toArray();
    }


    
    public  static int calculate(int range, int[] originalArray ){
        PriorityQueue<Integer> integers = new PriorityQueue<>(range, new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return  integer>t1?1:-1;
            }
        });

        for (int i = 0; i < range; i++) {
            integers.add(0);
        }
        int max=-99999;
        for (int i = 0; i < originalArray.length; i++) {
            int min=integers.poll();
            int curr=min+originalArray[i];
            integers.add(min+originalArray[i]);
            if (curr>max) max=curr;

        }
        return  max;
    }

}
