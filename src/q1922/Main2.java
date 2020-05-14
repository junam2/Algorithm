package q1922;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2 {
	static int n,m;
	static PriorityQueue<edge2> pq = new PriorityQueue<edge2>();
	static int[] parent;
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			pq.add(new edge2(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		
		//int[] parent ÃÊ±âÈ­
		parent = new int[n+1];
		
		for(int i=0; i<n+1; i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<m; i++) {
			edge2 tmp = pq.poll();
			
			int a = find(tmp.s);
			int b = find(tmp.e);
			
			if(a==b) continue;
			union(a,b);
			result += tmp.v;
		}
		
		System.out.println(result);
		
	}
	
	static int find(int a) {
		if(a == parent[a]) {
			return a;
		}
		parent[a] = find(parent[a]);
		return parent[a];
	}
	
	static void union(int a,int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot != bRoot) {
			parent[aRoot] = b;
		} else {
			return;
		}
	}
}

class edge2 implements Comparable<edge2>{
	int s;
	int e;
	int v;
	
	public edge2(int s,int e,int v) {
		this.s = s;
		this.e = e;
		this.v = v;
	}

	@Override
	public int compareTo(edge2 arg0) {
		// TODO Auto-generated method stub
		return (arg0.v >= this.v)?-1:1;
	}
}