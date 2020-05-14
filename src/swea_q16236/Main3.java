package swea_q16236;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main3 {
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static int n;
	static shark shark = new shark();
	static int[][] map;
	static int[][] distance;
	static int min_distance;
	static int total_time = 0;
	static boolean[][] visited;
	static ArrayList<feed> feed_arr = new ArrayList<feed>();
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		visited = new boolean[n][n];
		
		for(int i=0; i<n; i++) {
			String[] str = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				
				if(Integer.parseInt(str[j]) == 9) {
					shark.x = i;
					shark.y = j;
				} else if(Integer.parseInt(str[j]) > 0) {
					feed_arr.add(new feed(i,j,Integer.parseInt(str[j])));
				}
			}
		}
		
		loop : while(true) {
			visited = new boolean[n][n];
			distance = new int[n][n];
			
			for(int i=0; i<feed_arr.size(); i++) {
				feed tmp = feed_arr.get(i);
						
				if(tmp.size > shark.size) {
					distance[tmp.x][tmp.y] = -1;
				}
			}
			
			min_distance = Integer.MAX_VALUE;
			
			bfs(new edge(shark.x, shark.y));			

			for(int i=0; i<feed_arr.size(); i++) {
				feed tmp = feed_arr.get(i);
				
				if(tmp.size < shark.size && distance[tmp.x][tmp.y] != 0) {
					tmp.distance = distance[tmp.x][tmp.y];
					
					if(min_distance > distance[tmp.x][tmp.y]) {
						min_distance = distance[tmp.x][tmp.y];
					}
				}
			}
			
			if(min_distance == Integer.MAX_VALUE) {
				break loop;
			}
			
			for(int i=0; i<feed_arr.size(); i++) {
				feed tmp = feed_arr.get(i);
				
				if(tmp.distance == min_distance) {
					shark.eat_count++;
					total_time += min_distance;

					if(shark.eat_count == shark.size) {
						shark.size++;
						shark.eat_count = 0;
					}
					
					shark.x = tmp.x;
					shark.y = tmp.y;
					
					feed_arr.remove(i);
					break;
				}
			}
			
			if(feed_arr.size() == 0) {
				break loop;
			}
			
			
		}
		
		System.out.println(total_time);
	}
	
	static void bfs(edge s) {
		Queue<edge> q = new LinkedList<edge>();
		q.add(s);
		visited[s.x][s.y] = true;
		
		while(!q.isEmpty()) {
			edge v = q.poll();
			
			for(int i=0; i<4; i++) {
				int x = v.x + dx[i];
				int y = v.y + dy[i];
				
				if(x>=0 & x<n && y>=0 && y<n && distance[x][y] != -1 && !visited[x][y]) {
					distance[x][y] = distance[v.x][v.y] + 1;
					visited[x][y] = true;
					q.add(new edge(x,y));
				}
			}
		}
		
	}
	
	static void print(int[][] arr) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println();
	}

}

class shark {
	int size = 2;
	int eat_count = 0;
	int x;
	int y;
}

class feed {
	int x;
	int y;
	int size;
	int distance;
	
	public feed(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
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