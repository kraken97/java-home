        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.Scanner;
        import java.util.stream.IntStream;

        /**
         * Created by kraken on 02.10.16.
         */
        public class Empty {

            public static int calc(int[] ar) {
                int[] ar1 = new int[ar[0]];
                for (int i = 1; i < ar.length; i++)
                    ar1[ar[i] - 1] = 1;
                return linearSearch(ar1, 0) + 1;
            }

            public static int linearSearch(int[] list, int key) {
                for (int i = 0; i < list.length; i++)
                    if (key == list[i])
                        return i;
                return -1;
            }

            public static void main(String[] args) throws IOException {

                Scanner s =new Scanner(System.in);
                int n= s.nextInt();
                int[] arr=new int[n];
                arr[0]=n;
                for (int i = 1; i <n ; i++) {
                    arr[i]=s.nextInt();
                }

                s.close();
    //            int[] ar = Arrays.stream(r).mapToInt(Integer::parseInt).toArray();

                int min=calc(arr);
               System.out.println(min);


            }
        }
