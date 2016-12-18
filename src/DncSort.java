
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by kraken on 16.10.16.
 */
public class DncSort {


    static Comparator<String> comp=(a,b)-> getSortLvl(a)-getSortLvl(b)>0?1:-1;

    public static void main(String[] args) throws IOException {
        BufferedReader r=new BufferedReader(new FileReader("sortDnc.txt"));
        int m=Integer.parseInt(r.readLine());

        for (int j = 0; j < m; j++) {
            r.readLine();
            String[] data=r.readLine().split(" +");
            int[] arr=getIntArray(data);
            int size=arr[0];
            int count=arr[1];
            String[] dnc=new String[count];
            for (int i = 0; i < count; i++) {
                dnc[i]=r.readLine();
            }

                String[] sorted=sortDnc(dnc);
                for (int i = 0; i <sorted.length ; i++) {
                    System.out.println(sorted[i]);

                }
                System.out.println();
        }



    }




    public static int[] getIntArray(String[] ar) {
        return Arrays.stream(ar).mapToInt(Integer::parseInt).toArray();
    }


    public static  String[] sortDnc(String[] dncList){
        Arrays.sort(dncList,comp);
        return  dncList;
    }


    public static   int getSortLvl(String dnc){
        char[] word=dnc.toCharArray();
        int n=0;
        for (int i = 0; i < word.length; i++) {
            for (int j = i; j < word.length; j++) {
                if (word[j]<word[i]){
                    char temp=word[j];
                    word[j]=word[i];
                    word[i]=temp;
                    n++;
                }
            }
        }
        return  n;
    }
}
