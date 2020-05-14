package swea_q17144;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2 {
	static int n,m,t;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int[][] map;
	static int[][] dust_tmp_map;
	static int sum = 0;
	static ArrayList<edge> air_cleaner = new ArrayList<edge>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == -1) {
					air_cleaner.add(new edge(i,j));
				}
			}
		}
		
		for(int q=0; q<t; q++) {
			dust_tmp_map = new int[n][m];
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(map[i][j] != 0 && map[i][j] != -1) {
						dustGo(new edge(i,j));
					}
				}
			}
			
			dustResult();			
			for(int i=0; i<air_cleaner.size(); i++) {
				airClean(air_cleaner.get(i),i);
			}
			
		}
		
		dustSum();
		System.out.println(sum);
	}
	
	static void dustSum() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] != -1 && map[i][j] != 0) {
					sum += map[i][j];
				}
			}
		}
	}
	
	static void print() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static void airClean(edge e, int num) {
		int x = 0;
		int y = 0;
		
		if(num == 0) {
			x = e.x;
			y = e.y;
			
			for(int i=x-1; i>0; i--) {
				map[i][0] = map[i-1][0];
			}
			
			for(int i=0; i<m-1; i++) {
				map[0][i] = map[0][i+1];
			}
			
			for(int i=0; i<x; i++) {
				map[i][m-1] = map[i+1][m-1];
			}
			
			for(int i=m-1; i>1; i--) {
				map[x][i] = map[x][i-1];
			}
			
			map[e.x][1] = 0;
		} else {
			x = e.x;
			y = e.y;
			
			for(int i=x+1; i<n-1; i++) {
				map[i][0] = map[i+1][0];
			}
			
			for(int i=0; i<m-1; i++) {
				map[n-1][i] = map[n-1][i+1];
			}
			
			for(int i=n-1; i>x; i--) {
				map[i][m-1] = map[i-1][m-1];
			}
			
			for(int i=m-1; i>1; i--) {
				map[x][i] = map[x][i-1];
			}
			
			map[x][1] = 0;
		}
	}
	
	static void dustGo(edge e) {
		int count = 0;
		
		for(int i=0; i<4; i++) {
			int x = e.x + dx[i];
			int y = e.y + dy[i];
			
			if(x>=0 && x<n && y>=0 && y<m && map[x][y] != -1) {
				dust_tmp_map[x][y] += (int) map[e.x][e.y]/5;
				count++;
			}
		}
		
		map[e.x][e.y] -= (int)(map[e.x][e.y]/5) * count;
	}
	
	static void dustResult() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				map[i][j] += dust_tmp_map[i][j];
			}
		}
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
