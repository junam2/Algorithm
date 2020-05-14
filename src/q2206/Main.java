package q2206;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int n,m;
	static int min_distance = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][][] visited;
	static Queue<point> q = new LinkedList<point>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		
		map = new int[n][m];
		visited = new boolean[2][n][m];
		
		for(int i=0; i<n; i++) {
			char[] ch = br.readLine().toCharArray();
			for(int j=0; j<m; j++) {
				map[i][j] = (int) (ch[j]-'0');
			}
		}
		
		bfs();
		
		System.out.println(-1);
	
	}
	
	static void bfs() {
		q.add(new point(0,0,0,1));
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			point v = q.poll();
			
			if(v.x == n-1 && v.y == m-1) {
				System.out.println(v.distance);
				System.exit(0);
			}
			
			for(int i=0; i<4; i++) {
				int x = v.x + dx[i];
				int y = v.y + dy[i];
				
				if(x>=0 && x<n && y>=0 && y<m) {
					if(v.is_break == 0 && !visited[0][x][y]) {
						if(map[x][y] == 1) {
							q.add(new point(x,y,1,v.distance+1));
							visited[1][x][y] = true;
						} else {
							q.add(new point(x,y,0,v.distance+1));
							visited[0][x][y] = true;
						}
						
					} else {
						if(map[x][y] == 0 && !visited[1][x][y]) {
							q.add(new point(x,y,1,v.distance+1));
							visited[1][x][y] = true;
						}
					}
				}
			}
		}
	}

}

class point {
	int x;
	int y;
	int is_break;
	int distance;
	
	public point(int x,int y, int is_break, int distance) {
		this.x = x;
		this.y = y;
		this.is_break = is_break;
		this.distance = distance;
	}
}
