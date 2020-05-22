package q17836;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int n,m,t;
	static int[][] map;
	static boolean[][][] visited;
	static boolean getSword = false;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		visited = new boolean[2][n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs(new edge(0,0,0,false));
		
		System.out.println("Fail");
			
	}
	
	static void bfs(edge e) {
		Queue<edge> q = new LinkedList<edge>();
		visited[0][e.x][e.y] = true;
		q.add(e);
		
		while(!q.isEmpty()) {
			edge v = q.poll();
			
			if(v.time > t) {
				System.out.println("Fail");
				System.exit(0);
			}
			
			if(v.x==n-1 && v.y==m-1) {
				System.out.println(v.time);
				System.exit(0);
			}
			
			for(int i=0; i<4; i++) {
				int x = v.x + dx[i];
				int y = v.y + dy[i];
				
				if(x>=0 && x<n && y>=0 && y<m) {
					if(getSword && v.sword) {
						if(!visited[1][x][y]) {
							q.add(new edge(x,y,v.time+1,true));
							visited[1][x][y] = true;
						}
					} else {
						if(!visited[0][x][y] && map[x][y] != 1) {
							if(map[x][y] == 0) {
								q.add(new edge(x,y,v.time+1,false));
							} else {
								q.add(new edge(x,y,v.time+1,true));
								getSword = true;
							}
							visited[0][x][y] = true;
						} 
					}
					
				}
			}
		}
	}

}

class edge {
	int x;
	int y;
	int time;
	boolean sword;
	
	public edge(int x,int y, int time, boolean sword) {
		this.x = x;
		this.y = y;
		this.time = time;
		this.sword = sword;
	}
}
