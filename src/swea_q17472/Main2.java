package swea_q17472;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int n,m;
	static int[][] map;
	static int island = 0;
	static boolean[][] visited;
	static PriorityQueue<point2> pq = new PriorityQueue<>();
	static int[] parent;
	static int result = 0;
	static boolean[] islandVisited;
	
	public static void main(String[] args) throws Exception{
		/*
		 * 1. 각 섬을 분리한다. (2~6)
		 * 2. 각 섬마다 다리를 찾는다. 
		 * 3. mst로 최소 연결 찾기
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					island++;
					bfs_seperate(new point(i,j));
				}
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] != 0 && isSide(i,j)) {
					findBridge(i,j,map[i][j]);
				}
			}
		}
		
		parent = new int[island+1];
		
		for(int i=0; i<parent.length; i++) {
			parent[i] = i;
		}
		
		int size = pq.size();
		int bridgeCount = 0;
		
		for(int i=0; i<size; i++) {
			point2 p = pq.poll();
			
			int aRoot = find(p.s);
			int bRoot = find(p.e);
			
			if(aRoot != bRoot) {
				union(aRoot, bRoot);
				result += p.v;
				bridgeCount++;
			}
		}
		
		
		if(result != 0 && bridgeCount == island-1) {
			System.out.println(result);
		} else {
			System.out.println(-1);
		}
	}
	
	static boolean allVisited() {
		for(int i=0; i<islandVisited.length; i++) {
			if(!islandVisited[i]) return false;
		}
		return true;
	}
	
	static void union(int a,int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return;
		
		parent[aRoot] = b;
	}
	
	static int find(int a) {
		if(a==parent[a]) return parent[a];
		return parent[a] = find(parent[a]);
	}
	
	static void findBridge(int x,int y, int island) {
		for(int i=0; i<4; i++) {
			int tmp_x = x;
			int tmp_y = y;
			int distance = -1;
			
			while(true) {
				tmp_x += dx[i];
				tmp_y += dy[i];
				distance++;
				
				if(tmp_x<0 || tmp_x>=n || tmp_y<0 || tmp_y>=m) {
					break;
				}
				
				if(map[tmp_x][tmp_y] == island) break;
				
				if(map[tmp_x][tmp_y] != 0 && map[tmp_x][tmp_y] != island) {
					if(distance >= 2) {
						pq.add(new point2(island, map[tmp_x][tmp_y], distance));
						break;
					} else {
						break;
					}
				}
			}
		}
	}
	
	static boolean isSide(int x,int y) {
		for(int i=0; i<4; i++) {
			int x2 = x + dx[i];
			int y2 = y + dy[i];
			
			if(x2>=0 && x2<n && y2>=0 && y2<m && map[x2][y2] == 0) {
				return true;
			}
		}
		
		return false;
	}
	
	static void bfs_seperate(point e) {
		Queue<point> q = new LinkedList<point>();
		visited[e.x][e.y] = true;
		q.add(e);
		
		while(!q.isEmpty()) {
			point v = q.poll();
			
			map[v.x][v.y] = island;
			
			for(int i=0; i<4; i++) {
				int x = v.x + dx[i];
				int y = v.y + dy[i];
				
				if(x>=0 && x<n && y>=0 && y<m && !visited[x][y] && map[x][y] == 1) {
					q.add(new point(x,y));
					visited[x][y] = true;
				}
			}
		}
	}
}

class point {
	int x;
	int y;
	
	public point(int x,int y) {
		this.x = x;
		this.y = y;
	}
}

class point2 implements Comparable<point2>{
	int s;
	int e;
	int v;
	
	public point2(int s,int e,int v) {
		this.s = s;
		this.e = e;
		this.v = v;
	}

	@Override
	public int compareTo(point2 arg0) {
		return (this.v>=arg0.v)?1:-1;
	}
}
