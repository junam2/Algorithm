package q11403;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int n;
	static int[][] map;
	static boolean[] visited;
	static int[][] result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		visited = new boolean[n];
		result = new int[n][n];
		
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
		

		
		show(result);
	}
	
	static void bfs(int start, int end) {
		int[] count = new int[n];
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		
		loop : while(!q.isEmpty()) {
			int v = q.poll();
			
			if(v == end) {
				if(count[v] > 0) {
					result[start][end] = 1;
					break loop;
				}
			}
			
			for(int i=0; i<n; i++) {
				if(map[v][i] == 1 && !visited[i]) {
					q.add(i);
					visited[i] = true;
					count[i] = count[v] + 1;
				}
			}
		}
	}
	
	static void show(int[][] arr) {
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
