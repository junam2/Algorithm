package q14500;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int max = Integer.MIN_VALUE;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	static int n,m;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<int[]> arr_comb = new ArrayList<int[]>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		
		map = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i=0; i<n; i++) {
			str = br.readLine().split(" ");
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		combination();
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				dfs(i,j,0,0);
				bfs(new dot(i,j));
			}
		}
		
		System.out.println(max);
	}
	
	static void combination() {
		for(int i=0; i<4; i++) {
			for(int j=i+1; j<4; j++) {
				for(int k=j+1; k<4; k++) {
					int[] tmp = new int[3];
					tmp[0] = i;
					tmp[1] = j;
					tmp[2] = k;
					
					arr_comb.add(tmp);
				}
			}
		}
	}

	static void bfs(dot d) {
		int x = d.x;
		int y = d.y;
		
		for(int i=0; i<arr_comb.size(); i++) {
			Queue<dot> q = new LinkedList<dot>();
			q.add(d);
			int[] temp = arr_comb.get(i);
			int tmp_result = 0;
			
			for(int j=0; j<temp.length; j++) {
				int x1 = x + dx[temp[j]];
				int y1 = y + dy[temp[j]];
				
				if(x1>=0 && x1<n && y1>=0 && y1<m) {
					q.add(new dot(x1, y1));
				} else {
					break;
				}
			}
			
			if(q.size() == 4) {
				while(!q.isEmpty()) {
					dot v = q.poll();
					
					tmp_result += map[v.x][v.y];
				}
				
				if(max < tmp_result) {
					max = tmp_result;
				}
			}
		}
	}
	
	static void dfs(int a,int b, int depth, int sum) {
		if(depth == 4) {
			max = Math.max(max, sum);
			return;
		}
		
		for(int i=0; i<4; i++) {
			int x = a + dx[i];
			int y = b + dy[i];
			
			if(x<0 || x>=n || y<0 || y>=m || visited[x][y]) {
				continue;
			}
			
			visited[x][y] = true;
			dfs(x,y,depth+1, sum+map[x][y]);
			visited[x][y] = false;
			
			
		}
	}
}

class dot {
	int x;
	int y;
	
	public dot(int x, int y) {
		this.x = x;
		this.y = y;
	}
}