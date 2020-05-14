package q1436;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int start = 666;
		int count = 0;
		
		while(count != n) {			
//			if(str.contains("666")) {
//				count++;
//			}
			
			if(check666(start)) {
				count++;
			}
			
			start++;
		}
		
		System.out.println(start - 1);
	}
	
	static boolean check666(int n) {
		int count = 0;
		
		while(count!=3) {
			int k = n%10;
			
			if(k==6) {
				count++;
			} else {
				count = 0;
			}
			
			n = n/10;
			if(n==0) break;
		}
		
		if(count == 3) {
			return true;
		} else {
			return false;
		}
	}
}
