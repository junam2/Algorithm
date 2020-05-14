package q4963;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	//상,하,좌,우 대각선
	static int[] x = {-1,1,0,0,1,-1,1,-1};
	static int[] y = {0,0,-1,1,1,-1,-1,1};
	static int w; //너비
	static int h; //높이
	static int[][] map;
	static boolean[][] visited;
	static int count;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String[] wh = br.readLine().split(" ");
			w = Integer.parseInt(wh[0]);
			h = Integer.parseInt(wh[1]);
			
			if(w==0 && h==0) {
				break;
			}
			
			map = new int[h][w];
			visited = new boolean[h][w];
			
			for(int i=0; i<h; i++) {
				String[] str = br.readLine().split(" ");
				for(int j=0; j<w; j++) {
					map[i][j] = Integer.parseInt(str[j]);
				}
			}
			count = 0;
			
			
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					if(map[i][j]==1 && !visited[i][j]) {
					dfs(i,j);
					count++;
					}
				}
			}
			
			System.out.println(count);
		}
	}
	
	static void dfs(int a,int b) {
		visited[a][b] = true;
		
		for(int i=0; i<8; i++) {
			if(a+x[i] >= 0 && a+x[i] < h && b+y[i] >= 0 && b+y[i] < w
					&& map[a+x[i]][b+y[i]] == 1 
					&& !visited[a+x[i]][b+y[i]]) {
				dfs(a+x[i], b+y[i]);
			}
		}
	}

}
