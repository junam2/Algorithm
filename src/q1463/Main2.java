package q1463;

import java.util.Scanner;

public class Main2 {	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int[] dp = new int[n+1];
		
		for(int i=1; i<=n; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		
		dp[n] = 0;
		
		for(int i=n-1; i>=1; i--) {
			int a = Math.min(dp[i+1] +1, (i*2<=n)?dp[i*2]+1:Integer.MAX_VALUE);
			int b = Math.min(a, (i*3<=n)?dp[i*3]+1:Integer.MAX_VALUE);
			
			dp[i] = b;
		}
		
		System.out.println(dp[1]);
	}

}
