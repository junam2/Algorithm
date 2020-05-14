package swea_q17142;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {
	//n:연구소 크기, m: 바이러스 개수
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int n,m;
	static int[][] map;
	static int time = 0;
	static ArrayList<dot> virus_first = new ArrayList<dot>();
	static int[] comb;
	static Queue<dot> virus_wake = new LinkedList<dot>();
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		
		map = new int[n][n];
		
		for(int i=0; i<n; i++) {
			str = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				
				if(map[i][j] == 2) {
					virus_first.add(new dot(i,j));
				}
			}
		}
		
		comb = new int[virus_first.size()];
		
		for(int i=0; i<comb.length; i++) {
			if(i<m) {
				comb[i] = 0;
			} else {
				comb[i] = 1;
			}
		}
		
		do {
			int[][] map_copy = new int[n][n];
			virus_wake = new LinkedList<dot>();
			
			for(int i=0; i<n; i++) {
				map_copy[i] = Arrays.copyOf(map[i], n);
			}
			
			for(int i=0; i<comb.length; i++) {
				if(comb[i] == 0) {
					virus_wake.add(virus_first.get(i));
					map_copy[virus_first.get(i).x][virus_first.get(i).y] = 3;
				}
			}
			
			while(true) {				
				if(virus_wake.size() == 0 || check(map_copy)) {
					break;
				}
				
				bfs(map_copy);
				time++;
			}
			
			if(min > time && check(map_copy)) {
				min = time;
			}
			time = 0;
			
		} while(next_permutation(comb));
		
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {		
			System.out.println(min);
		}
	}
	
	
	public static void bfs(int[][] map_copy) {
		int size = virus_wake.size();
		
		for(int i=0; i<size; i++) {
			dot d = virus_wake.poll();
			int x = d.x;
			int y = d.y;
			
			for(int j=0; j<4; j++) {
				int x2 = x + dx[j];
				int y2 = y + dy[j];
				
				if(x2>=0 && x2<n && y2>=0 && y2<n && (map_copy[x2][y2] == 0 || map_copy[x2][y2] == 2)) {
					map_copy[x2][y2] = 3;
					virus_wake.add(new dot(x2,y2));
				}
 			}
		}	
	}
	
	public static boolean next_permutation(int[] arr) {
		int i = arr.length-1;
		
		while(i>0 && arr[i-1] >= arr[i]) {
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
		
		while(i < j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}
		return true;
		
	}
	
	public static boolean check(int[][] map) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] == 0) {
					return false;
				}
			}
		}
		
		return true;
	}
}

class dd {
	int x;
	int y;
	
	public dd(int x,int y) {
		this.x = x;
		this.y = y;
	}
}