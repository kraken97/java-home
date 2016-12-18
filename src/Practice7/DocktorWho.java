package Practice7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by kraken on 29.10.16.
 */
public class DocktorWho {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        bf.readLine();

            int secondLine[] = getIntArray(bf.readLine().split(" "));
            System.out.println(calculate(secondLine));
            System.out.println();

    }
    public static int[] getIntArray(String[] ar) {
        return Arrays.stream(ar).mapToInt(Integer::parseInt).toArray();
    }

        public static  PriorityQueue<Integer> toQueue(int[] array){
            PriorityQueue<Integer> integers = new PriorityQueue<Integer>(array.length, new Comparator<Integer>() {
                @Override
                public int compare(Integer integer, Integer t1) {
                    return  integer>t1?-1:1;
                }
            });
            for (int i = 0; i < array.length; i++) {
                integers.add(array[i]);
            }
            return  integers;
        }

    public  static  String calculate(int [] array){
        PriorityQueue<Integer> integers = toQueue(array);
        List<Integer> buffer=new LinkedList<>();
        try {
            while (!integers.isEmpty()){
                int num=integers.poll();
                for (int i = 0; i < num; i++) {
                    int curr=integers.poll()-1;
                    if (curr!=0) buffer.add(curr);
                }
                integers.addAll(buffer);
                buffer.clear();
            }
        }catch (Exception e){
            return  "fail";
        }
    return  "ok";
    }
}
