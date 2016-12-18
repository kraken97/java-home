import java.util.Scanner;

/**
 * Created by kraken on 31.10.16.
 */
public class Test {
    public static void main(String[] args) {
        Scanner s =new Scanner(System.in);
        int stopNumber=1;
        System.out.println("enter numbers");

        int k=s.nextInt();
        if(k==1){
            System.out.println(" lalal ");
        }else {
            int max=-99999999;
            int min=99999999;
            while (k!=stopNumber){

                if(k>max) max=k;
                if (k<min)min=k;
                k=s.nextInt();
            }

            System.out.println("max:"+max);
            System.out.println("min:"+min);

        }

    }
}
