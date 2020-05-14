package q1976;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int n,m;
	static int[][] map;
	static int[] parent;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		
		parent = new int[n+1];
		
		for(int i=1; i<n+1; i++) {
			parent[i] = i;
		}
		
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) {
					union(i,j);
				}
			}
		}
		
		ArrayList<Integer> route = new ArrayList<Integer>();
		st = new StringTokenizer(br.readLine());
		
		while(st.hasMoreTokens()) {
			route.add(Integer.parseInt(st.nextToken()));
		}
		
		int root = -1;
		boolean flag = true;
		
		for(int i=0; i<route.size(); i++) {
			
			if(i==0) {
				root = find(route.get(i));
			} else {
				if(root != find(route.get(i))) {
					flag = false;
					break;
				}
			}
		}
		
		if(flag) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) {
			return;
		} else {
			parent[aRoot] = find(bRoot);
		}
	}
	
	static int find(int a) {
		if(a == parent[a]) return a;
		return parent[a] = find(parent[a]);
	}
}
