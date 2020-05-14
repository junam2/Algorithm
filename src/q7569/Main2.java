package q7569;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {
	static int n,m,l;
	static int[] dx = {1,-1,0,0,0,0};
	static int[] dy = {0,0,1,-1,0,0};
	static int[] dz = {0,0,0,0,1,-1};
	static int[][][] map;
	static boolean[][][] visited;
	static int day = 0;
	static Queue<edge> q = new LinkedList<edge>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		
		map = new int[l][n][m];
		visited = new boolean[l][n][m];
		
		for(int i=0; i<l; i++) {
			for(int j=0; j<n; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0; k<m; k++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
					
					if(map[i][j][k] == 1) {
						q.add(new edge(j,k,i));
					}
				}
			}
		}
		
		
		while(true) {
			int size = q.size();
			
			if(size == 0) {
				break;
			}
			
			for(int i=0; i<size; i++) {
				edge v = q.poll();
				visited[v.z][v.x][v.y] = true;
				
				for(int j=0; j<6; j++) {
					int x = v.x + dx[j];
					int y = v.y + dy[j];
					int z = v.z + dz[j];
					
					if(x>=0 && x<n && y>=0 && y<m && z>=0 && z<l && map[z][x][y] == 0 && !visited[z][x][y]) {
						map[z][x][y] = 1;
						visited[z][x][y] = true;
						q.add(new edge(x,y,z));
					}
				}
			}
			
			day++;
		}
		
		if(check()) {
			System.out.println(day-1);
		} else {
			System.out.println(-1);
		}
	}

	static void print() {
		for(int i=0; i<l; i++) {
			for(int j=0; j<n; j++) {
				for(int k=0; k<m; k++) {
					System.out.print(map[i][j][k] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static boolean check() {
		for(int i=0; i<l; i++) {
			for(int j=0; j<n; j++) {
				for(int k=0; k<m; k++) {
					if(map[i][j][k] == 0) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
}

class edge {
	int x;
	int y;
	int z;
	
	public edge(int x,int y,int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
