package q9372;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	//mst ¹öÀü
	static int n,m;
	static int[] parent;
	static PriorityQueue<edge> pq;
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		int test = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<test; t++) {
			pq = new PriorityQueue<edge>();
			result = 0;
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
								
				pq.add(new edge(a,b));
			}
			
			parent = new int[n+1];
			
			for(int i=0; i<n+1; i++) {
				parent[i] = i;
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
		
		
	}
	
	static int find(int a) {
		if(a == parent[a]) return a;
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

class edge implements Comparable<edge>{
	int s;
	int e;
	int v = 1;
	
	public edge(int s,int e) {
		this.s = s;
		this.e = e;
	}

	@Override
	public int compareTo(edge arg0) {
		return (arg0.v>=this.v)?-1:1;
	}
}
