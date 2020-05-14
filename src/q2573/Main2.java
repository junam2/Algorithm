package q2573;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int[][] map;
	static int[][] sea_map;
	static boolean[][] visited;
	static int n,m;
	static int time = 0;
	static boolean all_ice_melting = false;
	static int ice = 0;
	
	public static void main(String[] args) throws Exception{
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
			visited = new boolean[n][m];
			sea_map = new int[n][m];
			ice = 0;
						
			for(int i=0; i<n; i++ ) {
				for(int j=0; j<m; j++) {
					if(map[i][j] != 0) {
						meltingIce(new edge(i,j));
					}
				}
			}
			
			System.out.println("sea_map");
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					map[i][j] += sea_map[i][j];
					
					if(map[i][j] < 0) {
						map[i][j] = 0;
					}
				}
			}
			
			if(isAllIceMelting()) {
				all_ice_melting = true;
				break;
			}
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(map[i][j] != 0 && !visited[i][j]) {
						bfs(new edge(i,j));
						System.out.println("map");
						ice++;
					}
				}
			}
			
			time++;
			
			if(ice >= 2) {
				break;
			}
		}
		
		if(all_ice_melting) {
			System.out.println(0);
		} else {
			System.out.println(time);
		}
		
	}
	
	static void print(int[][] map) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static boolean isAllIceMelting() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] != 0) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	static void meltingIce(edge e) {
		
		for(int i=0; i<4; i++) {
			int x = e.x + dx[i];
			int y = e.y + dy[i];
			
			if(x>=0 && x<n && y>=0 && y<m && map[x][y] == 0) {
				sea_map[e.x][e.y]--;
			}
		}
	}
	
	static void bfs(edge e) {
		Queue<edge> q = new LinkedList<edge>();
		q.add(e);
		visited[e.x][e.y] = true;
		
		while(!q.isEmpty()) {
			edge v = q.poll();
			
			for(int i=0; i<4; i++) {
				int x = v.x + dx[i];
				int y = v.y + dy[i];
				
				if(x>=0 && x<n && y>=0 && y<m && map[x][y] != 0 && !visited[x][y]) {
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
