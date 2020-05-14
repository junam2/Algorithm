package swea_q17142;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main3 {
	static int n,m;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int[] comb;
	static int[][] map;
	static int[][] tmp_map;
	static boolean[][] visited;
	static int time = 0;
	static int min_time = Integer.MAX_VALUE;
	static ArrayList<edge> virus_arr = new ArrayList<edge>();
	static Queue<edge> q;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		
		map = new int[n][n];
		tmp_map = new int[n][n];
		visited = new boolean[n][n];
		
		for(int i=0; i<n; i++) {
			str = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				if(Integer.parseInt(str[j]) == 2) {
					virus_arr.add(new edge(i,j));
				}
			}
		}
		
		comb = new int[virus_arr.size()];
		Arrays.fill(comb, 1);
		
		for(int i=0; i<m; i++) {
			comb[i] = 0;
		}
		
		do {
			for(int i=0; i<n; i++) {
				tmp_map[i] = Arrays.copyOf(map[i], n);
			}
			time = 0;
			q = new LinkedList<edge>();
			
			for(int i=0; i<comb.length; i++) {
				if(comb[i] == 0) {
					tmp_map[virus_arr.get(i).x][virus_arr.get(i).y] = 3;
					q.add(virus_arr.get(i));
				}
			}
			
			while(true) {
				
				if(q.isEmpty() || check(tmp_map)) {
					break;
				}
				
				bfs();
				time++;
				
				if(time > min_time) {
					break;
				}
			}
			
			if(check(tmp_map) && min_time > time) {
				min_time = time;
			}
			
		} while(next_permutation(comb));
		
		if(min_time == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min_time);
		}
	}
	
	static void bfs() {
		int size = q.size();
		
		for(int v=0; v<size; v++) {
			edge t = q.poll();
			
			for(int i=0; i<4; i++) {
				int x = t.x + dx[i];
				int y = t.y + dy[i];
				
				if(x>=0 && x<n && y>=0 && y<n && (tmp_map[x][y] == 0 || tmp_map[x][y] == 2 )) {
					tmp_map[x][y] = 3;
					q.add(new edge(x,y));
				}
			}
		}
	}
	
	static boolean check(int[][] arr) {
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				if(arr[i][j] == 0) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static boolean next_permutation(int[] arr) {
		int i = arr.length - 1;
		
		while(i>0 && arr[i-1] >= arr[i]) {
			i--;
		}
		
		if(i<=0) {
			return false;
		}
		
		int j = arr.length - 1;
		
		while(arr[i-1] >= arr[j]) {
			j--;
		}
		
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		
		j = arr.length-1;
		
		while(i < j) {
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
