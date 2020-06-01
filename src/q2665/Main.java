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
		 * �������� ���ϴ°Ÿ� ���� ����.. ������ �ð��ʰ� ����.
		 * 3���� �迭�� ���� �մ� ���� ó���ϴ� ������� �����غ���.
		 * �׸��� ���� �ּڰ��� ���� ���� �մ� ������ ���� �ּڰ����� �������� �ٷ� return�ؼ� ����ġŰ
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
			
			//����ġ��
			if(v.break_wall_count >= result) {
				continue;
			}
			
			//���� �� �ּڰ� Ȯ��
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
