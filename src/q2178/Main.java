package q2178;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class dot {
	int i;
	int j;
	int flag;
	
	public dot(int i,int j) {
		this.i = i;
		this.j = j;
	}
}

public class Main {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int a;
	static int b;
	static dot[][] map;
	static boolean[][] visited;
	static int distance;
	static Queue<dot> q = new LinkedList<dot>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] t = br.readLine().split(" ");
		
		a = Integer.parseInt(t[0]);
		b = Integer.parseInt(t[1]);
		
		map = new dot[a][b];
		visited = new boolean[a][b];
		
		for(int i=0; i<a; i++) {
			String[] tmp = br.readLine().split("");
			for(int j=0; j<b; j++) {
				if(Integer.parseInt(tmp[j])==1) {
					map[i][j] = new dot(i,j);
					map[i][j].flag = 1;
				} else {
					map[i][j] = new dot(i,j);
					map[i][j].flag = 0;
				}
			}
		}
		
		dfs(0,0);

		System.out.println(map[a-1][b-1].flag);
	}
	
	static void dfs(int i,int j) {
		
		visited[i][j] = true;
		q.add(map[i][j]);
		
		while(!q.isEmpty()) {
			dot d = q.poll();
			int x = d.i;
			int y = d.j;
			
			for(int k=0; k<4; k++) {
				int x2 = x + dx[k];
				int y2 = y + dy[k];
								
				if(x2>=0 && x2<a && y2>=0 && y2<b
						&& map[x2][y2].flag == 1 && !visited[x2][y2]) {
					q.add(map[x2][y2]);
					visited[x2][y2] = true;
					map[x2][y2].flag = map[x][y].flag + 1;
				}
			}
		}
	}
}
