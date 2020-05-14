package q15649;

import java.util.Scanner;

public class Main {
	static int n;
	static int m;
	static boolean[] visited;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		arr = new int[m];
		visited = new boolean[n+1];
		
		backTracking(0);
		System.out.println(sb);
	}
	
	static void backTracking(int k) {
		if(k == m) {
			for(int i=0; i<arr.length; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		} else {		
			for(int i=1; i<=n; i++) {
				if(!visited[i]) {
					visited[i] = true;
					arr[k] = i;
					backTracking(k+1);
					visited[i] = false;
				}
			}
		}
		
	}

}
