package swea_q2105_����Ʈ_ī��;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	//���� �Ʒ� -> ������ �Ʒ� -> ������ �� -> ���� ��
	static int[] dx = {1,1,-1,-1,0};
	static int[] dy = {-1,1,1,-1,0};
	static int n,test;
	static int[][] map;
	static boolean[][] visited;
	static boolean[] valueVisited;
	static int result;
	static int tmp_result;
	
	public static void main(String[] args) throws Exception {
		/*
		 * dfs�� Ǯ��
		 * ��ȸ�ϴ� ������ �� �̵���Ű�鼭 �̵��� �����ҋ����� �̵� ��Ų��.
		 * ���� �Ʒ� -> ������ �Ʒ� -> ������ �� -> ���� �� ��� ����.
		 * ���� �Ʒ��� �̵��� �� �ִ� ��ŭ�� ��� �̵��ϰ� �� ��츶�� ������ �Ʒ��ε� ���� �̵��Ѵ�.
		 * �ڱ��ڽ����� ���ƿ��� ����
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		test = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<test; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			
			map = new int[n][n];
			visited = new boolean[n][n];
			result = Integer.MIN_VALUE;
			valueVisited = new boolean[101];
			
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					Set<Integer> set = new HashSet<Integer>();
					set.add(map[i][j]);
					dfs(new edge(i,j), new edge(i,j), 0, 0);
				}
			}
			
			if(result == Integer.MIN_VALUE) {
				System.out.println("#" + (t+1) + " " + -1);
			} else {
				System.out.println("#" + (t+1) + " " + result);
			}
		}
	}
	
	static void dfs(edge start, edge end, int dir, int count) {
		if(visited[start.x][start.y] && start.x == end.x && start.y == end.y) {
			result = Math.max(result, count);
			return;
		}
		
		if(visited[start.x][start.y] || valueVisited[map[start.x][start.y]]) return;
		
		visited[start.x][start.y] = true;
		valueVisited[map[start.x][start.y]] = true;
		
		int nx = start.x + dx[dir];
		int ny = start.y + dy[dir];
		
		if(nx>=0 && nx<n && ny>=0 && ny<n) {
			dfs(new edge(nx,ny), end, dir, count+1);
		}
		
		if(dir+1 != 4) {
			int nx2 = start.x + dx[dir+1];
			int ny2 = start.y + dy[dir+1];
			
			if(count>0 && nx2>=0 && nx2<n && ny2>=0 && ny2<n) {
				dfs(new edge(nx2,ny2), end, dir+1,count+1);
			}
		}
		
		visited[start.x][start.y] = false;
		valueVisited[map[start.x][start.y]] = false;
	}
	
}

class edge {
	int x;
	int y;
	
	public edge(int x,int y) {
		this.x = x;
		this.y = y;
	}
 }
