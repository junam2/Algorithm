package q1389;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int n,m, count;
	static int[][] map;
	static boolean[] visited;
	static ArrayList<Integer> arr;
	static ArrayList<Integer> result = new ArrayList<Integer>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		
		map = new int[n][n];
		visited = new boolean[n];
		
		
		for(int i=0; i<m; i++) {
			str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			
			map[a-1][b-1] = 1;
			map[b-1][a-1] = 1;
		}
				
		for(int i=0; i<n; i++) {
			int tmp = 0;
			arr = new ArrayList<Integer>();
			for(int j=0; j<n; j++) {
				if(j != i) {
					bfs(i,j);
				}
			}
			
			for(int j=0; j<arr.size(); j++) {
				tmp += arr.get(j);
			}
			
			result.add(tmp);
			
		}
		
		int min = Collections.min(result);
		
		System.out.println(result.indexOf(min)+1);
	}
	
	public static void bfs(int start, int end) {
		Arrays.fill(visited, false);
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		int[] bridge = new int[n];
		visited[start] = true;
		
		loop: while(!q.isEmpty()) {
			int v = q.poll();
			
			if(v==end) {
				arr.add(bridge[v]);
				break loop;
			}
			
			for(int i=0; i<n; i++) {
				if(map[v][i] == 1 && !visited[i]) {
					q.add(i);
					visited[i] = true;
					bridge[i] = bridge[v] + 1;
				}
			}
			
		}
	}
}