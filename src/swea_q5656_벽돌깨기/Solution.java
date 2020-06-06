package swea_q5656_벽돌깨기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int w,h,n;
	static block[][] origin_map;
	static block[][] map;
	static boolean[][] visited;
	static int result;
	
	public static void main(String[] args) throws Exception {
		/*
		 * 중복순열 (순서 신경써할듯 ) 사용
		 * 1. w 중에 n 개 고른다.
		 * 2. 위에서부터 가장 가까운 벽돌을 찾는다.
		 * 3. 그 벽돌 기준으로 파괴시킨다.
		 * 4. 계속 깊게 들어가면서 재귀처럼 풀면 됨
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int test = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<test; t++) {
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			map = new block[h][w];
			origin_map = new block[h][w];
			result = Integer.MAX_VALUE;
			
			for(int i=0; i<h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<w; j++) {
					int size = Integer.parseInt(st.nextToken());
					origin_map[i][j] = new block(i,j,size);
					map[i][j] = new block(i,j,size);
				}
			}
			
			LinkedList<Integer> perm = new LinkedList<Integer>();
			
			//w개 중에 n개 고른다.
			rePermutation(w,n,perm);
			
			System.out.println("#" + (t+1) + " " + result);
		}
		
	}

	
	static void rePermutation(int w, int n, LinkedList<Integer> perm) {
		if(perm.size() == n) {
			//벽돌 꺠는 순서 정하기
			
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					map[i][j].size = origin_map[i][j].size;
				}
			}
			
			visited = new boolean[h][w];
			
			for(int i=0; i<perm.size(); i++) {
				int nh = getH(perm.get(i));
				
				if(nh == -1) continue;

				//폭발
				explosion(perm.get(i),nh);
				
				//내려오기
				mapDown();
			}
			
			int tmp = 0;
			
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					if(map[i][j].size != 0) {
						tmp++;
					}
				}
			}
			
			result = Math.min(result, tmp);
			return;
		}
		
		for(int i=0; i<w; i++) {
			perm.add(i);
			rePermutation(w,n,perm);
			perm.removeLast();
		}
	}
	
	static void mapDown() {
		//각 y좌표에 대해서
		for(int i=0; i<w; i++) {
			Queue<Integer> q = new LinkedList<Integer>();
			
			for(int j=h-1; j>=0; j--) {
				if(map[j][i].size > 0) q.add(map[j][i].size);
			}
			
			for(int j=h-1; j>=0; j--) {
				if(!q.isEmpty()) {
					map[j][i].size = q.poll();
				} else {
					map[j][i].size = 0;
				}
			}
		}
	}
	
	static void explosion(int nw,int nh) {
		//if(visited[nh][nw]) return;
		
		visited[nh][nw] = true;
		block b = map[nh][nw];
		int size = b.size;
		
		if(size > 1) {
			for(int i=1; i<size; i++) {
				for(int j=0; j<4; j++) {
					int x = b.x + dx[j]*i;
					int y = b.y + dy[j]*i;
					
					if(x<0 || x>=h || y<0 || y>=w) continue;
					
					if(map[x][y].size != 0) {
						block b1 = map[x][y];
						int size2 = b1.size;
						
						if(size2 > 1 && !visited[b1.x][b1.y]) {
							explosion(b1.y,b1.x);
						}
						
						map[x][y].size = 0;
					}
				}
			}
		}
		
		map[b.x][b.y].size = 0;;
	}
	
	static int getH(int w) {		
		for(int i=0; i<h; i++) {
			if(map[i][w].size != 0) {
				return i;
			}
		}
		
		return -1;
	}
}

class block {
	int x;
	int y;
	int size;
	
	public block(int x,int y,int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}
}