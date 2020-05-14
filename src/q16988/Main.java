package q16988;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int n,m;
	static int[][] map;
	static boolean[][] visited;
	static boolean flag;
	static int res = 0;
	static int count = 0;
	static int maxCount = Integer.MIN_VALUE;
	static ArrayList<edge> zeroArr = new ArrayList<edge>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++ ) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 0) {
					zeroArr.add(new edge(i,j));
				}
			}
		}
		
		int[] comb = new int[zeroArr.size()];
		Arrays.fill(comb, 1);
		comb[0] = 0; comb[1] = 0;

		do {
			ArrayList<edge> stone = new ArrayList<edge>();
			visited = new boolean[n][m];
			res = 0;

			for(int i=0; i<comb.length; i++) {
				if(comb[i] == 0) {
					edge tmp = zeroArr.get(i);
					map[tmp.x][tmp.y] = 1;
					stone.add(tmp);
				}
			}
			
			//¹ÙµÏ ½ÃÀÛ
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(map[i][j] == 2 && !visited[i][j]) {
						flag = true;
						count = 0;
						bfs(new edge(i,j));
						
						if(flag) {
							res += count;
						}
					}
				}
			}
			
			if(maxCount < res) {
				maxCount = res;
			}
			
			//¹ÙµÏ Á¾·á
			
			for(int i=0; i<stone.size(); i++) {
				map[stone.get(i).x][stone.get(i).y] = 0;
			}
			
		} while(next_permutation(comb));
		
		System.out.println(maxCount);
	}
	
	static void bfs(edge e) {
		Queue<edge> q = new LinkedList<edge>();
		q.add(e);
		visited[e.x][e.y] = true;
		
		while(!q.isEmpty()) {
			edge v = q.poll();
			count++;
			
			for(int i=0; i<4; i++) {
				int x = v.x + dx[i];
				int y = v.y + dy[i];
				
				if(x>=0 && x<n && y>=0 && y<m) {
					if(map[x][y] == 0) {
						flag = false;
					} else if(map[x][y] == 2 && !visited[x][y]) {
						visited[x][y] = true;
						q.add(new edge(x,y));
					}
				}
			}
		}
	}
	
	static boolean next_permutation(int[] arr) {
		int i = arr.length - 1;
		
		while(i>0 && arr[i-1] >= arr[i]) {
			i--;
		}
		
		if(i<=0) return false;
		
		
		int j = arr.length - 1;
		
		while(arr[i-1] >= arr[j]) {
			j--;
		}
		
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		
		j = arr.length - 1;
		
		while(i<j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}
		return true;
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
