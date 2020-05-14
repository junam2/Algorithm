package q14563;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int t = sc.nextInt();
        int[] arr = new int[t];
        
        for(int i=0; i<arr.length; i++) {
            arr[i] = sc.nextInt();
        }
        
        for(int i=0; i<arr.length; i++) {
            int tmp = arr[i];
            int total = 0;
            boolean flag = true;
            
            for(int j=1; j<=tmp/2; j++) {
                if(tmp%j == 0) {
                    total += j;
                }
                
                if(total > tmp) {
                    flag = false;
                    break;
                }
            }
            if(total == tmp) {
                System.out.println("Perfect");
            } else if(flag) {
                System.out.println("Deficient");
            } else {
                System.out.println("Abundant");
            }
        }
    }
}
