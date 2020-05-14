package q2644;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int n,m,start,end;
	static int result = 0;
	static int[][] map;
	static boolean[] visited;
	static int[] far;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		start = sc.nextInt();
		end = sc.nextInt();
		m = sc.nextInt();
		
		map = new int[n][n];
		far = new int[n];
		visited = new boolean[n];
		
		for(int i=0; i<m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			map[a-1][b-1] = 1;
			map[b-1][a-1] = 1;
		}
		
		bfs(start-1, end-1);
		
		if(far[end-1] == 0) {
			System.out.println(-1);
		} else {
			System.out.println(far[end-1]);
		}
	}
	
	static void bfs(int start, int end) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		visited[start] = true;
		
		loop: while(!q.isEmpty()) {
			int v = q.poll();
			
			if(v == end) {
				break loop;
			}
			
			for(int i=0; i<n; i++) {
				if(map[v][i] == 1 && !visited[i]) {
					q.add(i);
					visited[i] = true;
					far[i] = far[v] + 1;
				}
			}
		}
	}

}
