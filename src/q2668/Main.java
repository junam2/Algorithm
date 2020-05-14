package q2668;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {
	static int n;
	static int[] arr;
	static boolean[] visited;
	static Set<Integer> set = new HashSet<Integer>();
		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n+1];
		
		for(int i=1; i<=n; i++) {
			int a = Integer.parseInt(br.readLine());
			arr[i] = a; 
		}
		
		for(int i=1; i<=n; i++) {
			visited = new boolean[n+1];
			dfs(i, i, 1);
		}
		
		System.out.println(set.size());
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		Iterator<Integer> it = set.iterator();
		
		while(it.hasNext()) {
			result.add(it.next());
		}
		
		Collections.sort(result);
		
		for(int i=0; i<result.size(); i++) {
			System.out.println(result.get(i));
		}
		
	}
	
	static void dfs(int start, int end, int count) {
		if(arr[start] == end) {
			set.add(end);
		}
		
		if(count > n) {
			return;
		}
		
		dfs(arr[start], end, count+1);
	}
}
