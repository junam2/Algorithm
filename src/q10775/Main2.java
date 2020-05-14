package q10775;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main2 {
	/*
	 * union-find를 사용하는 방법
	 * k번째와 k-1을 merge하여 0이 되게 된다면 
	 * 그 전까지의 모든게 도킹되었다고 생각한다.
	 */
	
	static int g,p;
	static int[] parent;
	static int res = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		g = Integer.parseInt(br.readLine());
		p = Integer.parseInt(br.readLine());
		
		parent = new int[g+1];
		
		for(int i=1; i<g+1; i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<p; i++) {
			int gi = Integer.parseInt(br.readLine());
			
			int root = find(gi);
			
			if(root == 0) {
				break;
			} else {
				union(root, root-1);
				res++;
			}
		}
		
		System.out.println(res);
	}
	
	static void union(int a,int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		parent[aRoot] = bRoot;
	}
	
	static int find(int a) {
		if(a == parent[a]) return a;
		return parent[a] = find(parent[a]);
	}
}
