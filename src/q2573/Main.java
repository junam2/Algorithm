package q2573;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int n,m;
	static int[][] map;
	static boolean[][] visited;
	static boolean seperate_flag = false;
	static int[][] melting_map;
	static int count = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			melting_map = new int[n][m];
			check2();
			
			if(seperate_flag) {
				break;
			}
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(map[i][j] > 0) {
						check(i,j);
					}
				}
			}
			
			sumMap();
			count++;
			
			if(check3()) {
				break;
			}
		}
		
		if(seperate_flag) {
			System.out.println(count);
		} else {
			System.out.println(0);
		}
		
	}
	
	public static void check(int a, int b) {		
		for(int i=0; i<4; i++) {
			int x = a + dx[i];
			int y = b + dy[i];
			
			if(x>=0 && x<n && y>=0 && y<m && map[x][y] == 0) {
				melting_map[a][b] -= 1;
			}
		}
	}
	
	public static void sumMap() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				map[i][j] += melting_map[i][j];
				
				if(map[i][j] < 0) {
					map[i][j] = 0;
				}
			}
		}
	}
	
	public static void check2() {
		visited = new boolean[n][m];
		boolean one_dfs_flag = false;
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] != 0 && !one_dfs_flag) {
					one_dfs_flag = true;
					dfs(i,j);
					continue;
				}
				
				if(map[i][j] !=0 && !visited[i][j]) {
					seperate_flag = true;
					return;
				}
			}
		}
	}
	
	public static void dfs(int a,int b) {
		visited[a][b] = true;
		
		for(int i=0; i<4; i++) {
			int x = a + dx[i];
			int y = b + dy[i];
			
			if(x>=0 && x<n && y>=0 && y<m && !visited[x][y] && map[x][y] != 0) {
				dfs(x,y);
			}
		}
	}
	
	public static boolean check3() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] != 0) {
					return false;
				}
			}
		}
		
		return true;
	}
}
