package q2839;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int count = 0;
		
		while(true) {
			if(n%5 == 0) {
				System.out.println(n/5+count);
				break;
			} else if(n<0) {
				System.out.println(-1);
				break;
			}
			
			n -= 3;
			count++;
		}
		
	}

}
