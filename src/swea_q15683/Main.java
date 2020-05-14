package swea_q15683;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0,};
	static int n,m;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<dot> arrList = new ArrayList<dot>(); 
	static int answer = Integer.MAX_VALUE;
	
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
				
				if(Integer.parseInt(str[j]) >=1 && Integer.parseInt(str[j])<=5) {
					arrList.add(new dot(i,j, Integer.parseInt(str[j])));
				}
			}
		}
		
		search(0, map);
		System.out.println(answer);
				
	}
	
	static void test() {
		int a = 1;
		test2(a);
		System.out.println(a);
	}
	
	static void test2(int b) {
		b++;
	}

	public static void search(int index, int[][] prev) {
		int[][] visited = new int[n][m];
		if(index == arrList.size()) {
			int temp = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(prev[i][j] == 0) {
						temp++;
					}
				}
			}
			
			if(temp < answer) {
				answer = temp;
			}
		} else {
			dot d = arrList.get(index);
			int x = d.x;
			int y = d.y;
			int num = d.num;
			
			switch(num) {
			case 1:
				for(int i=0; i<4; i++) {
					for(int j=0; j<n; j++) {
						visited[j] = Arrays.copyOf(prev[j], m);
					}
					detect(visited, x, y, i);
					search(index + 1, visited);
				}
				break;
			case 2:
				for(int i=0; i<2; i++) {
					for(int j=0; j<n; j++) {
						visited[j] = Arrays.copyOf(prev[j], m);
					}
					detect(visited, x, y, i);
					detect(visited, x, y, (i+2));
					search(index+1, visited);
				}
				break;
			case 3:
				for(int i=0; i<4; i++) {
					for(int j=0; j<n; j++) {
						visited[j] = Arrays.copyOf(prev[j], m);
					}
					detect(visited, x, y, i);
					detect(visited, x, y, (i+1)%4);
					search(index+1, visited);
				}
				break;
			case 4:				
				for(int i=0; i<4; i++) {
					for(int j=0; j<n; j++) {
						visited[j] = Arrays.copyOf(prev[j], m);
					}
				detect(visited, x, y, i);
				detect(visited, x, y, (i+1)%4);
				detect(visited, x, y, (i+2)%4);
				search(index+1, visited);
				}
				break;
			case 5:
				for(int j=0; j<n; j++) {
					visited[j] = Arrays.copyOf(prev[j], m);
				}
				detect(visited, x, y, 0);
				detect(visited, x, y, 1);
				detect(visited, x, y, 2);
				detect(visited, x, y, 3);
				search(index+1, visited);
				
				break;
			}
		}
	}
	
	public static void detect(int[][] visited, int x,int y, int num) {
		switch(num) {
		case 0:
			for(int i=x; i>=0; i--) {
				if(map[i][y] == 6) {
					break;
				}
				visited[i][y] = 7;
			}
			break;
		case 1:
			for(int i=y; i<m; i++) {
				if(map[x][i] == 6) {
					break;
				}
				visited[x][i] = 7;
			}
			break;
		case 2:
			for(int i=x; i<n; i++) {
				if(map[i][y] == 6) {
					break;
				}
				visited[i][y] = 7;
			}
			break;
		case 3:
			for(int i=y; i>=0; i--) {
				if(map[x][i] == 6) {
					break;
				}
				visited[x][i] = 7;
			}
			break;
		}
	}
}

class dot {
	int x;
	int y;
	int num;
	
	public dot(int x,int y,int num) {
		this.x = x;
		this.y = y;
		this.num = num;
	}
}
