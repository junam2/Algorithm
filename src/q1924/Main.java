package q1924;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String[] days = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
		int[] months = {31,28,31,30,31,30,31,31,30,31,30,31};
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		int real_day = 0;
		
		for(int i=0; i<a-1; i++) {
			real_day += months[i];
		}
		real_day += b;
		
		System.out.println(days[(real_day)%7]);
	}

}
