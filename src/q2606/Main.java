package q2606;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int n;
	static int m;
	static int[][] map;
	static boolean[] visited;
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		visited = new boolean[n];
		
		for(int i=0; i<m; i++) {
			String[] str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			
			map[a-1][b-1] = 1;
			map[b-1][a-1] = 1;
		}
		
		bfs(0);
		
		System.out.println(result);
	}
	
	static void bfs(int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int v = q.poll();
			for(int i=0; i<n; i++) {
				if(map[v][i] == 1 && !visited[i]) {
					q.add(i);
					visited[i] = true;
					result++;
				}
			}
		}
	}
}
