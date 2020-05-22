package q16929;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int n,m;
	static char[][] map;
	static boolean[][] visited;
	static boolean flag = false;
	static int sx,sy;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		
		for(int i=0; i<n; i++) {
			char[] arr = br.readLine().toCharArray();
			map[i] = arr;
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(!flag) {
					visited = new boolean[n][m];
					sx = i;
					sy = j;
					dfs(i,j,1);
				}
			}
		}
		if(flag) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
	
	static void dfs(int x,int y,int cnt) {
		if(flag) return;
		
		visited[x][y] = true;
		
		for(int i=0; i<4; i++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			
			if(xx<0 || xx>=n || yy<0 || yy>=m || map[x][y] != map[xx][yy]) continue;
			
			if(!visited[xx][yy]) {
				visited[xx][yy] = true;
				dfs(xx,yy,cnt+1);
			} else {
				if(cnt>=4 && xx == sx && yy==sy) {
					flag = true;
					return;
				}
			}
		}
	}
}

class edge {
	int x;
	int y;
	
	public edge(int x,int y) {
		this.x = x;
		this.y = y;
	}
}

