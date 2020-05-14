package q10809;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int[] result = new int[26];
        
        for(int i=0; i<result.length; i++) {
            result[i] = -1;
        }
        
        for(int i=0; i<str.length(); i++) {
            int tmp = (int) (str.charAt(i) - 'a');
            
            if(result[tmp] == -1) {
                result[tmp] = i;
            }
        }
        
        for(int i=0; i<result.length; i++) {
            System.out.print(result[i] + " ");
        }        
    }
}