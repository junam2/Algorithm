package q15651;

import java.util.Scanner;

public class Main {
	static int n;
	static int m;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		arr = new int[m];
		
		backTracking(0);
		System.out.println(sb);
	}
	
	public static void backTracking(int k) {
		if(k == m) {
			for(int i=0; i<arr.length; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
		} else {
			for(int i=1; i<=n; i++) {
				arr[k] = i;
				backTracking(k+1);
			}
		}
	}

}
