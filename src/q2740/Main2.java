package q2740;

import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int tmp = n;
        int count = 0;
        
        while(true) {
            if(tmp < 10) {
                tmp = tmp*10 + tmp;
            } else {
                int a = tmp/10;
                int b = tmp%10;
                
                tmp = b*10 + (a+b)%10;
            }
            
            if(tmp == n) {
                break;
            } else {
                count++;
            }
        }
        
        System.out.println(count);
        
    }
}