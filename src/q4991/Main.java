package q4991;

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
	static edge start;
	static ArrayList<edge> dust;
	static char[][] map;
	static int[][] dist;
	static boolean[][] visited;
	static int minDistance;
	static boolean flag;
	static BufferedReader br;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		/*
		 * 1. 로봇 청소기 위치와 각 먼지 거리, 먼지 끼리의 거리 구하기
		 * 2. 순열로 먼지 돌면서 거리 더해주기
		 * 3. 최솟값 찾기
		 */
		
		loop: while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			
			if(n==0 && m==0) {
				break;
			}
			
			map = new char[n][m];
			minDistance = Integer.MAX_VALUE;
			dust = new ArrayList<edge>();
			flag = true;
			
			for(int i=0; i<n; i++) {
				char[] arr = br.readLine().toCharArray();
				
				map[i] = arr;
				
				for(int j=0; j<m; j++) {
					if(map[i][j] == 'o') {
						start = new edge(i,j);
					} else if(map[i][j] == '*') {
						dust.add(new edge(i,j));
					}
				}
			}
			
			dist = new int[dust.size()+1][dust.size()+1];
			
			//거리 구하기
			visited = new boolean[n][m];
			bfs_distance(0,start);
			for(int i=0; i<dust.size(); i++) {
				visited = new boolean[n][m];
				bfs_distance(i+1, dust.get(i));
			}
			
			//순열
			LinkedList<Integer> per = new LinkedList<Integer>();
			boolean[] check = new boolean[dust.size()];
			permutation(dust.size(), dust.size(), per, check);
			
			if(flag) {
				System.out.println(minDistance);
			}
			
		}
		
		System.out.println(sb.toString());
	}
	
	static void permutation(int c,int r, LinkedList<Integer> per, boolean[] check) {
		if(per.size() == r) {
			int d = 0;
			ArrayList<Integer> order = new ArrayList<Integer>();
			order.add(0);
			
			for(int i=0; i<per.size(); i++) {
				order.add(per.get(i));
			}
			
			for(int i=0; i<order.size()-1; i++) {
				if(dist[order.get(i)][order.get(i+1)] == 0) {
					System.out.println(-1);
					flag = false;
					return;
				}
				
				d += dist[order.get(i)][order.get(i+1)];
			}
			
			minDistance = Math.min(minDistance, d);
			
			return;
		}
		
		for(int i=0; i<c; i++) {
			if(!check[i]) {
				check[i] = true;
				per.add(i+1);
				permutation(c,r,per,check);
				check[i] = false;
				per.removeLast();
			}
		}
	}
	
	static void bfs_distance(int index, edge e) {
		int tmp = -1;
		int count = 0;
		Queue<edge> q = new LinkedList<edge>();
		q.add(e);
		visited[e.x][e.y] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			tmp++;
			
			for(int i=0; i<size; i++) {
				edge v = q.poll();
				
				for(int j=0; j<dust.size(); j++) {
					edge d = dust.get(j);
					
					if(v.x == d.x && v.y == d.y) {
						dist[index][j+1] = tmp;
						dist[j+1][index] = tmp;
						count++;
					}
				}
				
				if(count == dust.size()) {
					return;
				}
				
				for(int j=0; j<4; j++) {
					int x = v.x + dx[j];
					int y = v.y + dy[j];
					
					if(x>=0 && x<n && y>=0 && y<m && !visited[x][y] && map[x][y] != 'x') {
						q.add(new edge(x,y));
						visited[x][y] = true;
					}
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
