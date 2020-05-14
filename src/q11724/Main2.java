package q11724;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main2 {
	static int n,m;
	static int[][] map;
	static boolean[] visited;
	static int count = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		
		map = new int[n][n];
		visited = new boolean[n];
		
		for(int i=0; i<m; i++) {
			str = br.readLine().split(" ");
			map[Integer.parseInt(str[0])-1][Integer.parseInt(str[1])-1] = 1;
			map[Integer.parseInt(str[1])-1][Integer.parseInt(str[0])-1] = 1;

		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(!visited[i]) {
					bfs(i,j);
					count++;
				}
			}
		}
		
		System.out.println(count);
	}

	static void bfs(int start, int end) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int v = q.poll();
			
			for(int i=0; i<n; i++) {
				if(map[v][i] == 1 && !visited[i]) {
					visited[i] = true;
					q.add(i);
				}
			}
		}
	}
}
