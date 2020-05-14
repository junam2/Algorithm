package q1149;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] S = new int[n+1][3];
		int[][] dp = new int[n+1][3];
		
		for(int i=1; i<=n; i++) {
			String[] str = br.readLine().split(" ");
			for(int j=0; j<3; j++) {
				S[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		dp[1][0] = S[1][0]; dp[1][1] = S[1][1]; dp[1][2] = S[1][2];
		
		for(int i=2; i<=n; i++) {
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + S[i][0];
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + S[i][1];
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + S[i][2];
		}
		
		int tmp = Math.min(dp[n][0], dp[n][1]);
		
		System.out.println(Math.min(tmp, dp[n][2]));
 	}

}
