package q16398;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static int n;
	static int[] parent;
	static int[][] map;
	static PriorityQueue<edge> pq = new PriorityQueue<edge>();
	static long result = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		
		for(int i=0; i<n; i++) {
			String[] str = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		parent = new int[n];
		
		for(int i=0; i<parent.length; i++) {
			parent[i]= i;
		}
		
		int start = 1;
		for(int i= start-1; i<n-1; i++) {
			for(int j= start; j<n; j++) {
				pq.add(new edge(i,j,map[i][j]));
			}
			start++;
		}
		
		int size = pq.size();
		for(int i=0; i<size; i++) {
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
	
	public edge(int s,int e, int v) {
		this.s = s;
		this.e = e;
		this.v = v;
	}

	@Override
	public int compareTo(edge arg0) {
		// TODO Auto-generated method stub
		return arg0.v >= this.v? -1:1;
	}
}