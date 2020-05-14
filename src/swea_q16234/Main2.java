package swea_q16234;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {
	static int n,l,r;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int[][] map;
	static boolean[][] friend;
	static int move_count = 0;
	static int friend_count = 0;
	static int num = 0;
	static boolean flag = false;
	static ArrayList<edge> friends = new ArrayList<edge>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			friend = new boolean[n][n];
			flag = false;
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(!friend[i][j]) {
						init();
						bfs(new edge(i,j));
						
						if(friend_count != 0) {
							mapSetNum(friends, (int)(num/friend_count));
						}
					}
				}
			}
			
			if(!flag) {
				break;
			}
			move_count++;

			
		}
		
		System.out.println(move_count);
		
	}
	
	static void mapSetNum(ArrayList<edge> arr, int devidedNum) {
		for(int i=0; i<arr.size(); i++) {
			edge tmp = arr.get(i);
			map[tmp.x][tmp.y] = devidedNum;
		}
	}
	
	static void init() {
		friends = new ArrayList<edge>();
		friend_count = 0;
		num = 0;
	}
	
	static void bfs(edge e) {
		Queue<edge> q = new LinkedList<edge>();
		q.add(e);
		
		while(!q.isEmpty()) {
			edge v = q.poll();
			
			for(int i=0; i<4; i++) {
				int x = v.x + dx[i];
				int y = v.y + dy[i];
				
				if(x>=0 && x<n && y>=0 && y<n && !friend[x][y]) {
					int diff = Math.abs(map[v.x][v.y] - map[x][y]);
					
					if(diff>=l && diff<=r) {
						if(!friend[v.x][v.y]) {
							friend_count++;
							num += map[v.x][v.y];
							friends.add(new edge(v.x, v.y));
						}
												
						flag = true;
						friends.add(new edge(x,y));
						friend_count++;
						num += map[x][y];
						
						friend[v.x][v.y] = true;
						friend[x][y] = true;
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
	
	public edge(int x,int y) {
		this.x = x;
		this.y = y;
	}
}