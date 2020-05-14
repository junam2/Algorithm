package q9095;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[] dx = {1,2,3};
	static int n;
	static int count = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int t = Integer.parseInt(br.readLine());
		
		for(int i=0; i<t; i++) {
			n = Integer.parseInt(br.readLine());
			
			for(int j=1; j<=3; j++) {
				dfs(j);
			}
			
			System.out.println(count);
			count = 0;
		}
	}
	
	static void dfs(int a) {
		if(a == n) {
			count++;
			return;
		} else if(a > n) {
			return;
		}
		
		for(int i=0; i<dx.length; i++) {
			dfs(a+dx[i]);
		}
	}

}
