package swea_q17822;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int n,m,test;
	static int[][] map;
	static boolean[][] visited;
	static boolean adjoinFlag;
	static int result;
	static Queue<edge> q;
	
	public static void main(String[] args) throws Exception {
		/*
		 * 1. ȸ����Ų��.
		 * 2. ������ ���� ã�´�.
		 * 2-1. �����¿�. �糡�� ��� / �� ��, �� �Ʒ��� ��� ����
		 * 3. ������ ���� �����.
		 * 3-1. ���� �� ������ ��: �ٷ� ���������� �ڿ��� �����Ѱ� �������� ���� �� ����.
		 * 3-2. �������ϴ� queue�� arraylist�� ��Ƴ��� �� ���� �����.
		 * 4. �������� �ʴٸ�, ��� ���ؼ� ũ�� -1 ������ +1 ���ش�.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		test = Integer.parseInt(st.nextToken());
		
		map = new int[n+1][m+1];
		visited = new boolean[n+1][m+1];
		result = 0;
		
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int t=0; t<test; t++) {
			st = new StringTokenizer(br.readLine());
			int xi = Integer.parseInt(st.nextToken());
			int di = Integer.parseInt(st.nextToken());
			int ki = Integer.parseInt(st.nextToken());
			
			rotate(xi, di, ki);
			q = new LinkedList<edge>();
			
			adjoinFlag = false;
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=m; j++) {
					if(map[i][j] != Integer.MAX_VALUE) {
						findAdjoin(i,j);
					}
				}
			}
			
			if(q.size() > 0) {
				while(!q.isEmpty()) {
					edge v = q.poll();
					map[v.x][v.y] = Integer.MAX_VALUE;
				}
				adjoinFlag = true;
			}
			
			//��� ���ؼ� +1, -1 �ϱ�
			if(!adjoinFlag) {
				getAverageAndPlusMinus();
			}
			
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=m; j++) {
					if(map[i][j] == Integer.MAX_VALUE) {
						System.out.print(-1 + " ");
					} else {
						System.out.print(map[i][j] + " ");
					}
				}
				System.out.println();
			}
			
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				if(map[i][j] != Integer.MAX_VALUE) {
					result += map[i][j];
				}
			}
		}
		
		System.out.println(result);
	}
	
	static void getAverageAndPlusMinus() {
		//��� ���� �� -1�� �ƴ� �� �� Ȯ���ϱ�
		int total = 0;
		int count = 0;
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				if(map[i][j] != Integer.MAX_VALUE) {
					total += map[i][j];
					count++;
				}
			}
		}
		
		if(count == 0) {
			return;
		}
		
		//�ε��Ҽ��� �� �����ϱ�
		double average = (double) total/count;
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				if(map[i][j] != Integer.MAX_VALUE ) {
					if(average > map[i][j]) {
						map[i][j]++;
					} else if(average < map[i][j]) {
						map[i][j]--;
					}
				}
			}
		}
	}
	
	static void findAdjoin(int a, int b) {
		Queue<edge> qq = new LinkedList<edge>();
		int x = a;
		int y = b;
		qq.add(new edge(x,y));
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<1 || nx>n) continue;
			if(ny<1) ny = m;
			if(ny>m) ny = 1;
			
			if(nx>=1 && nx<=n && ny>=1 && ny<=m && map[nx][ny] != Integer.MAX_VALUE && map[nx][ny] == map[x][y]) {
				qq.add(new edge(nx,ny));
			}
		}
		
		if(qq.size()>1) {
			while(!qq.isEmpty()) {
				q.add(qq.poll());
			}
		}
	}
	
	static void rotate(int xi, int di, int ki) {
		for(int i=xi; i<=n; i+=xi) {
			int[] arr = map[i];
			
			//di=0 �ð� ����, d1=1 �ݽð� ����
			//�ð��ʰ����� deque�� �ٲ��ֱ�
			if(di == 0) {
				for(int q=0; q<ki; q++) {
					int tmp = map[i][m];
					for(int j=m; j>1; j--) {
						map[i][j] = map[i][j-1];
					}
					map[i][1] = tmp;
				}
				
			} else {
				for(int q=0; q<ki; q++) {
					int tmp = map[i][1];
					for(int j=1; j<m; j++) {
						map[i][j] = map[i][j+1];
					}
					map[i][m] = tmp;
				}
				
			}
		}
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