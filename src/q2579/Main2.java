package q2579;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n+1];
		int[][] dp = new int[n+1][3];
		
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		dp[1][1] = arr[1]; dp[1][2] = 0;
		dp[2][1] = arr[2]; dp[2][2] = arr[1] + arr[2];
		
		for(int i=3; i<=n; i++) {
			dp[i][1] = Math.max(dp[i-2][1], dp[i-2][2]) + arr[i];
			dp[i][2] = dp[i-1][1] + arr[i];
		}
		
		System.out.println(Math.max(dp[n][1], dp[n][2]));
	}

}
