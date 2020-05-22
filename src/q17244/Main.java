package q17244;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int n,m;
	static char[][] map;
	static boolean[][] visited;
	static edge start,end;
	static LinkedList<edge> stuff = new LinkedList<edge>();
	static int tmp_distance;
	static int min_distance = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		/*
		 * 1. 순열 이용해서 시작점 S - 물건들 - E 경로 구하기
		 * 2. 하나씩 최단 경로 구해서 이동 (bfs)
		 * 2-1. arr[i] - arr[i+1] 경로 구하기
		 * 2-2. 경로 누적 더해주기
		 * 3. 더해주다가 E에 도착하면 최솟값 검증
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		
		for(int i=0; i<n; i++) {
			char[] arr = br.readLine().toCharArray();
			map[i] = arr;
			
			for(int j=0; j<m; j++) {
				if(map[i][j] == 'S') {
					start = new edge(i,j);
				} else if(map[i][j] == 'X') {
					stuff.add(new edge(i,j));
				} else if(map[i][j] == 'E') {
					end = new edge(i,j);
				}
			}
		}
		
		int[] check = new int[stuff.size()];
		LinkedList<Integer> per = new LinkedList<Integer>(); 
		permutation(stuff.size(), stuff.size(), per, check);
		
		System.out.println(min_distance);
		
	}
	
	static void permutation(int nn,int r, LinkedList<Integer> per, int[] check) {
		if(per.size() == r) {
			//물건 순서 정한 후 시작
			ArrayList<edge> order = new ArrayList<edge>();
			order.add(start);
			
			for(int i=0; i<stuff.size(); i++) {
				order.add(stuff.get(per.get(i)));
			}
			
			order.add(end);
			
			int distance = 0;
			
			for(int i=0; i<order.size()-1; i++) {
				tmp_distance = 0;
				visited = new boolean[n][m];
				
				bfs(order.get(i), order.get(i+1));
				distance += tmp_distance-1;
			}

			min_distance = Math.min(min_distance, distance);
			
			return;
		}
		
		for(int i=0; i<nn; i++) {
			if(check[i] == 0) {
				check[i] = 1;
				per.add(i);
				permutation(nn,r,per,check);
				check[i] = 0;
				per.removeLast();
			}
		}
	}
	
	static void bfs(edge s, edge e) {
		Queue<edge> q = new LinkedList<edge>();
		q.add(s);
		visited[s.x][s.y] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			tmp_distance++;
			for(int i=0; i<size; i++) {
				edge v = q.poll();
				if(v.x == e.x && v.y == e.y) {
					return;
				}
				
				for(int j=0; j<4; j++) {
					int x = v.x + dx[j];
					int y = v.y + dy[j];
					
					if(x>=0 && x<n && y>=0 && y<m && !visited[x][y] && map[x][y] != '#') {
						visited[x][y] = true;
						q.add(new edge(x,y));
					}
				}
			}
		}
	}
}

class edge {
	int x;
	int y;
	
	public edge(int x ,int y) {
		this.x = x;
		this.y = y;
	}
}
