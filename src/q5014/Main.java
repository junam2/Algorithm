package q5014;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class Main {
	static int[] map;
	static boolean[] visited;
	static int F,S,G,U,D;
	static int count = 0;
	static int max_conut;
	static Queue<Integer> q = new LinkedList<Integer>();

	public static void main(String[] args) throws Exception	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[F+1];
		Arrays.fill(map, -1);
		visited = new boolean[F+1];
				
		bfs(S);
		if(map[G] == -1 && S!=G) {
			System.out.println("use the stairs");
		} else {
			System.out.println(map[G]+1);
		}
		
	}
	
	static void bfs(int s) {
		q.add(s);
		visited[s] = true;
		
		while(!q.isEmpty()) {
			count++;
			int t = q.remove();
			
			if(t == G) {
				return;
			}

			if(t+U <= F && !visited[t+U]) {
				q.add(t+U);
				map[t+U] = map[t] + 1;
				visited[t+U] = true;
			}
			if(t-D >= 1 && !visited[t-D]) {
				q.add(t-D);
				map[t-D] = map[t] + 1;
				visited[t-D] = true;
			}
		}
	}
}
