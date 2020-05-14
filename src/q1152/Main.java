package q1152;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] str = sc.nextLine().split(" ");
		int count = 0;
		
		
		if(str[str.length-1].equals(" ")) {
			count++;
		}
		
		System.out.println(str.length-count);
	}

}
