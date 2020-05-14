package swae_q14501;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main2 {
	public static int[] time;
	public static int[] pay;
	public static int n;
	public static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		time = new int[n+1];
		pay = new int[n+1];
		
		for(int i=1; i<=n; i++) {
			String[] str = br.readLine().split(" ");
			time[i] = Integer.parseInt(str[0]);
			pay[i] = Integer.parseInt(str[1]);
		}
		
		dfs(1, 0);
		
		System.out.println(max);
	}
	
	public static void dfs(int day, int sum) {
		if(day > n) {
			max = Math.max(max, sum);
			return;
		}
		
		
		if(day + time[day] -1 <= n) {
			dfs(day + time[day] , sum + pay[day]);
		}
		
		dfs(day+1, sum);
	}

}
