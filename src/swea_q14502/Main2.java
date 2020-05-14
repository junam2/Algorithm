package swea_q14502;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main2 {
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int n,m;
	static int[][] map;
	static int[][] tmp_map;
	static boolean[][] visited;
	static ArrayList<edge> blank = new ArrayList<edge>();
	static int[] comb;
	static int safe_zone = 0;
	static int max = Integer.MIN_VALUE;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		
		map = new int[n][m];
		tmp_map = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i=0; i<n; i++) {
			str = br.readLine().split(" ");
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				if(Integer.parseInt(str[j]) == 0) {
					blank.add(new edge(i,j));
				}
			}
		}
		
		comb = new int[blank.size()];
		Arrays.fill(comb, 1);
		for(int i=0; i<3; i++) {
			comb[i] = 0;
		}
		
		do {
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					tmp_map[i][j] = map[i][j];
				}
			}
			
			
			safe_zone = 0;
			visited = new boolean[n][m];
			
			for(int i=0; i<blank.size(); i++) {
				if(comb[i] == 0) {
					tmp_map[blank.get(i).x][blank.get(i).y] = 1;
				}
			}
			
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(tmp_map[i][j] == 2 && !visited[i][j]) {
						bfs(new edge(i,j));

					}
				}
			}
			
			safe_zone = count_safe_zone();
			
			if(max < safe_zone) {
				max = safe_zone;
			}
			
		} while(next_permutation(comb));
		
		
		System.out.println(max);
		
	}
	
	static boolean next_permutation(int[] arr) {
		int i = arr.length-1;
		
		while(i>0 && arr[i-1]>=arr[i]) {
			i--;
		}
		
		if(i<=0) {
			return false;
		}
		
		int j = arr.length-1;
		
		while(arr[i-1] >= arr[j]) {
			j--;
		}
		
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		
		j = arr.length-1;
		
		while(i<j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}
		
		return true;
	}
	
	static void print(int[][] arr) {
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void bfs(edge e) {
		Queue<edge> q = new LinkedList<edge>();
		q.add(e);
		visited[e.x][e.y] = true;
		
		while(!q.isEmpty()) {
			edge t = q.poll();
			
			for(int i=0; i<4; i++) {
				int x = t.x + dx[i];
				int y = t.y + dy[i];
				
				if(x>=0 && x<n && y>=0 && y<m && tmp_map[x][y] != 1 && !visited[x][y]) {
					tmp_map[x][y] = 2;
					visited[x][y] = true;
					q.add(new edge(x,y));
				}
			}
		}
	}
	
	static int count_safe_zone() {
		int count = 0;
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(tmp_map[i][j] == 0) {
					count++;
				}
			}
		}
		
		return count;
	}
}

class edge {
	int x;
	int y;
	
	public edge(int x, int y) {
		this.x = x;
		this.y = y;
	}
}