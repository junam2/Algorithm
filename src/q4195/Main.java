package q4195;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int test,f;
	static int[] parent;
	static int[] count;
	static StringBuffer sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuffer();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		test = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<test; t++) {
			st = new StringTokenizer(br.readLine());
			f = Integer.parseInt(st.nextToken());
			int idx = 1;
			HashMap<String, Integer> map = new HashMap<String,Integer>();
			parent = new int[200001];
			count = new int[200001];
			
			for(int i=0; i<parent.length; i++) {
				parent[i] = i;
				count[i] = 1;
			}
			
			for(int i=0; i<f; i++) {
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				
				if(!map.containsKey(a)) {
					map.put(a, idx++);
				}
				
				if(!map.containsKey(b)) {
					map.put(b, idx++);
				}
				
				int index_a = map.get(a);
				int index_b = map.get(b);
				
				start(index_a,index_b);

			}
		}
		
		System.out.println(sb.toString());
	}
	
	static void start(int a,int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) {
			sb.append(count[aRoot]).append("\n");
			return;
		}
		
		parent[aRoot] = bRoot;
		count[bRoot] += count[aRoot];
		
		sb.append(count[bRoot]).append("\n");
 	}
	
	static int find(int a) {
		if(a == parent[a]) return a;
		parent[a] = find(parent[a]);
		return parent[a];
	}
}

class edge implements Comparable<edge>{
	int s;
	int e;
	int v = 1;
	
	public edge(int s, int e) {
		this.s = s;
		this.e = e;
	}

	@Override
	public int compareTo(edge arg0) {
		return (arg0.v>=this.v)?-1:1;
	}
}