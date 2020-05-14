package q2468;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int n;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int max = -1;
	static int area = 0;
	static Queue<dot> q = new LinkedList<dot>();
	static ArrayList<Integer> arr = new ArrayList<Integer>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		visited = new boolean[n][n];
		
		for(int i=0; i<n; i++) {
			String[] str = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				if(max < map[i][j]) {
					max = map[i][j];
				}
			}
		}
		
		for(int k=0; k<=max; k++) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(!visited[i][j] && map[i][j] > k) {
						bfs(new dot(i,j), k);
						area++;
					}
				}
			}
			visited = new boolean[n][n];
			arr.add(area);
			area = 0;
		}
		
		System.out.println(Collections.max(arr));
	}
	
	static void bfs(dot d, int k) {
		visited[d.x][d.y] = true;
		q.add(d);
		
		while(!q.isEmpty()) {
			dot t = q.remove();
			int x1 = t.x;
			int y1 = t.y;
			
			for(int i=0; i<4; i++) {
				int x2 = x1 + dx[i];
				int y2 = y1 + dy[i];
				
				if(x2>=0 && x2<n && y2>=0 && y2<n && 
						map[x2][y2] > k && !visited[x2][y2]) {
					visited[x2][y2] = true;
					q.add(new dot(x2,y2));
				}
			}
		}
	}
}

class dot {
	int x;
	int y;
	
	public dot(int x,int y) {
		this.x = x;
		this.y = y;
	}
}

