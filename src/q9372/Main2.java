package q9372;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {
	//dfs ÀÌ¿כ
	static int n,m;
	static int[][] map;
	static int count;
	static Queue<Integer> q = new LinkedList<Integer>();
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		int test = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<test; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			
			map = new int[n+1][n+1];
			visited = new boolean[n+1];
			count = 0;
						
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[a][b] = 1;
				map[b][a] = 1;
			}
			
			bfs();
			
			System.out.println(count-1);
		}
	}
	
	static void bfs() {
		q.add(1);
		visited[1] = true;
		
		
		while(!q.isEmpty()) {
			int tmp = q.poll();
			count++;
			
			for(int i=1; i<=n; i++) {
				if(map[tmp][i] == 1 && !visited[i]) {
					q.add(i);
					visited[i] = true;
				}
			}
		}
	}

}

class point {
	int x;
	int y;
	
	public point(int x,int y) {
		this.x = x;
		this.y = y;
	}
}
