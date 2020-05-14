package swae_q14501;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] time = new int[n+1];
		int[] pay = new int[n+1];
		int[] dp = new int[n+1];
		
		for(int i=1; i<=n; i++) {
			String[] str = br.readLine().split(" ");
			time[i] = Integer.parseInt(str[0]);
			pay[i] = dp[i] = Integer.parseInt(str[1]);
		}

		
		for(int i=2; i<=n; i++){
			for(int j=1; j<i; j++){
				if(time[j]<=i-j){
					dp[i] = Math.max(pay[i]+dp[j], dp[i]);
				}
			}
		}

		int max=0;
		for(int i=1; i<=n; i++){
			if(time[i]+i<=n+1){
				if(max<dp[i]){
					max = dp[i];
				}
			}
		}
		System.out.println(max);
	}

}
