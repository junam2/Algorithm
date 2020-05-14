package q9095;

import java.util.Scanner;

public class Main2 {
	static int count = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		for(int q=0; q<t; q++) {		
			int n = sc.nextInt();
			
			dfs(n,0);
			System.out.println(count);
			count = 0;
		}
	}
	
	public static void dfs(int n, int sum) {
		if(sum == n) {
			count++;
			return;
		} else if(sum > n) {
			return;
		}
		
		dfs(n, sum+1);
		dfs(n, sum+2);
		dfs(n, sum+3);
		
	}

}
