package q11724;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int n;
	static int m;
	static int[][] map;
	static boolean[] visited;
	static int result = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		map = new int[n][n];
		visited = new boolean[n];
		
		for(int i=0; i<m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			map[a-1][b-1] = 1;
			map[b-1][a-1] = 1;
		}
		
		for(int i=0; i<n; i++) {
			if(!visited[i]) {
				bfs(i);
				result++;
			}
		}
		
		System.out.println(result);
		
	}
	
	public static void bfs(int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int v = q.poll();
			
			for(int i=0; i<n; i++) {
				if(map[v][i] == 1 && !visited[i]) {
					q.add(i);
					visited[i] = true;
				}
			}
		}
	}
}
