package q2667;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int n;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int count_danzi = 0;
	static Queue<dot> q = new LinkedList<dot>();
	static ArrayList<Integer> arr = new ArrayList<Integer>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		visited = new boolean[n][n];
		
		for(int i=0; i<n; i++) {
			String[] str = br.readLine().split("");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					bfs(new dot(i,j));
					count_danzi++;
				}
			}
		}
		Collections.sort(arr);
		System.out.println(count_danzi);
		for(int i=0; i<arr.size(); i++) {
			System.out.println(arr.get(i));
		}
	}
	
	static void bfs(dot d) {
		int villege_count = 0;
		visited[d.x][d.y] = true;
		q.add(d);
		
		while(!q.isEmpty()) {
			villege_count++;
			dot t = q.remove();
			int x1 = t.x;
			int y1 = t.y;
			
			for(int i=0; i<4; i++) {
				int x2 = x1 + dx[i];
				int y2 = y1 + dy[i];
				
				if(x2>=0 && x2<n && y2>=0 && y2<n && 
						map[x2][y2] == 1 && !visited[x2][y2]) {
					visited[x2][y2] = true;
					q.add(new dot(x2,y2));
				}
			}
		}
		
		arr.add(villege_count);
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
