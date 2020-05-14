package q1697;

import java.util.*;

public class Main {
	static int[] arr;
	static boolean[] visited;
	static Queue<Integer> q = new LinkedList<Integer>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		arr = new int[100001];
		visited = new boolean[100001];
		
		for(int i=0; i<arr.length; i++) {
			arr[i] = 100002;
		}
		
		arr[n] = 0;
		
		dfs(n,k);	
		
		System.out.println(arr[k]);
		
	}
	
	static void dfs(int n, int k) {
		q.add(n);
		
		while(!q.isEmpty()) {
			int v = q.poll();
			
			
			if(v==k) {
				break;
			}
			
			if(!visited[v]) {
				if(v-1 >= 0) {
					arr[v-1] = Math.min(arr[v-1], arr[v]+1);
					q.add(v-1);
				}
				if(v+1 < arr.length) {
					arr[v+1] = Math.min(arr[v+1], arr[v]+1);
					q.add(v+1);
				}
				if(2*v < arr.length) {
					arr[2*v] = Math.min(arr[2*v], arr[v]+1);
					q.add(2*v);
				}
			}
			
			visited[v] = true;
			
		}

	}

}
