package swea_q2105_디저트_카페;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class Main2 {
	//왼쪽 아래 -> 오른쪽 아래 -> 오른쪽 위 -> 왼쪽 위
	static int[] dx = {1,1,-1,-1,0};
	static int[] dy = {-1,1,1,-1,0};
	static int n,test;
	static int[][] map;
	static boolean[][] visited;
	static boolean[] valueVisited;
	static int result;
	
	public static void main(String[] args) throws Exception {
		/*
		 * dfs로 풀이
		 * 순회하는 방향을 쭉 이동시키면서 이동이 가능할떄까지 이동 시킨다.
		 * 왼쪽 아래 -> 오른쪽 아래 -> 오른쪽 위 -> 왼쪽 위 라고 가정.
		 * 왼쪽 아래로 이동할 수 있는 만큼은 계속 이동하고 각 경우마다 오른쪽 아래로도 같이 이동한다.
		 * 자기자신으로 돌아오면 종료
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		test = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<test; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			
			map = new int[n][n];
			result = Integer.MIN_VALUE;
			valueVisited = new boolean[101];
			visited = new boolean[n][n];
			
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					dfs(new point(i,j), new point(i,j), 0,0);
				}
			}
			
			if(result == Integer.MIN_VALUE) {
				System.out.println("#" + (t+1) + " " + -1);
			} else {
				System.out.println("#" + (t+1) + " " + result);
			}
		}
	}
	
	static void dfs(point start, point end, int count, int dir) {
		if(visited[start.x][start.y] && start.x == end.x && start.y == end.y) {
			result = Math.max(result, count);
			return;
		}
		
		if(visited[start.x][start.y] || valueVisited[map[start.x][start.y]]) {
			return;
		}
		
		visited[start.x][start.y] = true;
		valueVisited[map[start.x][start.y]] = true;
		
		 int ny = 0, nx = 0;
	        // d방향으로 한번더
	        ny = start.y + dy[dir];
	        nx = start.x + dx[dir];
	        if (nx>=0 && nx<n && ny>=0 && ny<n) {
	            dfs(new point(nx,ny), end, count+1, dir);
	        }
	 
	        // d+1 방향으로
	        if (dir + 1 != 4) {
	            ny = start.y + dy[dir + 1];
	            nx = start.x + dx[dir + 1];
	            if (nx>=0 && nx<n && ny>=0 && ny<n) {
	                dfs(new point(nx,ny), end, count+1, dir+1);
	            }
	        }
		
		visited[start.x][start.y] = false;
		valueVisited[map[start.x][start.y]] = false;
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
