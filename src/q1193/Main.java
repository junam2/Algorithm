package q1193;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        int total = 0;
        int count = 0;
        
        for(int i=1; i<Integer.MAX_VALUE; i++) {
        	total += i;
        	count++;
        	
        	if(total >= n) {
        		total -= i;
        		break;
        	}
        }
        
        int a = n - total;
        
        if(count%2 == 0) {
        	System.out.println(a + "/" + (count+1-a));
        } else {
        	System.out.println((count+1-a) + "/" + a);
        }
        
        
    }
}
