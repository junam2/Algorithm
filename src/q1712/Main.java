package q1712;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		
		int result = 0;
		boolean flag = true;
		
		if(b>=c) {
			result = -1;
			flag = false;
		}
		
		if(flag) {
			result = a / (c-b) + 1;
		}
		
		if(result <= 0) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}
	}

}
