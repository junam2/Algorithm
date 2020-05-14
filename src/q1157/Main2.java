package q1157;

import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next().toUpperCase();
		int max = Integer.MIN_VALUE;
		int index = -1;
		int count = 0;
		
		int[] arr = new int[26];
		
		for(int i=0; i<str.length(); i++) {
			char tmp = str.charAt(i);
			
			arr[tmp-'A']++;
		}
		
		for(int i=0; i<arr.length; i++) {
			if(max < arr[i]) {
				max = arr[i];
			}
		}
		
		for(int i=0; i<arr.length; i++) {
			if(max == arr[i]) {
				count++;
				index = i;
			}
		}
		
		if(count > 1) {
			System.out.println("?");
		} else {
			System.out.println((char) (index + 'A'));
		}
	}

}
