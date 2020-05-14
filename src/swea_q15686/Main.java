package swea_q15686;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int n,m;
	static int[][] map;
	static int[][] distance;
	static boolean[][] visited;
	static ArrayList<dot> home = new ArrayList<dot>();
	static ArrayList<dot> chick = new ArrayList<dot>();
	static ArrayList<dot[]> comb_result = new ArrayList<dot[]>();
	static ArrayList<Integer> result = new ArrayList<Integer>();
	static ArrayList<Integer> real_result = new ArrayList<Integer>();

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		
		map = new int[n][n];
		dot[] tmp = new dot[m];
		
		
		for(int i=0; i<n; i++) {
			str = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				
				if(map[i][j] == 2) {
					chick.add(new dot(i,j));
				} else if(map[i][j] == 1) {
					home.add(new dot(i,j));
				}
			}
		}
		combination(tmp, 0, chick.size(), m, 0, chick);
		
		for(int t=0; t<comb_result.size(); t++) {
			int total = 0;
			dot[] chicks = comb_result.get(t);
			for(int i=0; i<home.size(); i++) {
				result = new ArrayList<Integer>();
				dot d = home.get(i);
				
				for(int j=0; j<chicks.length; j++) {
					dot c = chicks[j];
					
					int distance = Math.abs(d.x - c.x) + Math.abs(d.y - c.y);
					
					result.add(distance);
				}
				
				total += Collections.min(result);
			}
			real_result.add(total);
		}
		
		System.out.println(Collections.min(real_result));
		/*
		for(int t=0; t<comb_result.size(); t++) {
			int[][] map_copy = new int[n][n];
			visited = new boolean[n][n];
			distance = new int[n][n];
			result = new ArrayList<Integer>();
			
			deepCopy(map_copy, comb_result.get(t));
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(map_copy[i][j] == 1) {
						bfs(new dot(i,j), map_copy);
					}
				}
			}
			int total = 0;
			
			for(int i=0; i<result.size(); i++) {
				total += result.get(i);
			}
			
			real_result.add(total);
		}
		
		System.out.println(Collections.min(real_result));
		*/
		
	}
	
	static void bfs(dot d, int[][] map) {
		visited = new boolean[n][n];
		distance = new int[n][n];
		
		int min = Integer.MAX_VALUE;
		Queue<dot> q = new LinkedList<dot>();
		q.add(d);
		visited[d.x][d.y] = true;
		
		while(!q.isEmpty()) {
			dot t = q.poll();
			int x = t.x;
			int y = t.y;
			
			
			for(int i=0; i<4; i++) {
				int x2 = x + dx[i];
				int y2 = y + dy[i];
				
				if(x2>=0 && x2<n && y2>=0 && y2<n && map[x2][y2] == 2) {
					if(min > distance[x][y]) {
						min = distance[x][y] + 1;
					}
				}
				
				
				if(x2>=0 && x2<n && y2>=0 && y2<n && map[x2][y2]!=2 && !visited[x2][y2]) {
					q.add(new dot(x2,y2));
					visited[x2][y2] = true;
					distance[x2][y2] = distance[x][y] + 1;
				}
			}
		}
		
		result.add(min);
	}
	
	public static void combination(dot[] tmp, int depth, int n, int k, int target, ArrayList<dot> arr) {
		if(k==0) {
			dot[] tmptmp = new dot[tmp.length];
			
			for(int i=0; i<tmptmp.length; i++) {
				tmptmp[i] = tmp[i];
			}
			
			comb_result.add(tmptmp);
		} else if(target == n) {
			return;
		} else {
			tmp[depth] = arr.get(target);
			combination(tmp, depth+1, n, k-1, target+1, arr);
			combination(tmp, depth, n, k, target+1, arr);
		}
	}
	
	public static void deepCopy(int[][] arr, dot[] d) {
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				if(map[i][j] == 2) {
					arr[i][j] = 0;
				} else {
					arr[i][j] = map[i][j];
				}		
			}
		}
		
		for(int i=0; i<d.length; i++) {
			int x = d[i].x;
			int y = d[i].y;
			
			arr[x][y] = 2;
		}
	}
}

class dot {
	int x;
	int y;
	
	public dot(int x,int y) {
		this.x = x;
		this.y = y;
	}
}