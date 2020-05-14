package swea_q16234;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;
	static int[][] open;
	static int count = 0;
	static int sum = 0;
	static int flag_count = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		L = Integer.parseInt(str[1]);
		R = Integer.parseInt(str[2]);
		
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
			map[i][j] = Integer.parseInt(str[j]);	
			}
		}
		
		loop: while(true) {
			visited = new boolean[N][N];
			open = new int[N][N];
			flag_count = 0;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					castleOpen(new dot(i,j));
				}
			}
			
			if(flag_count == 0) {
				break loop;
			} else {
				count++;
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(open[i][j] == 1 && !visited[i][j]) {
						bfs(new dot(i,j));
					}
				}
			}
			
			System.out.println(count);
			for(int i=0; i<N; i++) {
				for(int j=0; j<N ;j++) {
					System.out.print(open[i][j] + " ");
				}
				System.out.println();
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N ;j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
			
		}
		
		System.out.println(count);
	}
	
	public static void castleOpen(dot d) {
		int x = d.x;
		int y = d.y;
		
		
		for(int i=0; i<4; i++) {
			int x1 = d.x + dx[i];
			int y1 = d.y + dy[i];
			
			if(x1>=0 && x1<N && y1>=0 && y1<N) {
				if(Math.abs(map[x][y] - map[x1][y1])>=L && Math.abs(map[x][y] - map[x1][y1])<=R) {
					open[x][y] = 1;
					open[x1][y1] = 1;
					flag_count++;
				}
			}
		}
	}
	
	public static void bfs(dot d) {
		Queue<dot> q = new LinkedList<dot>();
		ArrayList<dot> save = new ArrayList<dot>();
		int tmp_count = 0;
		sum = 0;
		visited[d.x][d.y] = true;
		q.add(d);
		
		while(!q.isEmpty()) {
			dot t = q.poll();
			save.add(t);
			int x = t.x;
			int y = t.y;
			sum += map[x][y];
			tmp_count++;
			
			for(int i=0; i<4; i++) {
				int x1 = x + dx[i];
				int y1 = y + dy[i];
				
				if(x1>=0 && x1<N && y1>=0 && y1<N && Math.abs(map[x][y] - map[x1][y1])>=L && Math.abs(map[x][y] - map[x1][y1])<=R && !visited[x1][y1]) {
					q.add(new dot(x1, y1));
					visited[x1][y1] = true;
				}
			}
		}
		
		int divide = sum/tmp_count;
		
		for(int i=0; i<save.size(); i++) {
			dot t = save.get(i);
			map[t.x][t.y] = divide;
		}
		
	}
}

class dot {
	int x;
	int y;
	
	public dot(int x,int y) {
		this.x= x;
		this.y= y;
	}
}
