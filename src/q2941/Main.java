package q2941;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		String[] word = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
		int count = 0;
		
		for(int i=0; i<word.length; i++) {
			if(str.indexOf(word[i]) > -1) {
				str = str.replaceAll(word[i], "*");
			}
		}
		
		System.out.println(str.length());
	}
}
