package swea_q16236;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int size = 2;
	static int n;
	static int[][] map;
	static int[][] distance;
	static boolean[][] visited;
	static int time = 0;
	static dot shark;
	static dot pray;
	static int eat = 0;
	static PriorityQueue<fish> pq;
	static int eat_count = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		visited = new boolean[n][n];
		
		for(int i=0; i<n; i++) {
			String[] str = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				
				if(map[i][j] == 9) {
					shark = new dot(i,j);
				}
			}
		}
		
		while(true) {
			pq = new PriorityQueue<fish>();
			visited = new boolean[n][n];
			distance = new int[n][n];
			bfs(shark);
			
			if(pq.size()==0) {
				break;
			}
			
			fish eat = pq.poll();
			time += eat.distance;
			map[shark.x][shark.y] = 0;
			map[eat.x][eat.y] = 9;
			shark = new dot(eat.x, eat.y);
			eat_count++;
			
			if(eat_count == size) {
				size++;
				eat_count = 0;
			}
		}
		
		System.out.println(time);
		
	}
	
	public static void print() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void bfs(dot d) {
		Queue<dot> q = new LinkedList<dot>();
		int x = d.x;
		int y = d.y;
		visited[x][y] = true;
		q.add(d);
		
		while(!q.isEmpty()) {
			dot t = q.poll();
			
			if(map[t.x][t.y] != 9 && map[t.x][t.y] != 0) {
				if(map[t.x][t.y] < size) {
					pq.add(new fish(t.x, t.y, distance[t.x][t.y], map[t.x][t.y]));
				}
			}
			
			for(int i=0; i<4; i++) {
				int x2 = t.x + dx[i];
				int y2 = t.y + dy[i];
				
				if(x2>=0 && x2<n && y2>=0 && y2<n && map[x2][y2] <= size && !visited[x2][y2]) {
					q.add(new dot(x2,y2));
					visited[x2][y2] = true;
					distance[x2][y2] = distance[t.x][t.y] + 1;
				}
			}
		}
		
	}

}

class dt {
	int x;
	int y;
	
	public dt(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class fish implements Comparable<fish> {
	int x;
	int y;
	int distance;
	int size;
	
	public fish(int x, int y, int distance, int size) {
		this.x = x;
		this.y = y;
		this.distance = distance;
		this.size = size;
	}

	@Override
	public int compareTo(fish arg0) {
		// TODO Auto-generated method stub
		int compare1= this.distance - arg0.distance;;
		
		if(compare1 == 0) {
			int compare2 = this.x - arg0.x;
			
			if(compare2 == 0) {
				return this.y - arg0.y;
			} else {
				return compare2;
			}
		}
		
		return compare1;
		
	}
}