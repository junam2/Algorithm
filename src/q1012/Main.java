package q1012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int h,w, kk,count;
	static int[][] map;
	static boolean[][] visited;
	static Queue<dot> q = new LinkedList<dot>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int k = Integer.parseInt(br.readLine());
		for(int q=0; q<k; q++) {
			String[] a = br.readLine().split(" ");
			h = Integer.parseInt(a[1]);
			w = Integer.parseInt(a[0]);
			kk = Integer.parseInt(a[2]);
			count = 0;

			map = new int[h][w];
			visited = new boolean[h][w];
			
			for(int i=0; i<kk; i++) {
				String[] str = br.readLine().split(" ");
				map[Integer.parseInt(str[1])][Integer.parseInt(str[0])] = 1;
			}
			


			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					if(!visited[i][j] && map[i][j]==1) {
						bfs(i,j);
						count++;
					}
				}
			}

			System.out.println(count);
		}
		
	}
	
	static void bfs(int a,int b) {
		visited[a][b] = true;
		q.add(new dot(a,b));
		
		while(!q.isEmpty()) {
			dot v = q.poll();
			
			for(int i=0; i<dx.length; i++) {
				int x2 = v.x + dx[i];
				int y2 = v.y + dy[i];
				
				if(x2>=0 && x2<h && y2>=0 && y2<w &&
					map[x2][y2]==1	&& !visited[x2][y2]) {
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
