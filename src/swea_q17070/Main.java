package swea_q17070;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] map;
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];

		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0,1,1);
		
		System.out.println(result);
	}
	
	static void dfs(int x,int y,int type) {
		if(x==n-1 && y==n-1) {
			result++;
			return;
		}
		
		if(type == 1) {
			if(y+1 < n && map[x][y+1] != 1) {
				dfs(x,y+1,1);
			} 
			
			if(x+1<n && y+1<n && map[x+1][y] != 1 && map[x][y+1] != 1 && map[x+1][y+1] != 1) {
				dfs(x+1,y+1,3);
			}
		} else if(type == 2) {
			if(x+1<n && map[x+1][y] != 1) {
				dfs(x+1,y,2);
			}
			
			if(x+1<n && y+1<n && map[x+1][y] != 1 && map[x][y+1] != 1 && map[x+1][y+1] != 1) {
				dfs(x+1,y+1,3);
			}
		} else {
			if(y+1 < n && map[x][y+1] != 1) {
				dfs(x,y+1,1);
			} 
			
			if(x+1<n && map[x+1][y] != 1) {
				dfs(x+1,y,2);
			}
			
			if(x+1<n && y+1<n && map[x+1][y] != 1 && map[x][y+1] != 1 && map[x+1][y+1] != 1) {
				dfs(x+1,y+1,3);
			}
		}
	}
	
}
