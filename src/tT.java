import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.IntStream;

public class tT {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        while(true) {
            try {
                int len = Integer.parseInt(bf.readLine());
            } catch (Exception e) {
                break;
            }

            String r[] = bf.readLine().split(" ");
            int[] ar = getIntArray(r);
            String bounds[] = bf.readLine().split(" ");
            int[] ar2 = getIntArray(bounds);
            System.out.println(calculate(ar, ar2[0], ar2[1]));
        }
    }

    public static int[] getIntArray(String[] ar) {
       return Arrays.stream(ar).mapToInt(Integer::parseInt).toArray();
    }
    public static long calculate(int[] list, int l, int m){
        int n=list.length;
        if (l > m)
            return 0;

        for (int i = 0,j=list.length-1; list[i]<l||list[j]>m; i++,j--) {
            if (list[i]<l) n--;
            if (list[j]>m) n--;
        }
        return n;
    }
}

