package q1922;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static int n,m;
	static int[] parent;
	static PriorityQueue<edge> pq = new PriorityQueue<edge>();
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		parent = new int[n+1];
		
		for(int i=0; i<n+1; i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<m; i++) {
			String[] str = br.readLine().split(" ");
			pq.add(new edge(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2])));
		}
				
		for(int i=0; i<m; i++) {
			edge tmp = pq.poll();
			
			int a = find(tmp.s);
			int b = find(tmp.e);
			
			if(a==b) continue;
			
			union(a,b);
			result += tmp.v;
		}
		
		System.out.println(result);
		
	}
	
	public static int find(int a) {
		if(a == parent[a]) return a;
		parent[a] = find(parent[a]);
		return parent[a];
	}
	
	public static void union(int a,int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot != bRoot) {
			parent[aRoot] = b;
		} else {
			return;
		}
	}
}

class edge implements Comparable<edge>{
	int s;
	int e;
	int v;
	
	public edge(int s,int e,int v) {
		this.s = s;
		this.e = e;
		this.v = v;
	}

	@Override
	public int compareTo(edge arg0) {
		return arg0.v>=this.v?-1:1;
	}
}