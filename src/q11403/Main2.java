package q11403;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main2 {
	static int n;
	static int[][] map;
	static boolean[] visited;
	static int[][] map2;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		map2 = new int[n][n];
		visited = new boolean[n];
		
		for(int i=0; i<n; i++) {
			String[] str = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(!visited[i]) {
					bfs(i,j);
					Arrays.fill(visited, false);
				}
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(map2[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void bfs(int start, int end) {
		int[] count = new int[n];
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		
		
		loop: while(!q.isEmpty()) {
			int v = q.poll();
			
			if(v == end && count[v] > 0) {
				map2[start][end] = 1;
				break loop;
			}
			
			for(int i=0; i<n; i++) {
				if(map[v][i] == 1 && !visited[i]) {
					count[i]++;
					visited[i] = true;
					q.add(i);
				}
			}
		}
	}


}
