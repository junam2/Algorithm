package q2468;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main2 {
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,-1,1};
	static int n;
	static int[][] map;
	static boolean[][] visited;
	static int max_height = 0;
	static int max_island = 0;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		
		for(int i=0; i<n; i++) {
			String[] str = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				if(max_height < Integer.parseInt(str[j])) {
					max_height = Integer.parseInt(str[j]);
				}
			}
		}
		
		for(int q=0; q<max_height; q++) {
			visited = new boolean[n][n];
			int tmp_max_island = 0;
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(map[i][j] > q && !visited[i][j]) {
						bfs(new edge(i,j), q);
						tmp_max_island++;
					}
				}
			}
			
			if(max_island < tmp_max_island) {
				max_island = tmp_max_island;
			}
			
		}
		
		System.out.println(max_island);
	}
	
	static void bfs(edge e, int depth) {
		Queue<edge> q = new LinkedList<edge>();
		visited[e.x][e.y] = true;
		q.add(e);
		
		while(!q.isEmpty()) {
			edge t = q.poll();
			
			for(int i=0; i<4; i++) {
				int x = t.x + dx[i];
				int y = t.y + dy[i];
				
				if(x>=0 && x<n && y>=0 && y<n && map[x][y] > depth && !visited[x][y]) {
					q.add(new edge(x,y));
					visited[x][y] = true;
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
