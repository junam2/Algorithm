package swea_q17472;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int n,m;
	static int[][] map;
	static boolean[][] visited;
	static int island = 0;
	static PriorityQueue<edge> pq = new PriorityQueue<edge>();
	static int result = 0;	
	static int[] parents;
	static int bridge_count = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		
		map = new int[n][m];
		visited = new boolean[n][m];
		
		
		for(int i=0; i<n; i++) {
			str = br.readLine().split(" ");
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					island++;
					bfs(new dot(i,j));
				}
			}
		}
		visited = new boolean[n][m];
		//show();
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] != 0) {
					makeBridge(new dot(i,j), map[i][j]);
				}
			}
		}
		parents = new int[island+1];
		
		for(int i=0; i<parents.length; i++) {
			parents[i] = i;
		}
		
		int size = pq.size();
		for(int i=0; i<size; i++) {
			edge tmp = pq.poll();
						
			int a = find(tmp.s);
			int b = find(tmp.e);
			
			if(a==b) continue;
			
			union(tmp.s, tmp.e);
			result += tmp.v;
			bridge_count++;
		}
		
		if(result == 0 || bridge_count != island-1) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}
	}

	static void bfs(dot d) {
		Queue<dot> q = new LinkedList<dot>();
		visited[d.x][d.y] = true;
		map[d.x][d.y] = island;
		q.add(d);
		
		while(!q.isEmpty()) {
			dot t = q.poll();
			int x = t.x;
			int y = t.y;
			
			for(int i=0; i<4; i++) {
				int x2 = x + dx[i];
				int y2 = y + dy[i];
				
				if(x2>=0 && x2<n && y2>=0 && y2<m && map[x2][y2] == 1 && !visited[x2][y2]) {
					q.add(new dot(x2,y2));
					map[x2][y2] = island;
					visited[x2][y2] = true;
				}
			}
			
		}
	}
	
	public static void makeBridge(dot d, int num) {
		int x2 = d.x;
		int y2 = d.y;
		int length = 0;
		
		for(int i=0; i<4; i++) {
			while(true) {
				x2 = x2 + dx[i];
				y2 = y2 + dy[i];
				
				if(x2>=0 && x2<n && y2>=0 && y2<m) {
					if(map[x2][y2] == num) {
						length = 0;
						x2 = d.x;
						y2 = d.y;
						break;
					} else if(map[x2][y2] == 0){
						length++;
					} else {
						if(length > 1) {
							pq.add(new edge(num, map[x2][y2], length));
						}
						length = 0;
						x2 = d.x;
						y2 = d.y;
						break;
					}
				} else {
					length = 0;
					x2 = d.x;
					y2 = d.y;
					break;
				}
			}
		}
	}
	
	public static int find(int a) {
		if(a == parents[a]) return a;
		parents[a] = find(parents[a]);
		return parents[a];
	}
	
	public static void union(int s,int e) {
		int aRoot = find(s);
		int bRoot = find(e);
		
		if(aRoot != bRoot) {
			parents[aRoot] = e;
		} else {
			return;
		}
	}
	
	public static void show() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m ;j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}

class dot {
	int x;
	int y;
	
	public dot(int x,int y) {
		this.x = x;
		this.y = y ;
	}
}

class edge implements Comparable<edge> {
	int s;
	int e;
	int v;
	
	public edge(int s,int e,int v) {
		super();
		this.s = s;
		this.e = e;
		this.v = v;
	}

	@Override
	public int compareTo(edge arg0) {
		return arg0.v >= this.v ? -1:1;
	}
}
