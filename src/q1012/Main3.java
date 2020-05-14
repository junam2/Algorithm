package q1012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main3 {
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int t, m, n, k;
	static int count = 0;
	static int[][] map;
	static boolean[][] visited;
	static Queue<edge> q;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		t = Integer.parseInt(br.readLine());
		
		for(int q=0; q<t; q++) {
			count = 0;
			String[] str = br.readLine().split(" ");
			m = Integer.parseInt(str[0]);
			n = Integer.parseInt(str[1]);
			k = Integer.parseInt(str[2]);
			
			map = new int[m][n];
			visited = new boolean[m][n];
			
			for(int i=0; i<k; i++) {
				str = br.readLine().split(" ");
				int a = Integer.parseInt(str[0]);
				int b = Integer.parseInt(str[1]);
				
				map[a][b] = 1;
			}
			
			for(int i=0; i<m; i++) {
				for(int j=0; j<n; j++ ) {
					if(map[i][j] == 1 && !visited[i][j]) {
						bfs(new edge(i,j));
						count++;
					}
				}
			}
			
			System.out.println(count);
		}
		
	}
	
	static void bfs(edge e) {
		q = new LinkedList<edge>();
		visited[e.x][e.y] = true;
		q.add(e);
		
		while(!q.isEmpty()) {
			edge t = q.poll();
			int x = t.x;
			int y = t.y;
			
			for(int i=0; i<4; i++) {
				int x2 = x + dx[i];
				int y2 = y + dy[i];
				
				if(x2>=0 && x2<m && y2>=0 && y2<n && map[x2][y2] == 1 && !visited[x2][y2]) {
					q.add(new edge(x2,y2));
					visited[x2][y2] = true;
				}
			}
		}
	}

}

class edge {
	int x;
	int y;
	
	public edge(int x, int y) {
		this.x = x;
		this.y = y;
	}
}