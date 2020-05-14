package q9938;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n,l;
	static boolean[] visited;
	static int[] parent;
	
	
	static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) throws Exception {
		/*
		 * 1. drawer[ai] = 0 �� �ű⿡ �ֱ� (drawer �迭�� �־��ش�)
		 * 2. drawer[bi] = 0 �� �ű⿡ �ֱ� (����)
		 * 3. �� �� �ƴ϶��,
		 * 3-1. drawer[ai]�� bi�� �ִ´�.
		 * 3-2. drawer[bi]�� 0 �� �ƴ϶��, find�� ���� �ֻ����� ã�´�.
		 * 3-3. �ֻ����� ã�Ҵµ� root�� 0�� ���´ٸ� 4������ �̵�
		 * 4-1. drawer[bi]�� ���� �ٸ� ������ �̵���Ų��.
		 * 4-2. find�� ���� �ֻ��� ã�ư���
		 * 4-3. 0 �� ���´ٸ�, ���� ���Ź�����.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		
		parent = new int[300001];
		visited = new boolean[300001];
		
		for(int i=1; i<l+1; i++) {
			parent[i] = i;
		}
		
		
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			int ai = Integer.parseInt(st.nextToken());
			int bi = Integer.parseInt(st.nextToken());
			
			if(!visited[ai]) {
				visited[ai] = true;
				union(ai, bi);
			} else if(!visited[bi]) {
				visited[bi] = true;
				union(bi, ai);
			} else if(!visited[find(parent[ai])]){
				visited[find(parent[ai])] = true;
				union(ai,bi);
			} else if(!visited[find(parent[bi])]) {
				visited[find(parent[bi])] = true;
				union(bi,ai);
			} else {
				sb.append("SMECE").append("\n");
			}
		}
		
		System.out.println(sb.toString());
		
	}
	
	static void union(int a,int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		parent[aRoot] = bRoot;
		
		sb.append("LADICA").append("\n");
	}
	
	static int find(int a) {
		if(a == parent[a]) return a;
		return parent[a] = find(parent[a]);
	}
}
