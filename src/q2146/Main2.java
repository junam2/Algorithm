package q2146;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int n;
	static int[][] map;
	static int[][] distance;
	static int island = 1;
	static boolean[][] visited;
	static int min_distance = Integer.MAX_VALUE;
	static int tmp_distance = 0;
	static Queue<edge> q;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		visited = new boolean[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					bfsIsland(new edge(i,j));
					island++;
				}
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] != 0 && isSide(i,j)) {
					visited = new boolean[n][n];
					distance = new int[n][n];
					tmp_distance = 0;
					bfsDistance(new edge(i,j), map[i][j]);
					
					if(tmp_distance == 0) continue;
					
					min_distance = Math.min(min_distance, tmp_distance);
				}
			}
		}
		
		System.out.println(min_distance-1);
	}
	
	static void bfsDistance(edge e, int idx) {
		q = new LinkedList<edge>();
		q.add(e);
		
		while(!q.isEmpty()) {
			edge v = q.poll();
			
			if(map[v.x][v.y] != 0 && map[v.x][v.y] != map[e.x][e.y]) {
				tmp_distance = distance[v.x][v.y];
				return;
			}
			
			for(int i=0; i<4; i++) {
				int x = v.x + dx[i];
				int y = v.y + dy[i];
				
				if(x>=0 && x<n && y>=0 && y<n && !visited[x][y] && map[x][y] != idx) {
					q.add(new edge(x,y));
					visited[x][y] = true;
					distance[x][y] = distance[v.x][v.y] + 1;
				}
			}
		}
	}
	
	static boolean isSide(int x,int y) {
		int count = 0;
		
		for(int i=0; i<4; i++) {
			int x2 = x + dx[i];
			int y2 = y + dy[i];
			
			if(x2>=0 && x2<n && y2>=0 && y2<n && map[x2][y2] == 0) {
				count++;
				break;
			}
		}
		
		if(count > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	static void bfsIsland(edge e) {
		q = new LinkedList<edge>();
		visited[e.x][e.y] = true;
		q.add(e);
		
		while(!q.isEmpty()) {
			edge v = q.poll();
			map[v.x][v.y] = island;
			
			for(int i=0; i<4; i++) {
				int x = v.x + dx[i];
				int y = v.y + dy[i];
				
				if(x>=0 && x<n && y>=0 && y<n && !visited[x][y] && map[x][y] == 1) {
					visited[x][y] = true;
					q.add(new edge(x,y));
				}
			}
		}
	}
}

class edge{
	int x;
	int y;
	
	public edge(int x,int y) {
		this.x = x;
		this.y = y;
	}
}
