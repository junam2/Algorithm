package swea_q14503;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int N, M;
	static int[][] map;
	static boolean[][] isClean;
	static int r_x, r_y, r_d;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str1 = br.readLine().split(" ");
		M = Integer.parseInt(str1[0]);
		N = Integer.parseInt(str1[1]);
		
		map = new int[M][N];
		isClean = new boolean[M][N];
		
		String[] str2 = br.readLine().split(" ");
		r_x = Integer.parseInt(str2[0]);
		r_y = Integer.parseInt(str2[1]);
		r_d = Integer.parseInt(str2[2]);
		
		for(int i=0; i<M; i++) {
			String[] str3 = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(str3[j]);
			}
		}
		
		cleaning(r_x, r_y, r_d);
		
		System.out.println(checkClean());
	}
	
	static void dfs(int x, int y, int d, int stop) {
		int rotate_left = (d+3)%4;
		
		int rotate_back = (d+2)%4;
		
		if(isRange(x+dx[rotate_left], y+dy[rotate_left]) 
				&& !isClean[x+dx[rotate_left]][y+dy[rotate_left]] && map[x+dx[rotate_left]][y+dy[rotate_left]] == 0) {
			cleaning(x+dx[rotate_left], y+dy[rotate_left], rotate_left);
			return;
		} else {
			if(stop<4) {
				dfs(x, y, rotate_left, stop + 1);
				return;
			}
			
			if(stop==4) {
				if(isRange(x+dx[rotate_back], x+dy[rotate_back]) && map[x+dx[rotate_back]][y+dy[rotate_back]] == 0) {
					dfs(x+dx[rotate_back], y+dy[rotate_back], d, 0);
					return;
				} else{
					return;
				}
			}
		}
		
	}
	
	static boolean isRange(int x, int y) {
		if(x>=0 && x<M && y>=0 && y<N) {
			return true;
		} else {
			return false;
		}
	}
	
	static void cleaning(int x, int y, int d) {
		isClean[x][y] = true;
		dfs(x,y,d, 0);
	}
	
	static int checkClean() {
		int total = 0;
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(isClean[i][j]) {
					total++;
				}
			}
		}
		
		return total;
 	}
	
	
}
