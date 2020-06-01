package q2665;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int n;
	static int[][] map;
	static boolean[][][] visited;
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		/*
		 * 조합으로 구하는거면 답이 없음.. 무조건 시간초과 난다.
		 * 3차원 배열로 만들어서 뚫는 개수 처리하는 방식으로 접근해보자.
		 * 그리고 벽의 최솟값을 위해 만약 뚫는 개수가 현재 최솟값보다 많아지면 바로 return해서 가지치키
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		
		for(int i=0; i<n; i++) {
			String[] str = br.readLine().split("");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		visited = new boolean[100][n][n];
		
		bfs(new edge(0,0,0));
		
		System.out.println(result);
		
	}
	
	static void bfs(edge e) {
		visited[e.break_wall_count][e.x][e.y] = true;
		PriorityQueue<edge> q = new PriorityQueue<edge>();
		q.add(e);
		
		while(!q.isEmpty()) {
			edge v= q.poll();
			
			//가지치기
			if(v.break_wall_count >= result) {
				continue;
			}
			
			//도착 시 최솟값 확인
			if(v.x == n-1 && v.y == n-1) {
				result = Math.min(result, v.break_wall_count);
				break;
			}	
			
			for(int i=0; i<4; i++) {
				int x = v.x + dx[i];
				int y = v.y + dy[i];
				
				if(x>=0 && x<n && y>=0 && y<n) {
					if(map[x][y] == 1 && !visited[v.break_wall_count][x][y]) {
						q.add(new edge(x,y,v.break_wall_count));
						visited[v.break_wall_count][x][y] = true;
					} else if(map[x][y] == 0 && !visited[v.break_wall_count+1][x][y]){
						q.add(new edge(x,y,v.break_wall_count+1));
						visited[v.break_wall_count+1][x][y] = true;
					}
				}
			}
		}
	}
}

class edge implements Comparable<edge>{
	int x;
	int y;
	int break_wall_count;
	
	public edge(int x,int y,int break_wall_count) {
		this.x = x;
		this.y = y;
		this.break_wall_count = break_wall_count;
	}

	@Override
	public int compareTo(edge arg0) {
		return (this.break_wall_count > arg0.break_wall_count)?1:-1;
	}
}
