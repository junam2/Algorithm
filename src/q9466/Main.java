package q9466;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int n;
	static List<Integer>[] graph;
	static boolean[] cycle;
	static int team;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for(int q=0; q<t; q++) {
			team = 0;
			n = Integer.parseInt(br.readLine());
			String[] str = br.readLine().split(" ");
			graph = new List[n+1];
			cycle = new boolean[n+1];
			
			for(int i=1; i<=n; i++) {
				graph[i] = new LinkedList<Integer>();
				graph[i].add(Integer.parseInt(str[i-1]));
			}
			
			for(int i=1; i<=n; i++) {
				if(!cycle[i]) {
					dfs(i);
					team++;
				}
			}
			
			System.out.println(team);
		}
	}
	
	static void dfs(int a) {
		cycle[a] = true;
		
		for(Integer e : graph[a]) {
			if(!cycle[e]) {
				dfs(e);
			}
 		}
	}
}