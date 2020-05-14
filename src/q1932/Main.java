package q1932;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[n+1][n+1];
		int[][] dp = new int[n+1][n+1];
		
		for(int i=1; i<=n; i++) {
			String[] str = br.readLine().split(" ");
			for(int j=1; j<=i; j++) {
				arr[i][j] = Integer.parseInt(str[j-1]);
			}
		}
		
		dp[1][1] = arr[1][1];
		for(int i=2; i<=n; i++) {
			for(int j=1; j<=i; j++) {
				dp[i][j] = Math.max((j-1>=1)?dp[i-1][j-1]+arr[i][j]:Integer.MIN_VALUE, (j<=i)?dp[i-1][j] + arr[i][j]:Integer.MIN_VALUE);
			}
		}
		
		int max = Integer.MIN_VALUE;
		for(int i=1; i<=n; i++) {
			if(max < dp[n][i]) {
				max = dp[n][i];
			}
		}
		
		System.out.println(max);
	}

}
