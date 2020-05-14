package q2146;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class Main {
	static int n;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] map;
	static boolean[][] visited;
	static int area = +1;
	static int min = Integer.MAX_VALUE;
	static Queue<dot> q = new LinkedList<dot>();


	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		visited = new boolean[n][n];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//다른 섬 영역 구분하기 위한 bfs
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
				bfsSeperate(new dot(i,j, map[i][j]));
				area++;
				}
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] == 0 || !isEdge(i, j))
					continue;
				visited = new boolean[n][n];
				
				int cnt = 0;
				int idx = map[i][j];
				
				visited[i][j] = true;
				q.add(new dot(i,j,map[i][j]));
				
				loop: while(!q.isEmpty()) {
					int size = q.size();
					
					for(int s=0; s<size; s++) {
						dot t = q.remove();
						
						if(map[t.x][t.y] != idx && map[t.x][t.y] != 0) {
							q.clear();
							break loop;
						}
						
						for(int k=0; k<4; k++) {
							int x1 = t.x + dx[k];
							int y1 = t.y + dy[k];
							
							if(x1>=0 && x1<n && y1>=0 && y1<n
									&& !visited[x1][y1] && map[x1][y1] != idx) {
								q.add(new dot(x1,y1,map[x1][y1]));
								visited[x1][y1] = true;
							}
						}
					}
					cnt++;
				}		
				min = Math.min(min, cnt-1);
			}
		}
		
		System.out.println(min);	
		
	}
	
	static void show(int[][] arr) {
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	
	static void bfsSeperate(dot d) {
		Queue<dot> q1 = new LinkedList<dot>();
		map[d.x][d.y] = area;
		visited[d.x][d.y] = true;
		q1.add(d);
		
		while(!q1.isEmpty()) {
			dot t = q1.remove();
			int x1 = t.x;
			int y1 = t.y;
			
			for(int i=0; i<4; i++) {
				int x2 = x1 + dx[i];
				int y2 = y1 + dy[i];
				
				if(x2>=0 && x2<n && y2>=0 && y2<n && 
						map[x2][y2] == 1 && !visited[x2][y2]) {
					q1.add(new dot(x2,y2, map[x2][y2]));
					visited[x2][y2] = true;
					map[x2][y2] = area;
				}
			}
		}
	}
	
	static boolean isEdge(int a, int b) {
		for(int i=0; i<4; i++) {
			int x1 = a + dx[i];
			int y1 = b + dy[i];
			
			if(x1>=0 && x1<n && y1>=0 && y1<n && map[x1][y1] == 0) {
				return true;
			}
		}
		return false;
	}
}


class dot {
	int x;
	int y;
	int area_info;
	
	public dot(int x,int y, int area_info) {
		this.x = x;
		this.y = y;
		this.area_info = area_info;
	}
}