package q2178;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class Main2 {
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int x,y;
	static int[][] map;
	static int[][] distance;
	static boolean[][] visited;
	static Queue<edge> q;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String[] str = br.readLine().split(" ");
		
		x = Integer.parseInt(str[0]);
		y = Integer.parseInt(str[1]);
		
		map = new int[x][y];
		distance = new int[x][y];
		visited = new boolean[x][y];
		
		for(int i=0; i<x; i++) {
			str = br.readLine().split("");
			for(int j=0; j<y; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		bfs(new edge(0,0));
		
		System.out.println(distance[x-1][y-1]+1);
	}
	
	static void bfs(edge e) {
		q = new LinkedList<edge>();
		visited[e.x][e.y] = true;
		q.add(e);
		
		while(!q.isEmpty()) {
			edge t = q.poll();
			int x1 = t.x;
			int y1 = t.y;
			
			for(int i=0; i<4; i++) {
				int x2 = x1 + dx[i];
				int y2 = y1 + dy[i];
				
				if(x2>=0 && x2<x && y2>=0 && y2<y && map[x2][y2] == 1 && !visited[x2][y2]) {
					distance[x2][y2] = distance[x1][y1] + 1;
					visited[x2][y2] = true;
					q.add(new edge(x2,y2));
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
