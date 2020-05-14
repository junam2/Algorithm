package q1012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main2 {
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int n;
	static int m;
	static int warm;
	static int[][] map;
	static boolean[][] visited;
	static int count = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int test = Integer.parseInt(br.readLine());
		
		for(int t=0; t<test; t++) {
			String[] str = br.readLine().split(" ");
			count = 0;
			n = Integer.parseInt(str[0]);
			m = Integer.parseInt(str[1]);
			warm = Integer.parseInt(str[2]);
			map = new int[n][m];
			visited = new boolean[n][m];
			
			for(int i=0; i<warm; i++) {
				str = br.readLine().split(" ");
				int x = Integer.parseInt(str[0]);
				int y = Integer.parseInt(str[1]);
				
				map[x][y] = 1;
			}
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(!visited[i][j] && map[i][j] == 1) {
						bfs(new dot2(i,j));
						count++;
					}
				}
			}
			
			System.out.println(count);
		}
	}
	
	public static void bfs(dot2 d) {
		Queue<dot2> q = new LinkedList<dot2>();
		visited[d.x][d.y] = true;
		q.add(d);
		
		while(!q.isEmpty()) {
			dot2 t = q.poll();
			int x = t.x;
			int y = t.y;
			
			for(int i=0; i<4; i++) {
				int x2 = x + dx[i];
				int y2 = y + dy[i];
				
				if(x2>=0 && x2<n && y2>=0 && y2<m && !visited[x2][y2] && map[x2][y2] == 1) {
					visited[x2][y2] = true;
					q.add(new dot2(x2,y2));
				}
			}
		}
	}
}

class dot2 {
	int x;
	int y;
	
	public dot2(int x,int y) {
		this.x = x; this.y = y;
	}
}
