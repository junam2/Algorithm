package q1568;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int count = 1;
		int time = 0;
		
		while(n!=0) {
			if(n < count) {
				count = 1;
			}
			
			n -= count;
			time++;
			count++;
		}
		
		System.out.println(time);
	}

}
