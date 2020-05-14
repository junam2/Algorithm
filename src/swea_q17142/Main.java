package swea_q17142;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int n,number;
	static int[][] map;
	static boolean[][] visited;
	static Queue<dot> virus_constant = new LinkedList<dot>();
	static Queue<dot> virus = new LinkedList<dot>();
	static Queue<dot> virus_wake;
	static ArrayList<dot> arr;
	static int day;
	static int min = Integer.MAX_VALUE;
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		number = Integer.parseInt(str[1]);
		
		map = new int[n][n];
		visited = new boolean[n][n];
		
		for(int i=0; i<n; i++) {
			str = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				
				if(map[i][j] == 2) {
					virus.add(new dot(i,j));
					virus_constant.add(new dot(i,j));
				}
			}
		}
		int size = virus_constant.size();
		int[] comb = new int[size];
		Arrays.fill(comb, 1);
		
		for(int i=0; i<number; i++) {
			comb[i] = 0;
		}
		
		do {
			
			int[][] map_clone = new int[n][n];
			
			for(int i=0; i<n; i++) {
				map_clone[i] = Arrays.copyOf(map[i], n);
			}
			
			if(check(map_clone)) {
				min = 0;
				break;
			} else {
				virus_wake = new LinkedList<dot>();
				arr = new ArrayList<dot>();
				arr.addAll(virus_constant);
				day = 0;
				flag = true;
				
				for(int i=0; i<comb.length; i++) {
					if(comb[i] == 0) {
						dot d = arr.get(i);
						map_clone[d.x][d.y] = 3;
						virus_wake.add(d);
					}
				}
				
				while(true) {
					
					bfs(map_clone);
					if(!flag) {
						break;
					}
					day++;
				}
				
				if(day < min && check(map_clone)) {
					min = day;
				}
			}
			
		} while(next_permutation(comb));
		
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
		
	}
	
	public static boolean next_permutation(int[] arr) {
		int i = arr.length -1;
		
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
	
	public static boolean next_permutation2(int[] arr) {
		int i = arr.length-1;
		
		while(i>0 && arr[i-1] >= arr[i]) {
			i--;
		}
		
		if(i<=0) {
			return false;
		}
		
		int j = arr.length -1;
		while(arr[i-1] >= arr[j]) {
			j--;
		}
		
		int temp = arr[j];
		arr[j] = arr[i-1];
		arr[i-1] = temp;
		
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
	
	public static boolean next_permutation3(int[] arr) {
		int i = arr.length-1;
		
		while(i>0 && arr[i-1] >= arr[i]) {
			i--;
		}
		
		if(i<=0) return false;
		
		int j = arr.length -1;
		while(arr[i-1]>=arr[j]) {
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
		
		return false;
	}
	
	public static void bfs(int[][] map) {
		int size = virus_wake.size();
		
		for(int i=0; i<size; i++) {
			dot q = virus_wake.poll();
			int x = q.x;
			int y = q.y;
			
			for(int j=0; j<4; j++) {
				int x1 = x + dx[j];
				int y1 = y + dy[j];
				
				if(x1>=0 && x1<n && y1>=0 && y1<n && (map[x1][y1] == 0 || map[x1][y1] == 2)) {
						map[x1][y1] = 3;
						virus_wake.add(new dot(x1, y1));
				}
			}
		}
		
		if(virus_wake.size() == 0) {
			flag = false;
			return;
		}
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

class dot {
	int x;
	int y;
	
	public dot(int x,int y) {
		this.x = x;
		this.y = y;
	}
}