package swea_q13460;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int n,m;
	static int[][] map;
	static int count = 0;
	static boolean[][][][] visited;
	
	public static void main(String[] args) throws Exception {
		/*
		 * 1. 구슬 이동
		 * 2. 만약 R, B가 같은 자리라면, 둘 중에 많이 이동한 애를 하나 전으로 옮기기 (벽 나올 때까지 움직이는거)
		 * 3. 4차원 배열로 방문여부 확인
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		visited = new boolean[n][m][n][m];
		
		int rx = 0,ry = 0,bx = 0,by= 0;
		
		for(int i=0; i<n; i++) {
			String[] arr = br.readLine().split("");
			for(int j=0; j<m; j++) {
				String str = arr[j];
				int tmp = 0;
				
				switch(str) {
				case "#" :
					tmp = -1;
					break;
				case "." :
					tmp = 0;
					break;
				case "R":
					tmp = 0;
					rx = i;
					ry = j;
					break;
				case "B":
					tmp = 0;
					bx = i;
					by = j;
					break;
				case "O":
					tmp = 3;
					break;
				}
				
				map[i][j] = tmp;
			} 
		}
		
		bfs(rx,ry,bx,by);
		
	}
	
	static void bfs(int rx,int ry,int bx,int by) {
		Queue<pair> q = new LinkedList<pair>();
		pair p = new pair(rx,ry,bx,by);
		q.add(p);
		visited[rx][ry][bx][by] = true;
		
		loop: while(true) {
			int size = q.size();
			
			count++;
			
			if(count > 10) {
				System.out.println(-1);
				System.exit(0);
			}
			
			for(int k=0; k<size; k++) {
				pair t = q.poll();
				int rrx = t.rx;
				int rry = t.ry;
				int bbx = t.bx;
				int bby = t.by;
				
				for(int i=0; i<4; i++) {
					boolean blueFlag = false;
					int new_rrx = t.rx;
					int new_rry = t.ry;
					int new_bbx = t.bx;
					int new_bby = t.by;
					
					//파란 공 이동
					while(true) {
						new_bbx += dx[i];
						new_bby += dy[i];
						
						if(map[new_bbx][new_bby] == -1) {
							new_bbx -= dx[i];
							new_bby -= dy[i];
							break;
						}
						
						if(map[new_bbx][new_bby] == 3) {
							blueFlag = true;
						}
						
					}
					
					//빨간 공 이동
					while(true) {
						new_rrx += dx[i];
						new_rry += dy[i];
						
						if(map[new_rrx][new_rry] == -1) {
							new_rrx -= dx[i];
							new_rry -= dy[i];
							break;
						}
						
						if(map[new_rrx][new_rry] == 3 && !blueFlag) {
							System.out.println(count);
							return;
						}

					}
					
					//빨강공 파랑공 위치가 같을 경우 조정
					if(new_rrx == new_bbx && new_rry == new_bby) {
						int red_distance = Math.abs(rrx - new_rrx) + Math.abs(rry - new_rry);
						int blue_distance = Math.abs(bbx - new_bbx) + Math.abs(bby - new_bby);
						
						if(red_distance > blue_distance) {
							new_rrx -= dx[i];
							new_rry -= dy[i];
						} else {
							new_bbx -= dx[i];
							new_bby -= dy[i];
						}
						
					}
					
					if(map[new_rrx][new_rry] == 3 && !blueFlag) {
						System.out.println(count);
						return;
					}
					
					
					//pair 수정
					if(!visited[new_rrx][new_rry][new_bbx][new_bby] && !blueFlag) {
						q.add(new pair(new_rrx, new_rry, new_bbx, new_bby));
						visited[new_rrx][new_rry][new_bbx][new_bby] = true;
					}
				}
				
			}
			
		}
	}
}

class pair {
	int rx;
	int ry;
	int bx;
	int by;
	
	public pair(int rx,int ry, int bx, int by) {
		this.rx = rx;
		this.ry = ry;
		this.bx = bx;
		this.by = by;
	}
}
